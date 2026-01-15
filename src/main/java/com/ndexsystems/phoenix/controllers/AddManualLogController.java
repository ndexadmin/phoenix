package com.ndexsystems.phoenix.controllers;

import java.time.LocalDate;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndexsystems.phoenix.config.TenantContext;
import com.ndexsystems.phoenix.services.ActivityLogService;
import com.ndexsystems.phoenix.util.Constants;
import com.ndexsystems.phoenix.views.ActivitySearchView;
import com.ndexsystems.phoenix.views.ContextView;
import com.ndexsystems.phoenix.views.ManualAuditLogView;
import com.ndexsystems.phoenix.views.UserView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/{product}/{locale}/activity")

public class AddManualLogController extends BaseController {

	private final ActivityLogService activityLogService;

	public AddManualLogController(ActivityLogService activityLogService, HttpSession session,
			MessageSource messageSource) {
		super(session, messageSource);
		this.activityLogService = activityLogService;
	}

	@PostMapping("/add")
	public String saveManualLog(@PathVariable String product, @PathVariable String locale,
			@ModelAttribute("manualLog") ManualAuditLogView manualLog) {
		ContextView ctx = requireContext(product, locale);
		UserView user = ctx.getUser();
		manualLog.setActivityType("14");
		activityLogService.addManualAuditItem(user, manualLog);

		return "redirect:/" + product + "/" + locale + "/" + "activity/add";
	}

	@GetMapping("/return")
	public String returnToActivity(@PathVariable String product, @PathVariable String locale, Model model) {
		ContextView ctx = requireContext(product, locale);

		model.addAttribute("title", "Activity Log");
		model.addAttribute("categories", activityLogService.setUpCategories("sysadmin", TenantContext.getTenant()));
		model.addAttribute("importances", activityLogService.setUpImportance());
		model.addAttribute("auditItems",
				activityLogService.lookupAuditTrailView(
						String.valueOf(ctx.getOrganizationUnit().getOrganizationUnitKey()), "%", "%", "%",
						LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59, 59), Constants.PAGE1,
						Constants.MAX_PAGE, null, null, locale));
		model.addAttribute("searchView", new ActivitySearchView());

		return "activityLog";
	}
}
