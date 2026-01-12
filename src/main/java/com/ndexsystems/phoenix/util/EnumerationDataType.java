package com.ndexsystems.phoenix.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
*/
public abstract class EnumerationDataType {
	private String name;
	private String persistentId;

	protected EnumerationDataType(String aName, String aPersistentId) {
		name = aName;
		persistentId = aPersistentId;
	}

	public String getName() {
		return name;
	}

	public String getPersistentId() {
		return persistentId;
	}

	protected String retrieveResource(String aResourceBundleName, String aResourceKey, Locale aLocale) {
		ResourceBundle resourceBundle;
		StringBuffer warningBuffer;
		String resource;

		resourceBundle = null;

		try {
			resourceBundle = ResourceBundle.getBundle("com.ndexsystems.datatypes." + aResourceBundleName, aLocale);
		} catch (MissingResourceException e) {
			warningBuffer = new StringBuffer();
			warningBuffer.append("Can't find resource bundle for base name '");
			warningBuffer.append(aResourceBundleName);
			warningBuffer.append("' ");
			warningBuffer.append(aLocale);
		}

		if (resourceBundle != null) {
			try {
				resource = resourceBundle.getString(aResourceKey);
			} catch (MissingResourceException e) {
				warningBuffer = new StringBuffer();
				warningBuffer.append("Can't find resource for key '");
				warningBuffer.append(aResourceKey);
				warningBuffer.append("' in resource bundle '");
				warningBuffer.append(aResourceBundleName);
				warningBuffer.append("' ");
				warningBuffer.append(aLocale);
				resource = getName();
			}
		} else {
			resource = aResourceKey + '.' + aLocale.getLanguage();
		}

		return resource;
	}

	public String toString() {
		return getClass().getName() + ":" + getName();
	}
}
