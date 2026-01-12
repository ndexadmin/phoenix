package com.ndexsystems.phoenix.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ndexsystems.phoenix.dto.enume.UserStatus;

import lombok.Data;

@Data
public class UserDTO {
	private BigDecimal organizationUnitKey;
	private String email;
	private String firstName;
	private String id;
	private Short institutionDisclaimerAck;
	private String investorId;
	private LocalDateTime lastLoginDate;
	private String lastName;
	private String loginId;
	private Short marketContractAck;
	private Integer maximumNumberOfItems;
	private String password;
	private Short passwordChanged;
	private Short hasPhone3;
	private String phone3Extension;
	private String phone3Number;
	private String phone3Origin;
	private String phone3Type;
	private Short hasPhone4;
	private String phone4Extension;
	private String phone4Number;
	private String phone4Origin;
	private String phone4Type;
	private Integer numberOfInvalidLogins;
	private NdexEnumerationElement type;
	private UserStatus status;
	private String salt;
	private String preferredLanguage;
	private LocalDateTime lastPasswordChangeDate;	
	private String country;
}
