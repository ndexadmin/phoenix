package com.ndexsystems.phoenix.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.ndexsystems.phoenix.config.TenantContext;

@TestExecutionListeners(
    listeners = {
        DependencyInjectionTestExecutionListener.class,
        TenantTestExecutionListener.class,
        TransactionalTestExecutionListener.class
    },
    mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
public abstract class AbstractTenantDbTest {

    protected static final String FIRM_ID = "gicapitalcorp";

    @BeforeEach
    void setTenant() {
        TenantContext.setTenant(FIRM_ID);
    }

    @AfterEach
    void clearTenant() {
        TenantContext.clear();
    }
}
