package com.ndexsystems.phoenix.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.ndexsystems.phoenix.AbstractTenantJpaTestSupport;
import com.ndexsystems.phoenix.entities.AuditItem;
import com.ndexsystems.phoenix.entities.AuditItemSpRow;
import org.springframework.beans.factory.annotation.Value;

@SpringBootTest
@ActiveProfiles("test")
class AuditItemRepositoryTest extends AbstractTenantJpaTestSupport {

	@Autowired
	private AuditItemRepository auditItemRepository;

	@Autowired
	private AuditItemSpRowRepository auditItemSpRowRepository;

	 @Value("${phoenix.test.org-unit-id}")
	private String orgUnitId;
	private static final Integer CURSOR_START = 1;
	private static final Integer RANGE = 200;

	private LocalDateTime startDate;
	private LocalDateTime endDate;

	private String selUserId;
	private String selCategoryAny;
	private String selImportanceAny;

	@BeforeEach
	void setup() {
		startDate = LocalDateTime.of(2025, 12, 19, 0, 0, 0);
		endDate = LocalDateTime.of(2025, 12, 19, 23, 59, 59);

		selUserId = "%";
		selCategoryAny = "%";
		selImportanceAny = "%";
	}

	@Transactional
	@Test
	void sp_findByImportanceTest() {
		List<AuditItemSpRow> results = auditItemSpRowRepository.findByImportance(CURSOR_START, RANGE, orgUnitId,
				selUserId, selCategoryAny, selImportanceAny, startDate, endDate, null, null, "en");

		assertThat(results).isNotNull();
		assertThat(results.size()).isLessThanOrEqualTo(RANGE);
		assertFirstRowLooksValid(results);
	}

	 @Test
	    void saveAuditItem() {
		 
		 BigDecimal maxId = auditItemRepository.findMaxObjectId();
		    BigDecimal nextId = maxId.add(BigDecimal.ONE);

		    AuditItem saved = null;

		    try {
		        AuditItem item = new AuditItem();
		        item.setOBJECT_ID(nextId);                 // MAX + 1
		        item.setInstitution_Key(null);
		        item.setStemLog_Key(null);
		        item.setCategory("ACSF");
		        item.setNDate(LocalDateTime.now());
		        item.setEnglishDescription("Manual edit (test)");
		        item.setFrenchDescription("Modification manuelle (test)");
		        item.setImportance("0");
		        item.setUserId("sysadmin");

		        saved = auditItemRepository.save(item);

		        assertThat(saved).isNotNull();
		        assertThat(saved.getOBJECT_ID()).isEqualTo(nextId);
		        assertThat(saved.getOBJECT_TIMESTAMP()).isNotNull();
		        assertThat(saved.getOBJECT_COUNTER()).isEqualTo(1);
		        assertThat(saved.getCategory()).isEqualTo("ACSF");
		        assertThat(saved.getUserId()).isEqualTo("sysadmin");

		    } finally {
		        if (saved != null && saved.getOBJECT_ID() != null) {
		            auditItemRepository.deleteById(saved.getOBJECT_ID());
		        }
		    }
	    }

	private static void assertFirstRowLooksValid(List<AuditItemSpRow> results) {
		if (results.isEmpty()) {
			return;
		}
		AuditItemSpRow first = results.get(0);
		assertThat(first.getOBJECT_ID()).isNotNull();
		assertThat(first.getCategory()).isNotNull();
		assertThat(first.getImportance()).isNotNull();
		assertThat(first.getUserId()).isNotNull();
		assertThat(first.getNDate()).isNotNull();
	}
}
