package com.ndexsystems.phoenix.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ndexsystems.phoenix.config.TenantContext;
import com.ndexsystems.phoenix.dto.UserLanguageResources;
import com.ndexsystems.phoenix.dto.enume.UserStatus;
import com.ndexsystems.phoenix.entities.AuditItem;
import com.ndexsystems.phoenix.repositories.AuditItemRepository;
import com.ndexsystems.phoenix.util.AuthUserContext;
import com.ndexsystems.phoenix.util.UserContext;
import com.ndexsystems.phoenix.util.Utils;
import com.ndexsystems.phoenix.views.AuditItemView;
import com.ndexsystems.phoenix.views.ManualAuditLogView;
import com.ndexsystems.phoenix.views.UserView;

@SpringBootTest
@ActiveProfiles("test")
class ActivityLogServiceTest {

	@Autowired
	private ActivityLogService activityLogService;

	@Autowired
	private AuditItemRepository auditItemRepository;

	@Autowired
	private LanguageResourceService languageResourceService;

	private final String firmId = "gicapitalcorp";

	@BeforeEach
	void setup() {
		TenantContext.setTenant(firmId);
	}

	@AfterEach
	void cleanup() {
		UserContext.clear();
		TenantContext.clear();
	}

	@Test
	void testSetUpCategoriesEn() {

		UserLanguageResources lang = languageResourceService.load("en");
		UserContext.set(lang);

		Map<String, String> categories = activityLogService.setUpCategories("sysadmin", firmId);

		assertThat(categories).as("Le map ne doit pas être null").isNotNull();

		assertThat(categories).as("Le map doit contenir la clé 'All'").containsKey("All");

		assertThat(categories.get("All")).as("'All' doit avoir un label (en)").isNotBlank();
	}

	@Test
	void testSetUpCategoriesFr() {

		UserLanguageResources lang = languageResourceService.load("fr");
		UserContext.set(lang);

		Map<String, String> categories = activityLogService.setUpCategories("sysadmin", firmId);

		assertThat(categories).as("Le map ne doit pas être null").isNotNull();

		assertThat(categories).as("Le map doit contenir la clé 'All'").containsKey("All");
		assertEquals("Toutes les catégories", categories.get("All"));
	}

	@Test
	void testSetUpImportanceFR() {

		// GIVEN
		UserLanguageResources lang = languageResourceService.load("fr");
		UserContext.set(lang);

		// WHEN
		Map<String, String> result = activityLogService.setUpImportance();

		// THEN
		assertNotNull(result);
		assertFalse(result.isEmpty());

		assertThat(result).containsKeys("0", "1", "2", "All");

		assertEquals("Information", result.get("0"));
		assertEquals("Avertissement", result.get("1"));
		assertEquals("Erreur", result.get("2"));
		assertEquals("Tous les niveaux", result.get("All"));
	}

	@Test
	void testSetUpImportanceEn() {

		// GIVEN
		UserLanguageResources lang = languageResourceService.load("en");
		UserContext.set(lang);

		// WHEN
		Map<String, String> result = activityLogService.setUpImportance();

		// THEN
		assertNotNull(result);
		assertFalse(result.isEmpty());

		assertThat(result).containsKeys("0", "1", "2", "All");

		assertEquals("Information", result.get("0"));
		assertEquals("Warning", result.get("1"));
		assertEquals("Error", result.get("2"));
		assertEquals("All levels", result.get("All"));
	}

	@Test
	void lookupAuditTrailView() {

		// GIVEN
		UserLanguageResources lang = languageResourceService.load("en");
		UserContext.set(lang);
		String userId = null;
		String category = null;
		String importance = null;

		TenantContext.setTenant(firmId);

		List<AuditItemView> auditItemViews = activityLogService.lookupAuditTrailView("21", Utils.orWildcard(userId),
				Utils.orWildcard(category), Utils.orWildcard(importance), LocalDate.now().atStartOfDay(),
				LocalDate.now().atTime(23, 59, 59), 1, 200, null, null, null);

		assertNotNull(auditItemViews, "La liste des AuditItemView ne doit pas être null");
	}

	@Test
	void testAddManualAuditItem_savesAndDeletes() {

		// GIVEN
		UserView user = UserView.builder().organizationUnitKey(BigDecimal.valueOf(73)).email("john.doe@test.com")
				.firstName("System").lastName("Administrator").loginId("sysadmin").status(UserStatus.ACCESSALLOWED)
				.country("CA").build();

		AuthUserContext.set(user);

		BigDecimal savedId = null;

		try {
			ManualAuditLogView manualLog = new ManualAuditLogView();
			manualLog.setActivityDate(LocalDate.now().toString());
			manualLog.setImportance("0");
			manualLog.setActivityType("ACSF");
			manualLog.setUserId(user.getLoginId());

			manualLog.setEnglishDescription("Successful Test");

			manualLog.setFrenchDescription("Connexion Test");

			// WHEN
			savedId = activityLogService.addManualAuditItem(user, manualLog);

			// THEN
			assertThat(savedId).isNotNull();

			AuditItem saved = auditItemRepository.findById(savedId).orElse(null);
			assertThat(saved).isNotNull();
			assertThat(saved.getOBJECT_ID()).isEqualTo(savedId);

			assertThat(saved.getInstitution_Key()).isEqualTo(BigDecimal.valueOf(73));
			assertThat(saved.getStemLog_Key()).isNull();

			assertThat(saved.getCategory()).isEqualTo("ACSF");
			assertThat(saved.getImportance()).isEqualTo("0");
			assertThat(saved.getUserId()).isEqualTo("sysadmin");

			assertThat(saved.getNDate()).isNotNull();
			assertThat(saved.getEnglishDescription()).isNotBlank();
			assertThat(saved.getFrenchDescription()).isNotBlank();

			assertThat(saved.getEnglishDescription()).contains("Successful");
			assertThat(saved.getFrenchDescription()).contains("Connexion");

		} finally {
			if (savedId != null) {
				auditItemRepository.deleteById(savedId);
			}
			AuthUserContext.clear();
		}
	}

}
