package com.ndexsystems.phoenix.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "FinancialInstitution")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialInstitution implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "OBJECT_ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal objectId;

    @Column(name = "OBJECT_TIMESTAMP", nullable = false, precision = 19, scale = 0)
    private BigDecimal objectTimestamp;

    @Column(name = "OBJECT_COUNTER", nullable = false)
    private Integer objectCounter;

    @Column(name = "applicationStartupDate")
    private LocalDateTime applicationStartupDate;

    @Column(name = "lastFeedDate")
    private LocalDateTime lastFeedDate;

    @Column(name = "localCurrencyName", length = 5)
    private String localCurrencyName;

    @Column(name = "nextCustRepId", length = 5)
    private String nextCustRepId;

    @Column(name = "propertyHolder", length = 254)
    private String propertyHolder;

    @Column(name = "highwaterMarkTolerance", precision = 16, scale = 2)
    private BigDecimal highwaterMarkTolerance;

    @Column(name = "communityFirmId", length = 3)
    private String communityFirmId;

    @Column(name = "englishDescription", length = 60)
    private String englishDescription;

    @Column(name = "frenchDescription", length = 60)
    private String frenchDescription;

    @Column(name = "sogoDomain1", length = 64)
    private String sogoDomain1;

    @Column(name = "sogoDomain2", length = 64)
    private String sogoDomain2;

    @Column(name = "sogoDomain3", length = 64)
    private String sogoDomain3;

    @Column(name = "encryptionKey", length = 256)
    private String encryptionKey;
}
