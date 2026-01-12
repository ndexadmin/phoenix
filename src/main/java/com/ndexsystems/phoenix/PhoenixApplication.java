package com.ndexsystems.phoenix;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ndexsystems.phoenix.config.TenantContext;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class)
public class PhoenixApplication {
	
	@Value("${databases.defaultDb}")
	private String defaultTenant;

	@PostConstruct
	public void init() {
		TenantContext.setTenant(defaultTenant);
	}

	public static void main(String[] args) {
		SpringApplication.run(PhoenixApplication.class, args);
	}
}