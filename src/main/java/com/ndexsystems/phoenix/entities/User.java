	package com.ndexsystems.phoenix.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User_Table")
public class User {

	@Id
	@Column(name = "OBJECT_ID", nullable = false, precision = 19, scale = 0)
	private BigDecimal objectId;

	@Column(name = "OBJECT_TIMESTAMP", nullable = false, precision = 19, scale = 0)
	private BigDecimal objectTimestamp;

	@Column(name = "OBJECT_COUNTER", nullable = false)
	private Integer objectCounter;

	@Column(name = "OrganizationUnit_Key", precision = 19, scale = 0)
	private BigDecimal organizationUnitKey;

	@Column(name = "email", length = 88)
	private String email;

	@Column(name = "firstName", length = 32)
	private String firstName;

	@Column(name = "id", length = 13)
	private String id;

	@Column(name = "institutionDisclaimerAck")
	private Short institutionDisclaimerAck;

	@Column(name = "investorId", length = 20)
	private String investorId;

	@Column(name = "lastLoginDate")
	private LocalDateTime lastLoginDate;

	@Column(name = "lastName", length = 32)
	private String lastName;

	@Column(name = "loginId", length = 45)
	private String loginId;

	@Column(name = "marketContractAck")
	private Short marketContractAck;

	@Column(name = "maximumNumberOfItems")
	private Integer maximumNumberOfItems;

	@Column(name = "password", length = 44)
	private String password;

	@Column(name = "passwordChanged")
	private Short passwordChanged;

	@Column(name = "hasPhone3")
	private Short hasPhone3;

	@Column(name = "phone3_extension", length = 6)
	private String phone3Extension;

	@Column(name = "phone3_number", length = 24)
	private String phone3Number;

	@Column(name = "phone3_origin", length = 5)
	private String phone3Origin;

	@Column(name = "phone3_type", length = 5)
	private String phone3Type;

	@Column(name = "hasPhone4")
	private Short hasPhone4;

	@Column(name = "phone4_extension", length = 6)
	private String phone4Extension;

	@Column(name = "phone4_number", length = 24)
	private String phone4Number;

	@Column(name = "phone4_origin", length = 5)
	private String phone4Origin;

	@Column(name = "phone4_type", length = 5)
	private String phone4Type;

	@Column(name = "nType", length = 5)
	private String ntype;

	@Column(name = "propertyHolder", length = 64)
	private String propertyHolder;

	@Column(name = "numberOfInvalidLogins")
	private Integer numberOfInvalidLogins;

	@Column(name = "status", length = 5)
	private String status;

	@Column(name = "BatchTradeClearing_Key", precision = 19, scale = 0)
	private BigDecimal batchTradeClearingKey;

	@Column(name = "salt", length = 32)
	private String salt;

	@Column(name = "preferredLanguage", length = 5)
	private String preferredLanguage;

	@Column(name = "lastPasswordChangeDate")
	private LocalDateTime lastPasswordChangeDate;

	@Column(name = "country", length = 5)
	private String country;

	@Column(name = "numberOfDayBeforeWithdrawal")
	private Integer numberOfDayBeforeWithdrawal;


}
