package com.ndexsystems.phoenix.util;

import org.springframework.core.Ordered;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import com.ndexsystems.phoenix.config.TenantContext;

public class TenantTestExecutionListener extends AbstractTestExecutionListener {

    @Override
    public int getOrder() {
       
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void beforeTestMethod(TestContext testContext) {
     
        TenantContext.setTenant(AbstractTenantDbTest.FIRM_ID);
    }

    @Override
    public void afterTestMethod(TestContext testContext) {
        TenantContext.clear();
    }
}
