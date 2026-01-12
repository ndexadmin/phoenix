package com.ndexsystems.phoenix.services;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Service;

import com.ndexsystems.phoenix.dto.NdexEnumerations;
import com.ndexsystems.phoenix.dto.UserLanguageResources;

@Service
public class LanguageResourceService {

	public UserLanguageResources load(String localeCode) {
		Locale locale = switch (localeCode.toLowerCase()) {
	    case "fr" -> Locale.FRENCH;
	    case "en" -> Locale.ENGLISH;
	    default -> Locale.ENGLISH;
	};

		ResourceBundle backdoorBundle = ResourceBundle.getBundle("ndex/fenginebackdoor", locale);
		ResourceBundle equate = ResourceBundle.getBundle("ndex/equateEnumerationResources", locale);
		ResourceBundle ndexBundle = ResourceBundle.getBundle("ndex/ndexEnumerationResourceBundle", locale);
		NdexEnumerations ndexEnums = NdexEnumerations.load(ndexBundle);

		return new UserLanguageResources(backdoorBundle,equate,ndexEnums, locale);
	}
}


