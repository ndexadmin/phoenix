package com.ndexsystems.phoenix.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.ndexsystems.phoenix.AbstractTenantJpaTestSupport;
import com.ndexsystems.phoenix.dto.enume.UserStatus;
import com.ndexsystems.phoenix.util.AuthUserContext;
import com.ndexsystems.phoenix.views.UserView;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ActivityLogControllerTest extends AbstractTenantJpaTestSupport {

	@Autowired
	private MockMvc mockMvc;

	private static final String CONTEXT_PATH = "/phoenix";
	private static final String PRODUCT = "fullservice";
	private static final String FIRM_ID = "gicapitalcorp";

	private static final String URL_ACTIVITY_EN = "/phoenix/" + PRODUCT + "/en/" + FIRM_ID + "/activity";
	private static final String URL_ACTIVITY_FR = "/phoenix/" + PRODUCT + "/fr/" + FIRM_ID + "/activity";

	private static final String URL_SEARCH_EN = "/phoenix/" + PRODUCT + "/en/" + FIRM_ID + "/activity/search";
	private static final String URL_SEARCH_FR = "/phoenix/" + PRODUCT + "/fr/" + FIRM_ID + "/activity/search";

	private static final String URL_ADD_EN = "/phoenix/" + PRODUCT + "/en/" + FIRM_ID + "/activity/add";
	private static final String URL_LOGIN_EN = "/phoenix/" + PRODUCT + "/en/" + FIRM_ID + "/login";

	@BeforeEach
	void setup() {
		AuthUserContext.set(UserView.builder().organizationUnitKey(BigDecimal.valueOf(73)).email("john.doe@test.com")
				.firstName("System").lastName("Administrator").loginId("sysadmin").status(UserStatus.ACCESSALLOWED)
				.country("CA").build());
	}

	@AfterEach
	void cleanup() {
		AuthUserContext.clear();
	}

		@Test
		void testShowActivityFR() throws Exception {
			mockMvc.perform(
					get(URL_ACTIVITY_FR).contextPath(CONTEXT_PATH).sessionAttr("USER_CONTEXT", AuthUserContext.get()))
					.andExpect(status().isOk()).andExpect(view().name("activityLog"))
					.andExpect(model().attributeExists("title")).andExpect(model().attributeExists("categories"))
					.andExpect(model().attributeExists("importances")).andExpect(model().attributeExists("searchView"))
					.andExpect(model().attributeExists("auditItems"));
		}

	@Test
	void testShowActivityEN() throws Exception {
		mockMvc.perform(
				get(URL_ACTIVITY_EN).contextPath(CONTEXT_PATH).sessionAttr("USER_CONTEXT", AuthUserContext.get()))
				.andExpect(status().isOk()).andExpect(view().name("activityLog"))
				.andExpect(model().attributeExists("title")).andExpect(model().attributeExists("categories"))
				.andExpect(model().attributeExists("importances")).andExpect(model().attributeExists("searchView"))
				.andExpect(model().attributeExists("auditItems"));
	}

	@Test
	void testShowActivity_userNull_redirectToLogin() throws Exception {
		AuthUserContext.clear();

		mockMvc.perform(get(URL_ACTIVITY_EN).contextPath(CONTEXT_PATH)).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl(URL_LOGIN_EN));
	}

	@Test
	void testSearchActivitiesFR() throws Exception {
		mockMvc.perform(post(URL_SEARCH_FR).contextPath(CONTEXT_PATH).sessionAttr("USER_CONTEXT", AuthUserContext.get())
				.param("userId", "%").param("category", "ACSF").param("importance", "0").param("fromDate", "2025-12-19")
				.param("toDate", "2025-12-19").param("searchDescription", "").param("searchCriteria", ""))
				.andExpect(status().isOk()).andExpect(view().name("activityLog"))
				.andExpect(model().attributeExists("title")).andExpect(model().attributeExists("categories"))
				.andExpect(model().attributeExists("importances")).andExpect(model().attributeExists("searchView"))
				.andExpect(model().attributeExists("auditItems"));
	}

	@Test
	void testSearchActivitiesEN() throws Exception {
		mockMvc.perform(post(URL_SEARCH_EN).contextPath(CONTEXT_PATH).sessionAttr("USER_CONTEXT", AuthUserContext.get())
				.param("userId", "%").param("category", "ACSF").param("importance", "0").param("fromDate", "2025-12-19")
				.param("toDate", "2025-12-19").param("searchDescription", "").param("searchCriteria", ""))
				.andExpect(status().isOk()).andExpect(view().name("activityLog"))
				.andExpect(model().attributeExists("title")).andExpect(model().attributeExists("categories"))
				.andExpect(model().attributeExists("importances")).andExpect(model().attributeExists("searchView"))
				.andExpect(model().attributeExists("auditItems"));
	}

/*	@Test
	void testShowAddManualLogUserPresent() throws Exception {
		mockMvc.perform(get(URL_ADD_FR).contextPath(CONTEXT_PATH).sessionAttr("USER_CONTEXT", AuthUserContext.get()))
				.andExpect(status().isOk()).andExpect(view().name("activityAdd"))

				.andExpect(model().attributeExists("manualLog"))
				.andExpect(model().attribute("manualLog", hasProperty("activityDate", is(LocalDate.now().toString()))))
				.andExpect(model().attribute("manualLog", hasProperty("importance", is("Information"))))
				.andExpect(model().attribute("manualLog", hasProperty("activityType", is("Manual edit"))))
				.andExpect(model().attribute("manualLog", hasProperty("userId", is("sysadmin"))))
				.andExpect(model().attribute("manualLog", hasProperty("description", is(""))));
	}*/

	@Test
	void testShowAddManualLog_userNull_redirectToLogin() throws Exception {
		AuthUserContext.clear();

		mockMvc.perform(get(URL_ADD_EN).contextPath(CONTEXT_PATH)).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl(URL_LOGIN_EN));
	}
}
