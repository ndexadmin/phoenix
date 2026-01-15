package com.ndexsystems.phoenix.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.ndexsystems.phoenix.entities.OrganizationUnit;
import com.ndexsystems.phoenix.views.OrganizationUnitView;

class OrganizationUnitMapperEntityToViewTest {

    private final OrganizationUnitMapperEntityToView mapper =
            Mappers.getMapper(OrganizationUnitMapperEntityToView.class);
    @Test
    void toViewTest() {
        // GIVEN
        OrganizationUnit entity = new OrganizationUnit();
        entity.setObjectId(BigDecimal.valueOf(65));
        entity.setOrganizationUnitKey(BigDecimal.valueOf(999));
        entity.setId("1T");
        entity.setNName("Root Organization");
        entity.setOnlineInstitutionDiscl("discl");
        entity.setBatchTradeClearingKey(BigDecimal.valueOf(123));
        entity.setNumberOfAccountOpened(7);
        entity.setPropertyHolder("Y");

        // WHEN
        OrganizationUnitView view = mapper.toView(entity);

        // THEN
        assertThat(view).isNotNull();

       
        assertThat(view.getOrganizationUnitKey()).isEqualTo(entity.getOrganizationUnitKey());
        assertThat(view.getId()).isEqualTo(entity.getId());
        assertThat(view.getNName()).isEqualTo(entity.getNName());
        assertThat(view.getOnlineInstitutionDiscl()).isEqualTo(entity.getOnlineInstitutionDiscl());
        assertThat(view.getBatchTradeClearingKey()).isEqualTo(entity.getBatchTradeClearingKey());
        assertThat(view.getNumberOfAccountOpened()).isEqualTo(entity.getNumberOfAccountOpened());
        assertThat(view.getPropertyHolder()).isEqualTo(entity.getPropertyHolder());
        assertThat(view.getParentOrganizationUnit()).isNull();
    }

}
