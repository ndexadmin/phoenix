package com.ndexsystems.phoenix.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialInstitutionDTO implements Serializable {

	private static final long serialVersionUID = -6733248756697751223L;

	private Date applicationStartupDate;
	private Date lastFeedDate;
	private String sogoDomainName;
	private String firmId;
	private String firmName;
	private String firmFrenchName;
	private String communityFirmId;
	private String encryptionKey;
	private String propertyHolder;
	private boolean isAmericanFirm;
	private boolean isCanadianFirm;
	private boolean summarizedActivities;
	private String sogoDomain1;
	private String sogoDomain2;
	private String sogoDomain3;
}
