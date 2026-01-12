package com.ndexsystems.phoenix.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ndexsystems.phoenix.services.AuthenticationService;
import com.ndexsystems.phoenix.util.AuthUserContext;
import com.ndexsystems.phoenix.views.LoginView;
import com.ndexsystems.phoenix.views.UserView;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/{product}/{locale}/{firmId}")
@RequiredArgsConstructor
public class LoginController {

	private final AuthenticationService authenticationService;
	private final HttpSession session;

	@GetMapping("/login")
	public String showLoginPage(@PathVariable String product, @PathVariable String locale, @PathVariable String firmId,
			Model model) {
		model.addAttribute("product", product);
		model.addAttribute("locale", locale);
		model.addAttribute("firmId", firmId);

		return "login";
	}

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> authenticateUser(@PathVariable String product,
			@PathVariable String locale, @PathVariable String firmId, @RequestBody LoginView loginForm) {

		UserView userView = authenticationService.authenticate(loginForm.loginId(), loginForm.password(), product);
		session.setAttribute("USER_CONTEXT", userView);
		AuthUserContext.set(userView);
		Map<String, Object> resp = new HashMap<>();
		resp.put("authenticated", true);
		resp.put("userId", loginForm.loginId());
		resp.put("firmId", firmId);
		resp.put("locale", locale);

		return ResponseEntity.ok(resp);
	}
}