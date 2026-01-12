package com.ndexsystems.phoenix.util;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public record Preferences(Locale locale, int scale, char groupingSeparator, char decimalSeparator,
		String negativeValueFormat, String dateFormat, char dateSeparator) implements Serializable {

	public static final String MM_DD_CCYY = "MM_DD_CCYY";
	public static final String DD_MM_CCYY = "DD_MM_CCYY";
	public static final String M_D_CCYY = "M_D_CCYY";
	public static final String CCYY_MM_DD = "CCYY_MM_DD";
	public static final String NEGATIVE_PREFIX = "NEGATIVE_PREFIX";
	public static final String NEGATIVE_PARENTHESIS = "NEGATIVE_PARENTHESIS";
	public static final String VALID_DATE_SEPARATORS = "\\-/ ";

	
	private static final Map<String, Preferences> CACHE = new ConcurrentHashMap<>();

	
	  public static Preferences getDefaultFor(Locale locale) {
	        Locale finalLocale = Objects.requireNonNullElse(locale, Locale.ENGLISH);
	        return CACHE.computeIfAbsent(finalLocale.toString(), key -> createDefault(finalLocale));
	    }

	/**
	 * Retourne les préférences par défaut (English).
	 */
	public static Preferences getDefault() {
		return getDefaultFor(Locale.ENGLISH);
	}

	public static Preferences createDefault(Locale locale) {
		if (Locale.US.equals(locale)) {
			return new Preferences(locale, 2, ',', '.', NEGATIVE_PREFIX, MM_DD_CCYY, '/');
		} else if ("fr".equals(locale.getLanguage())) {
			return new Preferences(locale, 2, ' ', ',', NEGATIVE_PREFIX, CCYY_MM_DD, '-');
		} else {
	
			return new Preferences(locale, 2, ',', '.', NEGATIVE_PREFIX, CCYY_MM_DD, '/');
		}
	}
}