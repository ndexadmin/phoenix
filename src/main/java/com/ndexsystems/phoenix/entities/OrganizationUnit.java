package com.ndexsystems.phoenix.entities;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "OrganizationUnit")
public class OrganizationUnit {

    @Id
    @Column(name = "OBJECT_ID")
    private BigDecimal objectId;

    @Column(name = "OBJECT_TIMESTAMP", nullable = false)
    private BigDecimal objectTimestamp;

    @Column(name = "OBJECT_COUNTER", nullable = false)
    private Integer objectCounter;

    // FK vers le parent
    @Column(name = "OrganizationUnit_Key")
    private BigDecimal organizationUnitKey;

    @Column(name = "id")
    private String id;

    @Column(name = "nName")
    private String nName;

    @Column(name = "onlineInstitutionDiscl")
    private String onlineInstitutionDiscl;

    @Column(name = "BatchTradeClearing_Key")
    private BigDecimal batchTradeClearingKey;

    @Column(name = "numberOfAccountOpened")
    private Integer numberOfAccountOpened;

    @Column(name = "propertyHolder")
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
