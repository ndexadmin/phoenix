package com.ndexsystems.phoenix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.ndexsystems.phoenix.entities.FinancialInstitution;
import com.ndexsystems.phoenix.views.FinancialInstitutionView;

@Mapper(componentModel = "spring")
public interface FinancialInstitutionViewMapper {

    @Mappings({

        @Mapping(source = "englishDescription", target = "firmName"),
        @Mapping(source = "frenchDescription", target = "firmFrenchName"),
        @Mapping(target = "sogoDomainName", ignore = true),
        @Mapping(target = "firmId", ignore = true),
        @Mapping(target = "isAmericanFirm", ignore = true),
        @Mapping(target = "isCanadianFirm", ignore = true),
        @Mapping(target = "summarizedActivities", ignore = true)
    })
    FinancialInstitutionView entityToViewtoView(FinancialInstitution entity);
}