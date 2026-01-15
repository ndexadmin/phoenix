package com.ndexsystems.phoenix.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ndexsystems.phoenix.dto.UserLanguageResources;
import com.ndexsystems.phoenix.dto.enume.UserStatus;
import com.ndexsystems.phoenix.entities.AuditItem;
import com.ndexsystems.phoenix.entities.AuditItemSpRow;
import com.ndexsystems.phoenix.mapper.AuditItemMapperEntityToView;
import com.ndexsystems.phoenix.mapper.FinancialInstitutionDTOMapper;
import com.ndexsystems.phoenix.repositories.AuditItemRepository;
import com.ndexsystems.phoenix.repositories.AuditItemSpRowRepository;
import com.ndexsystems.phoenix.repositories.FCPropertiesRepository;
import com.ndexsystems.phoenix.repositories.FinancialInstitutionRepository;
import com.ndexsystems.phoenix.util.Constants;
import com.ndexsystems.phoenix.util.UserContext;
import com.ndexsystems.phoenix.util.Utils;
import com.ndexsystems.phoenix.views.AuditItemView;
import com.ndexsystems.phoenix.views.ManualAuditLogView;
import com.ndexsystems.phoenix.views.UserView;

@ExtendWith(MockitoExtension.class)
class ActivityLogServiceTest {

	@Mock
	private FCPropertiesRepository fCPropertiesRepository;
	@Mock
	private FinancialInstitutionRepository financialInstitutionRepository;

	@Mock
	private AuditItemRepository auditItemRepository;
	@Mock
	private AuditItemSpRowRepository auditItemSpRowRepository;

	@Mock
	private FinancialInstitutionDTOMapper mapper;
	@Mock
	private AuditItemMapperEntityToView mapperView;

	@InjectMocks
	private ActivityLogService activityLogService;


	@AfterEach
	void cleanup() {
		UserContext.clear();
	}

	@Test
	void testSetUpCategoriesEn() {
	    UserLanguageResources resources = mock(UserLanguageResources.class);
	    ResourceBundle backdoor = mock(ResourceBundle.class);
	    ResourceBundle equate = mock(ResourceBundle.class);

	    when(resources.backdoorBundle()).thenReturn(backdoor);
	    when(resources.equate()).thenReturn(equate);

	    when(backdoor.getString(Constants.ALL_CATEGORIES)).thenReturn("All categories");
	    when(equate.getString(anyString())).thenAnswer(inv -> inv.getArgument(0, String.class));

	    UserContext.set(resources);

	    when(fCPropertiesRepository.loadFirmProperties()).thenReturn(List.of());

	    Map<String, String> categories =
	            activityLogService.setUpCategories("sysadmin", "capstoneinvestmentgroup");

	    assertThat(categories).isNotNull();
	    assertThat(categories).containsKey("All");
	    assertThat(categories.get("All")).isNotBlank();
	}


