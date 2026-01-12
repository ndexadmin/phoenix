package com.ndexsystems.phoenix.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndexsystems.phoenix.services.ActivityLogService;
import com.ndexsystems.phoenix.views.ManualAuditLogView;
import com.ndexsystems.phoenix.views.UserView;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/{product}/{locale}/{firmId}/activity")
@RequiredArgsConstructor
public class AddManualLogController {

	private final ActivityLogService activityLogService;
	private final HttpSession session;

	@PostMapping("/add")
	public String saveManualLog(@PathVariable String product, @PathVariable String locale, @PathVariable String firmId,
			@ModelAttribute("manualLog") ManualAuditLogView manualLog) {
		UserView user = (UserView) session.getAttribute("USER_CONTEXT");
		if (user == null) {
			return "redirect:/" + product + "/" + locale + "/" + firmId + "/login";
		}
	

		activityLogService.addManualAuditItem(user, manualLog);

		
		return "redirect:/" + product + "/" + locale + "/" + firmId + "/activity";
	}
}
