package com.ndexsystems.phoenix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.time.Duration;
import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // -------------------------
    // Locale Resolver
    // -------------------------
    @Bean
    public LocaleResolver localeResolver() {
        var clr = new CookieLocaleResolver();
        clr.setDefaultLocale(Locale.ENGLISH);
        clr.setCookieHttpOnly(true);
        clr.setCookieSecure(false);
        clr.setCookiePath("/");
        clr.setCookieMaxAge(Duration.ofDays(7));
        return clr;
    }

    // -------------------------
    // Changer la langue via ?lang=fr
    // -------------------------
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    // -------------------------
    // Messages i18n
    // -------------------------
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms =
                new ReloadableResourceBundleMessageSource();
        ms.setBasenames(
                "classpath:messages/messages"
        );

        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
