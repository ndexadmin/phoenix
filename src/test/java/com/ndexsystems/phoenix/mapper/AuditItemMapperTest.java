package com.ndexsystems.phoenix.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ndexsystems.phoenix.dto.UserLanguageResources;
import com.ndexsystems.phoenix.entities.AuditItemSpRow;
import com.ndexsystems.phoenix.services.LanguageResourceService;
import com.ndexsystems.phoenix.util.UserContext;
import com.ndexsystems.phoenix.views.AuditItemView;

@SpringBootTest
@ActiveProfiles("test")
class AuditItemMapperTest {

	private final AuditItemMapperEntityToView mapper = Mappers.getMapper(AuditItemMapperEntityToView.class);

	private AuditItemSpRow auditItemEntity;
	private LocalDateTime date;
	@Autowired
	private LanguageResourceService service;

	@BeforeEach
	void setUp() {
		date = LocalDateTime.of(2025, 12, 19, 17, 50, 16);

		auditItemEntity = new AuditItemSpRow();
		auditItemEntity.setOBJECT_ID(BigDecimal.valueOf(194));
		auditItemEntity.setCategory("ACSF");
		auditItemEntity
				.setEnglishDescription("Successful Login : sysadmin - System Administrator - 2025/12/19 - 17:50:16");
		auditItemEntity
				.setFrenchDescription("Connexion réussie : sysadmin – Administrateur système – 2025/12/19 – 17:50:16");
		auditItemEntity.setImportance("0");
		auditItemEntity.setUserId("sysadmin");
		auditItemEntity.setNDate(date);
	}

	@Test
	void toViewTestfr() {

		// WHEN
		UserLanguageResources resources = service.load("fr");
		UserContext.set(resources);
		AuditItemView view = mapper.toView(auditItemEntity, "fr");
		// FR attendu
		assertThat(view.getDescription())
				.isEqualTo("Connexion réussie : sysadmin – Administrateur système – 2025/12/19 – 17:50:16");
		// THEN
		assertThat(view.getCategory()).isEqualTo("Accès (utilisateur de la firme)");
		assertThat(view.getUserId()).isEqualTo(auditItemEntity.getUserId());
		assertThat(view.getNDate()).isEqualTo(auditItemEntity.getNDate());

	}

	@Test
	void toViewTestEn() {

		// WHEN
		UserLanguageResources resources = service.load("en");
		UserContext.set(resources);
		AuditItemView view = mapper.toView(auditItemEntity, "en");

		// THEN
		assertThat(view).isNotNull();

		// EN attendu
		assertThat(view.getDescription())
				.isEqualTo("Successful Login : sysadmin - System Administrator - 2025/12/19 - 17:50:16");
	}

}
