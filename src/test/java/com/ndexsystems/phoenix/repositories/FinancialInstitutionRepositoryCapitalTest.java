package com.ndexsystems.phoenix.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ndexsystems.phoenix.AbstractTenantJpaTestSupport;
import com.ndexsystems.phoenix.entities.FinancialInstitution;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class FinancialInstitutionRepositoryCapitalTest extends AbstractTenantJpaTestSupport {

	@Autowired
	private FinancialInstitutionRepository repository;

	@Test
	void testStoredProcedureCall() {

		FinancialInstitution fi = repository.loadFinancialInstitution();

		assertThat(fi).as("La procédure n'a retourné aucun FinancialInstitution").isNotNull();

		assertThat(fi.getObjectId()).as("OBJECT_ID ne doit pas être null").isNotNull();

		assertThat(fi.getEnglishDescription()).as("englishDescription ne doit pas être null").isNotNull();

		assertThat(fi.getLocalCurrencyName()).as("localCurrencyName doit contenir une valeur (CAD, USD, etc.)")
				.isNotBlank();

	}
}
