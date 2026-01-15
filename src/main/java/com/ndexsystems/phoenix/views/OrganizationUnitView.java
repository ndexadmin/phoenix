package com.ndexsystems.phoenix.views;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrganizationUnitView {

    private BigDecimal organizationUnitKey;
    private String id;
    private String nName;
    private String onlineInstitutionDiscl;
    private BigDecimal batchTradeClearingKey;
    private Integer numberOfAccountOpened;
    private String propertyHolder;
    private OrganizationUnitView parentOrganizationUnit;
}
