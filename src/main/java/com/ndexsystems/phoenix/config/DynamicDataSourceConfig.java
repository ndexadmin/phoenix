package com.ndexsystems.phoenix.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableConfigurationProperties(MultiDbProperties.class)
@RequiredArgsConstructor
public class DynamicDataSourceConfig {

	private final DynamicRoutingDataSource routing;
	private final MultiDbProperties dbProps;

	@PostConstruct
	public void initDefaultTenant() {
		String defaultTenant = dbProps.getDefaultDb();

		if (defaultTenant == null) {
			throw new IllegalStateException("databases.default-db est manquant dans test.properties !");
		}

		TenantContext.setTenant(defaultTenant);
	}

	@Bean
	public DataSource dataSource() {
		return routing;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

		HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

		factory.setPackagesToScan("com.ndexsystems.phoenix.entities");
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(vendor);

		return factory;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
