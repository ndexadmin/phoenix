package com.ndexsystems.phoenix.views;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ndexsystems.phoenix.dto.NdexEnumerationElement;
import com.ndexsystems.phoenix.dto.enume.UserStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserView {
	private String id;
	private BigDecimal organizationUnitKey;
	private String email;
	private String firstName;

	private LocalDateTime lastLoginDate;
	private String lastName;
	private String loginId;
	private NdexEnumerationElement type;
	private UserStatus status;
	private String country;
}