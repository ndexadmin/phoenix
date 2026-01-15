package com.ndexsystems.phoenix;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.ndexsystems.phoenix.config.TenantContext;

@ActiveProfiles("test")
public abstract class AbstractTenantJpaTestSupport {

	protected static final String FIRM_ID = "capstoneinvestmentgroup";

	@BeforeTransaction
	void setTenantBeforeTx() {
		TenantContext.setTenant(FIRM_ID);
	}

	@AfterTransaction
	void clearTenantAfterTx() {
		TenantContext.clear();
	}

	@BeforeEach
	void ensureTenantForNonTxTests() {
		if (TenantContext.getTenant() == null) {
			TenantContext.setTenant(FIRM_ID);
		}
	}

	@AfterEach
	void cleanupAfterEach() {
		TenantContext.clear();
	}
}
