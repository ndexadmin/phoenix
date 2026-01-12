package com.ndexsystems.phoenix.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;


@Data
@ConfigurationProperties(prefix = "databases")
public class MultiDbProperties {

       private String defaultDb;

    
    private Map<String, DbConfig> configs;

    @Data
    public static class DbConfig {
        private String id;
        private String url;
        private String username;
        private String password;
        private String driver;
    }
}