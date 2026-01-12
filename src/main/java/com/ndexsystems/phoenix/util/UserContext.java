package com.ndexsystems.phoenix.util;

import com.ndexsystems.phoenix.dto.UserLanguageResources;

public class UserContext {

	private static final ThreadLocal<UserLanguageResources> userContext = new ThreadLocal<>();

	public static void set(UserLanguageResources resources) {
		userContext.set(resources);
	}

	public static UserLanguageResources get() {
		return userContext.get();
	}

	public static void clear() {
		userContext.remove();
	}
}
