package com.ndexsystems.phoenix.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ndexsystems.phoenix.dto.NdexEnumeration;
import com.ndexsystems.phoenix.dto.NdexEnumerations;
import com.ndexsystems.phoenix.dto.UserLanguageResources;

public class LanguageResourceServiceTest {

	private LanguageResourceService service;

	@BeforeEach
	void setUp() {
		service = new LanguageResourceService();
	}

	@Test
	void testBackdoorBundleFr() {
		UserLanguageResources resources = service.load("fr");

		ResourceBundle backdoor = resources.backdoorBundle();
		assertNotNull(backdoor, "Backdoor FR bundle must not be null");

		assertEquals("Toutes les catégories", backdoor.getString("all.categories"),
				"FR backdoor bundle should return correct translation");
	}

	@Test
	void testBackdoorBundleEn() {
		UserLanguageResources resources = service.load("en");

		ResourceBundle backdoor = resources.backdoorBundle();
		assertNotNull(backdoor, "Backdoor EN bundle must not be null");

		assertEquals("All categories", backdoor.getString("all.categories"),
				"EN backdoor bundle should return correct translation");
	}

	@Test
	void testNdexEnumerationsFr() {
		UserLanguageResources resources = service.load("fr");

		NdexEnumerations enums = resources.ndexEnums();
		assertNotNull(enums, "NdexEnumerations FR must not be null");

		assertNotNull(enums.get("usertype"), "Ndex enumerations FR should contain 'usertype'");
	}

	@Test
	void testNdexEnumerationsEn() {
		UserLanguageResources resources = service.load("en");

		NdexEnumerations enums = resources.ndexEnums();
		assertNotNull(enums, "NdexEnumerations EN must not be null");

		assertNotNull(enums.get("usertype"), "Ndex enumerations EN should contain 'usertype'");
	}

	@Test
	void testEquateFr() {
		UserLanguageResources resources = service.load("fr");

		ResourceBundle equate = resources.equate();
		assertNotNull(equate, "equate EN bundle must not be null");

		assertEquals("Encaisse en date de règlement",
				equate.getString("reportfield.settlementDateCashBalance.description"),
				"FR equate bundle should return correct translation");
	}

	@Test
	void testEquateEnglish() {
		UserLanguageResources resources = service.load("en");

		ResourceBundle equate = resources.equate();
		assertNotNull(equate, "equate EN bundle must not be null");

		assertEquals("Income Frequency", equate.getString("datacollectingfield.incomeFrequency.description"),
				"EN equate bundle should return correct translation");
	}

	@Test
	void testNdexEnumerationsImportanceFr() {
	    UserLanguageResources resources = service.load("fr");

	    NdexEnumerations enums = resources.ndexEnums();
	    NdexEnumeration importance = enums.get("importance");
	    assertNotNull(resources);
	    assertNotNull(enums);
	    assertNotNull(importance);


	    assertEquals(3, importance.getAll().size(), "Importance doit contenir 3 valeurs");

	}


	@Test
	void testNdexEnumerationsImportanceEn() {
		 UserLanguageResources resources = service.load("en");

		    NdexEnumerations enums = resources.ndexEnums();
		    NdexEnumeration importance = enums.get("importance");
		    assertNotNull(resources);
		    assertNotNull(enums);
		    assertNotNull(importance);


		    assertEquals(3, importance.getAll().size(), "Importance doit contenir 3 valeurs");
	}
}
