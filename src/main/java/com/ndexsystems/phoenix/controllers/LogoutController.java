package com.ndexsystems.phoenix.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogoutController {

	private final HttpSession session;

	@GetMapping("/logout")
	public String logout() {

		session.invalidate();

		return "redirect:/login";
	}
}