	@Test
	void testSetUpImportanceEn() {
		// GIVEN
		UserLanguageResources resources = mock(UserLanguageResources.class);
		ResourceBundle backdoor = mock(ResourceBundle.class);

		when(resources.backdoorBundle()).thenReturn(backdoor);
		when(backdoor.getString("all.levels")).thenReturn("All levels");

		// ndexEnums()
		var ndex = mock(com.ndexsystems.phoenix.dto.NdexEnumerations.class);
		var importanceEnum = mock(com.ndexsystems.phoenix.dto.NdexEnumeration.class);

		when(resources.ndexEnums()).thenReturn(ndex);
		when(ndex.get("Importance")).thenReturn(importanceEnum);

		// elements
		var e0 = mock(com.ndexsystems.phoenix.dto.NdexEnumerationElement.class);
		var e1 = mock(com.ndexsystems.phoenix.dto.NdexEnumerationElement.class);
		var e2 = mock(com.ndexsystems.phoenix.dto.NdexEnumerationElement.class);

		when(e0.backOfficeId()).thenReturn("0");
		when(e0.value()).thenReturn("Information");

		when(e1.backOfficeId()).thenReturn("1");
		when(e1.value()).thenReturn("Warning");

		when(e2.backOfficeId()).thenReturn("2");
		when(e2.value()).thenReturn("Error");

		when(importanceEnum.getAll()).thenReturn(List.of(e0, e1, e2));

		UserContext.set(resources);

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
		String locale = "en";

		AuditItemSpRow row = mock(AuditItemSpRow.class);
		when(auditItemSpRowRepository.findByImportance(anyInt(), anyInt(), anyString(), anyString(), anyString(),
				anyString(), any(LocalDateTime.class), any(LocalDateTime.class), any(), any(), any()))
				.thenReturn(List.of(row));

		AuditItemView view = new AuditItemView();
		when(mapperView.toView(row, locale)).thenReturn(view);

		// WHEN
		List<AuditItemView> result = activityLogService.lookupAuditTrailView("21", Utils.orWildcard(null),
				Utils.orWildcard(null), Utils.orWildcard(null), LocalDate.now().atStartOfDay(),
				LocalDate.now().atTime(23, 59, 59), 1, 200, null, null, locale);

		// THEN
		assertNotNull(result);
		assertThat(result).hasSize(1);
		verify(auditItemSpRowRepository, times(1)).findByImportance(eq(1), eq(200), eq("21"), anyString(), anyString(),
				anyString(), any(LocalDateTime.class), any(LocalDateTime.class), isNull(), isNull(), eq(locale));
	}

	@Test
	void seachAuditTrailView() {
		// GIVEN
		String locale = "en";

		AuditItemSpRow row = mock(AuditItemSpRow.class);
		when(auditItemSpRowRepository.findByDescDate(anyInt(), anyInt(), anyString(), anyString(), anyString(),
				anyString(), any(LocalDateTime.class), any(LocalDateTime.class), any(), any(), any()))
				.thenReturn(List.of(row));

		AuditItemView view = new AuditItemView();
		when(mapperView.toView(row, locale)).thenReturn(view);

		// WHEN
		List<AuditItemView> result = activityLogService.seachAuditTrailView("21", "%", "%", "%",
				LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59, 59), 1, 200, null, null, locale);

		// THEN
		assertNotNull(result);
		assertThat(result).hasSize(1);
		verify(auditItemSpRowRepository, times(1)).findByDescDate(eq(1), eq(200), eq("21"), anyString(), anyString(),
				anyString(), any(LocalDateTime.class), any(LocalDateTime.class), isNull(), isNull(), eq(locale));
	}

	@Test
	void testAddManualAuditItemSave_mocked_same_asserts() {
		// GIVEN
		UserView user = UserView.builder().organizationUnitKey(BigDecimal.valueOf(73)).email("john.doe@test.com")
				.firstName("System").lastName("Administrator").loginId("sysadmin").status(UserStatus.ACCESSALLOWED)
				.country("CA").build();

		ManualAuditLogView manualLog = new ManualAuditLogView();
		manualLog.setActivityDate(LocalDate.now().toString());
		manualLog.setImportance("0");
		manualLog.setActivityType("ACSF");
		manualLog.setUserId(user.getLoginId());
		manualLog.setEnglishDescription("Successful Test");
		manualLog.setFrenchDescription("Connexion Test");

		// maxId -> nextId = 101
		when(auditItemRepository.findMaxObjectId()).thenReturn(BigDecimal.valueOf(100));

		// on capture l'entité sauvée
		ArgumentCaptor<AuditItem> captor = ArgumentCaptor.forClass(AuditItem.class);
		when(auditItemRepository.save(any(AuditItem.class))).thenAnswer(inv -> inv.getArgument(0));

		// WHEN
		BigDecimal savedId = activityLogService.addManualAuditItem(user, manualLog);

		// THEN (mêmes asserts fonctionnels)
		assertThat(savedId).isNotNull().isEqualTo(BigDecimal.valueOf(101));

		verify(auditItemRepository).save(captor.capture());
		AuditItem saved = captor.getValue();

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
	}
}
