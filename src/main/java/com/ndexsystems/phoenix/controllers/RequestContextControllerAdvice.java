package com.ndexsystems.phoenix.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.ndexsystems.phoenix.config.TenantContext;
import com.ndexsystems.phoenix.dto.UserLanguageResources;
import com.ndexsystems.phoenix.services.LanguageResourceService;
import com.ndexsystems.phoenix.util.UserContext;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class RequestContextControllerAdvice {

	private final LanguageResourceService languageResourceService;

	@ModelAttribute
	public void initContext(@PathVariable(required = false) String firmId,
			@PathVariable(required = false) String locale) {

		if (firmId != null) {
			TenantContext.setTenant(firmId);
		}

		if (locale != null) {
			UserLanguageResources resources = languageResourceService.load(locale);
			UserContext.set(resources);
		}
	}
}
