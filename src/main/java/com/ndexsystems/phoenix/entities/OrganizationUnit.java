package com.ndexsystems.phoenix.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "OrganizationUnit")
public class OrganizationUnit {

    @Id
    private BigDecimal OBJECT_ID;

    private BigDecimal OBJECT_TIMESTAMP;

    private Integer OBJECT_COUNTER;

    private BigDecimal OrganizationUnit_Key;

    private String id;

    private String nName;

    private String onlineInstitutionDiscl;

    private BigDecimal BatchTradeClearing_Key;

    private Integer numberOfAccountOpened;

    private String propertyHolder;

   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "OrganizationUnit_Key",
        referencedColumnName = "OBJECT_ID",
        insertable = false,
        updatable = false
    )
    private OrganizationUnit parentOrganizationUnit;
}
