package com.ndexsystems.phoenix.config;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private final MultiDbProperties dbProps;

    private final Map<String, DataSource> cache = new HashMap<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getTenant();
    }

    @Override
    protected DataSource determineTargetDataSource() {

        String tenant = TenantContext.getTenant();

        if (tenant == null) {
            throw new IllegalStateException("Aucun tenant défini !");
        }

      
        if (cache.containsKey(tenant)) {
            return cache.get(tenant);
        }

        MultiDbProperties.DbConfig cfg = dbProps.getConfigs().get(tenant);

        if (cfg == null) {
            throw new IllegalArgumentException("Tenant inconnu : " + tenant);
        }

        DataSource ds = DataSourceBuilder.create()
                .url(cfg.getUrl())
                .username(cfg.getUsername())
                .password(cfg.getPassword())
                .driverClassName(cfg.getDriver())
                .build();

        cache.put(tenant, ds);

        return ds;
    }

    
    @Override
    public void afterPropertiesSet() {
      
    }
}
