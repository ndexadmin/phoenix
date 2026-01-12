package com.ndexsystems.phoenix.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.ndexsystems.phoenix.AbstractTenantJpaTestSupport;
import com.ndexsystems.phoenix.config.TenantContext;
import com.ndexsystems.phoenix.entities.User;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserRepositoryCapitalTest extends AbstractTenantJpaTestSupport  {

	@Autowired
	private UserRepository userRepository;

	@Test
	void testUserTableLoginId() {

		TenantContext.setTenant("gicapitalcorp");
		String loginId = "sysadmin";
		
			
		
		User user = userRepository.userTableLoginId(loginId);
		assertThat(user).as("La procédure n'a retourné aucun user pour : " + loginId).isNotNull();

		assertThat(user.getLoginId()).as("Le champ loginId devrait être égal au loginId fourni").isEqualTo(loginId);

		assertThat(user.getFirstName()).isEqualTo("System");
		assertThat(user.getLastName()).isEqualTo("Administrator");
	}
}
