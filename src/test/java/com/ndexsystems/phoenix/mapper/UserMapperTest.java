package com.ndexsystems.phoenix.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ndexsystems.phoenix.dto.NdexEnumerationElement;
import com.ndexsystems.phoenix.dto.NdexEnumerations;
import com.ndexsystems.phoenix.dto.UserDTO;
import com.ndexsystems.phoenix.dto.UserLanguageResources;
import com.ndexsystems.phoenix.dto.enume.UserStatus;
import com.ndexsystems.phoenix.entities.User;
import com.ndexsystems.phoenix.services.LanguageResourceService;
import com.ndexsystems.phoenix.util.UserContext;

@SpringBootTest
public class UserMapperTest {

	@Autowired
	UserMapperEntityToDTO mapper;
	
	@Autowired
	private LanguageResourceService languageResourceService;
	NdexEnumerations enums ;

	@BeforeEach
	void setup() {
	enums = NdexTestFactory.fakeEnums();
		
	}

	@Test
	void testMapStatus() {
		User u = new User();
		u.setStatus("ALOWD"); 

		UserDTO dto = mapper.toDTO(u);

		assertNotNull(dto.getStatus());
		assertEquals(UserStatus.ACCESSALLOWED, dto.getStatus());
	}

	@Test
	void testMapTypeBackOfficeIdToEnumElementFR() {
		  UserLanguageResources lang = languageResourceService.load("fr");
		    UserContext.set(lang);
		
		User user = new User();
		user.setNtype("0");

		UserDTO dto = mapper.toDTO(user);

		assertNotNull(dto.getType(), "type should not be null");

		NdexEnumerationElement type = dto.getType();

		assertEquals("0", type.backOfficeId());
		assertEquals("usertype", type.ndexEnumerationName());
		assertEquals("administrator", type.resourceKey());
		assertEquals("Administrateur", type.value());
	}
	
	@Test
	void testMapTypeBackOfficeIdToEnumElementEN() {
		  UserLanguageResources lang = languageResourceService.load("en");
		    UserContext.set(lang);
		
		User user = new User();
		user.setNtype("0");

		UserDTO dto = mapper.toDTO(user);

		assertNotNull(dto.getType(), "type should not be null");

		NdexEnumerationElement type = dto.getType();

		assertEquals("0", type.backOfficeId());
		assertEquals("usertype", type.ndexEnumerationName());
		assertEquals("administrator", type.resourceKey());
		assertEquals("Administrator", type.value());
	}

	@Test
	void test_mapType_null_safe() {
		User u = new User();
		u.setNtype(null);

		UserDTO dto = mapper.toDTO(u);

		assertNull(dto.getType());
	}

	@Test
	void test_mapStatus_unknown_code() {
		User u = new User();
		u.setStatus("XXXXXX");

		UserDTO dto = mapper.toDTO(u);

		assertNull(dto.getStatus());
	}
}
