package com.ndexsystems.phoenix.mapper;

import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ndexsystems.phoenix.dto.UserLanguageResources;
import com.ndexsystems.phoenix.dto.enume.Importance;
import com.ndexsystems.phoenix.entities.AuditItemSpRow;
import com.ndexsystems.phoenix.util.AuditItemCategory;
import com.ndexsystems.phoenix.util.UserContext;
import com.ndexsystems.phoenix.views.AuditItemView;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class AuditItemMapperEntityToView {


	@Mapping(target = "category", expression = "java(resolveCategory(entity.getCategory()))")
	@Mapping(target = "userId", expression = "java(entity.getUserId())")
	@Mapping(target = "description", expression = "java(resolveDescription(entity, locale))")
	@Mapping(target = "importance", expression = "java(resolveImportance(entity.getImportance(), locale))")
	public abstract AuditItemView toView(AuditItemSpRow entity, String locale);

	protected String resolveDescription(AuditItemSpRow entity, String locale) {
		if (entity == null) {
			return null;
		}

		if ("fr".equalsIgnoreCase(locale) && entity.getFrenchDescription() != null
				&& !entity.getFrenchDescription().isBlank()) {
			return entity.getFrenchDescription();
		}
		return entity.getEnglishDescription();
	}

	protected String resolveImportance(String importanceId, String locale) {
		if (importanceId == null) {
			return null;
		}
		return Importance.fromPersistentId(importanceId).getLabel(locale);
	}
	
	protected String resolveCategory(String category) {
	    if (category == null) {
	        throw new NoSuchElementException(
	            "null is not a valid persistentId for AuditItemCategory"
	        );
	    }

	    AuditItemCategory cat =
	            AuditItemCategory.getElementFromPersistentId(category);
	    UserLanguageResources resources = UserContext.get();
		ResourceBundle equate = resources.equate();

	    return equate.getString(cat.getDescriptionKey());
	}
}