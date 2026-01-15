package com.ndexsystems.phoenix.controllers;

import org.springframework.context.MessageSource;

import com.ndexsystems.phoenix.config.TenantContext;
import com.ndexsystems.phoenix.views.ContextView;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseController {

    protected static final String SESSION_CONTEXT_KEY = "CONTEXT";

    private final HttpSession session;
    protected final MessageSource messageSource;

    
    protected ContextView requireContext(String product, String locale) {
        ContextView ctx = (ContextView) session.getAttribute(SESSION_CONTEXT_KEY);

        if (ctx == null || ctx.getUser() == null) {
            throw new UnauthenticatedException(buildLoginRedirect(product, locale));
        }

        return ctx;
    }

    private String buildLoginRedirect(String product, String locale) {
        return "redirect:/" + product + "/" + locale + "/" + TenantContext.getTenant() + "/login";
    }

    /**
     * Exception interne utilisée UNIQUEMENT par BaseController.
     * Le @ControllerAdvice la capture et retourne le redirect.
     */
    public static final class UnauthenticatedException extends RuntimeException {
        private final String redirect;

        public UnauthenticatedException(String redirect) {
            super("Unauthenticated");
            this.redirect = redirect;
        }

        public String getRedirect() {
            return redirect;
        }
    }
}
