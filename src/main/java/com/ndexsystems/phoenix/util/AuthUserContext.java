package com.ndexsystems.phoenix.util;

import com.ndexsystems.phoenix.views.UserView;

public final class AuthUserContext {

    private static final ThreadLocal<UserView> CURRENT_USER = new ThreadLocal<>();

    private AuthUserContext() {}

    public static void set(UserView user) {
        CURRENT_USER.set(user);
    }

    public static UserView get() {
        return CURRENT_USER.get();
    }

    public static void clear() {
        CURRENT_USER.remove();
    }
}
