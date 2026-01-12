package com.ndexsystems.phoenix.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.context.MessageSource;

public final class Utils {

    private Utils() {
    }

    public static String orWildcard(String value) {
        return (value == null || value.isBlank()) ? "%" : value;
    }

    public static String auditItemDescription(
            MessageSource messageSource,
            Locale locale,
            String loginId,
            String firstName,
            String lastName,
            LocalDateTime loginDate
    ) {
        String formattedDate =
                DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss", locale)
                                 .format(loginDate);

        return messageSource.getMessage(
                "audit.login.success",
                new Object[]{ loginId, firstName, lastName, formattedDate },
                locale
        );
    }
}
