package com.ndexsystems.phoenix.dto;

import java.util.Locale;
import java.util.ResourceBundle;

public record UserLanguageResources(ResourceBundle backdoorBundle,ResourceBundle equate,NdexEnumerations ndexEnums, Locale locale) {
}
