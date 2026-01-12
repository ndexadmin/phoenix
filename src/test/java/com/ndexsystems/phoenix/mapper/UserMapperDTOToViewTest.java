package com.ndexsystems.phoenix.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.ndexsystems.phoenix.dto.UserDTO;
import com.ndexsystems.phoenix.dto.enume.UserStatus;
import com.ndexsystems.phoenix.views.UserView;

public class UserMapperDTOToViewTest {

	private final UserMapperDTOToView mapper = Mappers.getMapper(UserMapperDTOToView.class);

	@Test
	void toViewTest() {

		// GIVEN
		UserDTO dto = new UserDTO();
		dto.setOrganizationUnitKey(BigDecimal.valueOf(42));
		dto.setEmail("john.doe@test.com");
		dto.setFirstName("John");
		dto.setLastName("Doe");
		dto.setLoginId("jdoe");
		dto.setLastLoginDate(LocalDateTime.of(2024, 1, 10, 8, 30));
		dto.setCountry("CA");
		dto.setStatus(UserStatus.ACCESSALLOWED);
		dto.setPassword("secret");
		dto.setSalt("salt");
		dto.setId("23");

		// WHEN
		UserView view = mapper.toView(dto);

		// THEN
		assertThat(view).isNotNull();

		// champs mappés
		assertThat(view.getId()).isEqualTo(dto.getId());
		assertThat(view.getOrganizationUnitKey()).isEqualTo(dto.getOrganizationUnitKey());
		assertThat(view.getEmail()).isEqualTo(dto.getEmail());
		assertThat(view.getFirstName()).isEqualTo(dto.getFirstName());
		assertThat(view.getLastName()).isEqualTo(dto.getLastName());
		assertThat(view.getLoginId()).isEqualTo(dto.getLoginId());
		assertThat(view.getLastLoginDate()).isEqualTo(dto.getLastLoginDate());
		assertThat(view.getCountry()).isEqualTo(dto.getCountry());
		assertThat(view.getStatus()).isEqualTo(dto.getStatus());
		assertThat(view.getType()).isEqualTo(dto.getType());
	}
}
