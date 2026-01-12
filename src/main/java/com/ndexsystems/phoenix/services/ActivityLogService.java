package com.ndexsystems.phoenix.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ndexsystems.phoenix.dto.FinancialInstitutionDTO;
import com.ndexsystems.phoenix.dto.FirmPropertiesDTO;
import com.ndexsystems.phoenix.dto.NdexEnumeration;
import com.ndexsystems.phoenix.dto.NdexEnumerationElement;
import com.ndexsystems.phoenix.dto.NdexEnumerations;
import com.ndexsystems.phoenix.dto.UserLanguageResources;
import com.ndexsystems.phoenix.entities.AuditItem;
import com.ndexsystems.phoenix.entities.AuditItemSpRow;
import com.ndexsystems.phoenix.entities.FCProperties;
import com.ndexsystems.phoenix.mapper.AuditItemMapperEntityToView;
import com.ndexsystems.phoenix.mapper.FinancialInstitutionDTOMapper;
import com.ndexsystems.phoenix.repositories.AuditItemRepository;
import com.ndexsystems.phoenix.repositories.AuditItemSpRowRepository;
import com.ndexsystems.phoenix.repositories.FCPropertiesRepository;
import com.ndexsystems.phoenix.repositories.FinancialInstitutionRepository;
import com.ndexsystems.phoenix.util.AuditItemCategory;
import com.ndexsystems.phoenix.util.Constants;
import com.ndexsystems.phoenix.util.UserContext;
import com.ndexsystems.phoenix.views.AuditItemView;
import com.ndexsystems.phoenix.views.ManualAuditLogView;
import com.ndexsystems.phoenix.views.UserView;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityLogService {

	private final FCPropertiesRepository fCPropertiesRepository;
	private final FinancialInstitutionRepository financialInstitutionRepository;

	private final AuditItemRepository auditItemRepository;
	private final AuditItemSpRowRepository auditItemSpRowRepository;
	private final FinancialInstitutionDTOMapper mapper;
	private final AuditItemMapperEntityToView mapperView;

	@Transactional
	public Map<String, String> setUpCategories(String currentUserId, String firmId) {

		FirmPropertiesDTO firmProperties = loadFirmProperties(firmId);
		AuditItemCategory[] auditItemCategories;
		if (firmProperties.isAmericanBehavior()) {

			if (!firmProperties.isTaxLotAccountLevel() && !firmProperties.isTaxLotAccountingOutsideCustodian()
					&& !firmProperties.isTaxLotAccountingAtCustodianLevel()) {

				auditItemCategories = new AuditItemCategory[AuditItemCategory.TAXLOT_DOMAIN.length + 1];

				int index = 0;
				for (AuditItemCategory item : AuditItemCategory.TAXLOT_DOMAIN) {
					auditItemCategories[index++] = item;
				}

				auditItemCategories[index] = AuditItemCategory.TRANSFERACCOUNTDATA;

			} else {
				auditItemCategories = AuditItemCategory.TAXLOT_DOMAIN;
			}

		} else {
			auditItemCategories = AuditItemCategory.STANDARDDISPLAY_DOMAIN;
		}

		boolean taxlotAccounting = firmProperties.isTaxLotAccountingAtCustodianLevel();
		UserLanguageResources resources = UserContext.get();
		ResourceBundle equate = resources.equate();
		Map<String, String> categoryMap = new LinkedHashMap<>();
		categoryMap.put(Constants.ALL, resources.backdoorBundle().getString(Constants.ALL_CATEGORIES));
		FinancialInstitutionDTO financialInstitutionDTO = mapper
				.entityToDTO(financialInstitutionRepository.loadFinancialInstitution());
		for (AuditItemCategory category : auditItemCategories) {
			String id = category.getPersistentId();
			String name = equate.getString(category.getDescriptionKey());

			boolean exclude = AuditItemCategory.INTERNALUSE.getPersistentId().equalsIgnoreCase(id)
					|| AuditItemCategory.MISMATCH.getPersistentId().equalsIgnoreCase(id)
					|| AuditItemCategory.EDITLOG.getPersistentId().equalsIgnoreCase(id)
					|| (AuditItemCategory.HISTORICALPOSITIONBOOKVALUEEDITION.getPersistentId().equalsIgnoreCase(id)
							&& taxlotAccounting)
					|| (AuditItemCategory.COSTBASISEDITION.getPersistentId().equalsIgnoreCase(id) && !taxlotAccounting
							&& !financialInstitutionDTO.isAmericanFirm())
					|| (AuditItemCategory.TAXLOTEDITION.getPersistentId().equalsIgnoreCase(id) && !taxlotAccounting);

			if (!exclude) {
				categoryMap.put(id, name);
			}

			if (currentUserId.equals(Constants.SYSTEM_ADMINISTRATOR)
					&& AuditItemCategory.EDITLOG.getPersistentId().equalsIgnoreCase(id)) {
				// only sysadmin has the right to see edited logs
				categoryMap.put(id, name);
			}

		}

		return categoryMap;
	}

	private FirmPropertiesDTO loadFirmProperties(String firmId) {

		List<FCProperties> rows = fCPropertiesRepository.loadFirmProperties();

		FirmPropertiesDTO dto = new FirmPropertiesDTO(firmId);

		for (FCProperties row : rows) {

			String key = getCollectionKey(row.getName(), row.getPersistentId());
			String value;

			if (row.isBooleanOnly()) {
				Boolean bool = row.getBooleanValue();
				value = (bool != null && bool) ? "true" : "false";
			} else {
				value = row.getStringValue();
			}

			dto.getProperties().put(key, value);
		}

		return dto;
	}

	private String getCollectionKey(String name, String custodianId) {
		return name + "." + custodianId.toUpperCase();
	}

	public Map<String, String> setUpImportance() {
		UserLanguageResources resources = UserContext.get();
		NdexEnumerations ndex = resources.ndexEnums();
		NdexEnumeration importanceEnum = ndex.get("Importance");

		Map<String, String> importanceMap = new TreeMap<>();

		if (importanceEnum != null) {
			for (NdexEnumerationElement element : importanceEnum.getAll()) {

				importanceMap.put(element.backOfficeId(), element.value());

			}
		}

		String allLabel = resources.backdoorBundle().getString("all.levels");

		importanceMap.put("All", allLabel);

		return importanceMap;

	}

	@Transactional
	public List<AuditItemView> lookupAuditTrailView(String organizationUnitKey, String userId, String category,
			String importance, LocalDateTime startDate, LocalDateTime endDate, Integer page1, Integer maxPage,
			String searchDescription, String searchCriteria, String locale) {

		List<AuditItemSpRow> auditItems = auditItemSpRowRepository.findByImportance(page1, maxPage, organizationUnitKey,
				userId, category, importance, startDate, endDate, searchDescription, searchCriteria, locale);

		return auditItems.stream().map(item -> mapperView.toView(item, locale)).toList();
	}

	@Transactional
	public List<AuditItemView> seachAuditTrailView(String organizationUnitKey, String userId, String category,
			String importance, LocalDateTime startDate, LocalDateTime endDate, Integer page1, Integer maxPage,
			String searchDescription, String searchCriteria, String locale) {

		List<AuditItemSpRow> auditItems = auditItemSpRowRepository.findByDescDate(page1, maxPage, organizationUnitKey,
				userId, category, importance, startDate, endDate, searchDescription, searchCriteria, locale);

		return auditItems.stream().map(item -> mapperView.toView(item, locale)).toList();
	}

	public BigDecimal addManualAuditItem(UserView user, ManualAuditLogView manualLog) {

	    LocalDateTime date = LocalDateTime.now();

	    BigDecimal maxId = auditItemRepository.findMaxObjectId();
	    BigDecimal nextId = maxId.add(BigDecimal.ONE);

	    AuditItem item = new AuditItem();
	    item.setOBJECT_ID(nextId);
	    item.setInstitution_Key(user.getOrganizationUnitKey());
	    item.setStemLog_Key(null);

	    item.setCategory("ACSF");
	    item.setImportance("0");

	    item.setNDate(date);
	    item.setUserId(user.getLoginId());

	    item.setEnglishDescription(manualLog.getEnglishDescription());
	    item.setFrenchDescription(manualLog.getFrenchDescription());

	    auditItemRepository.save(item);
		return nextId;
	}

}
