package com.ndexsystems.phoenix.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndexsystems.phoenix.services.ActivityLogService;
import com.ndexsystems.phoenix.util.Constants;
import com.ndexsystems.phoenix.util.Utils;
import com.ndexsystems.phoenix.views.ActivitySearchView;
import com.ndexsystems.phoenix.views.ManualAuditLogView;
import com.ndexsystems.phoenix.views.UserView;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/{product}/{locale}/{firmId}")
@RequiredArgsConstructor
public class ActivityLogController {

	private final ActivityLogService activityLogService;
	private final HttpSession session;
	private final MessageSource messageSource;

	@GetMapping("/activity")
	public String showActivity(@PathVariable String product, @PathVariable String locale, @PathVariable String firmId,
			Model model) {
		UserView user = (UserView) session.getAttribute("USER_CONTEXT");
		if (user == null) {
			return "redirect:/" + product + "/" + locale + "/" + firmId + "/login";
		}

		ManualAuditLogView manualLog = new ManualAuditLogView();
		manualLog.setActivityDate(LocalDate.now().toString());
		manualLog.setImportance("0");
		manualLog.setActivityType("ACSF");
		manualLog.setUserId(user.getLoginId());

		LocalDateTime now = LocalDateTime.now();

	
		manualLog.setEnglishDescription(Utils.auditItemDescription(messageSource, Locale.ENGLISH, user.getLoginId(),
				user.getFirstName(), user.getLastName(), now));

		manualLog.setFrenchDescription(Utils.auditItemDescription(messageSource, Locale.FRENCH, user.getLoginId(),
				user.getFirstName(), user.getLastName(), now));

		activityLogService.addManualAuditItem(user, manualLog);

		model.addAttribute("title", "Activity Log");
		model.addAttribute("categories", activityLogService.setUpCategories("sysadmin", firmId));
		model.addAttribute("importances", activityLogService.setUpImportance());
		model.addAttribute("auditItems",
				activityLogService.lookupAuditTrailView("21", "%", "%", "%", LocalDate.now().atStartOfDay(),
						LocalDate.now().atTime(23, 59, 59), Constants.PAGE1, Constants.MAX_PAGE, null, null, locale));
		model.addAttribute("searchView", new ActivitySearchView());

		return "activityLog";
	}

	@PostMapping("/activity/search")
	public String searchActivities(@PathVariable String product, @PathVariable String locale,
			@PathVariable String firmId, @ModelAttribute("searchView") ActivitySearchView searchView, Model model) {

		UserView user = (UserView) session.getAttribute("USER_CONTEXT");
		if (user == null) {
			return "redirect:/" + product + "/" + locale + "/" + firmId + "/login";
		}

		prepareCommonModel(model, locale, firmId);

		LocalDateTime startDate = searchView.getFromDate() != null ? searchView.getFromDate().atStartOfDay()
				: LocalDateTime.of(1753, 1, 1, 0, 0, 0);

		LocalDateTime endDate = searchView.getToDate() != null ? searchView.getToDate().atTime(23, 59, 59)
				: LocalDateTime.of(9999, 12, 31, 23, 59, 59);

		searchView.setCategory(searchView.getCategory().replace("All", "%"));
		searchView.setImportance(searchView.getImportance().replace("All", "%"));
		model.addAttribute("auditItems",
				activityLogService.seachAuditTrailView("21", Utils.orWildcard(searchView.getUserId()),
						searchView.getCategory(), searchView.getImportance(), startDate, endDate, Constants.PAGE1,
						Constants.MAX_PAGE, searchView.getSearchDescription(), searchView.getSearchCriteria(), locale));

		model.addAttribute("searchView", searchView);

		return "activityLog";
	}

	private void prepareCommonModel(Model model, String locale, String firmId) {
		model.addAttribute("title", "Activity Log");
		model.addAttribute("categories", activityLogService.setUpCategories("sysadmin", firmId));
		model.addAttribute("importances", activityLogService.setUpImportance());
	}

	@GetMapping("/activity/add")
	public String showAddManualLog(@PathVariable String product, @PathVariable String locale,
			@PathVariable String firmId, Model model) {

		UserView user = (UserView) session.getAttribute("USER_CONTEXT");
		if (user == null) {
			return "redirect:/" + product + "/" + locale + "/" + firmId + "/login";
		}

		ManualAuditLogView manualLog = new ManualAuditLogView();
		manualLog.setActivityDate(LocalDate.now().toString());
		manualLog.setImportance("Information");
		manualLog.setActivityType("Manual edit");
		manualLog.setUserId(user.getLoginId());
		manualLog.setEnglishDescription("");
		manualLog.setFrenchDescription("");
		model.addAttribute("manualLog", manualLog);

		return "activityAdd";
	}
}
