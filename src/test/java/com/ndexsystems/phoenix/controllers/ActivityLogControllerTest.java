package com.ndexsystems.phoenix.controllers;

import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.ndexsystems.phoenix.dto.enume.UserStatus;
import com.ndexsystems.phoenix.views.ContextView;
import com.ndexsystems.phoenix.views.OrganizationUnitView;
import com.ndexsystems.phoenix.views.UserView;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ActivityLogControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private static final String CONTEXT_PATH = "/phoenix";
	private static final String PRODUCT = "fullservice";

	private static final String URL_ACTIVITY_EN = "/phoenix/" + PRODUCT + "/en/activity";
	private static final String URL_ACTIVITY_FR = "/phoenix/" + PRODUCT + "/fr/activity";

	private static final String URL_SEARCH_EN = "/phoenix/" + PRODUCT + "/en/activity/search";
	private static final String URL_SEARCH_FR = "/phoenix/" + PRODUCT + "/fr/activity/search";

	private static final String URL_ADD_EN = "/phoenix/" + PRODUCT + "/en/activity/add";
	private static final String URL_LOGIN = "/phoenix/" + PRODUCT + "/en/";

	private ContextView context;
	
	@Value("${databases.defaultDb}")
    protected String defaultDb;

	@BeforeEach
	void setup() {
		UserView user = UserView.builder().organizationUnitKey(BigDecimal.valueOf(73)).email("john.doe@test.com")
				.firstName("System").lastName("Administrator").loginId("sysadmin").status(UserStatus.ACCESSALLOWED)
				.country("CA").build();

		OrganizationUnitView orgUnit = new OrganizationUnitView();
		orgUnit.setOrganizationUnitKey(BigDecimal.valueOf(73));
		orgUnit.setId("1T");
		orgUnit.setNName("Root Organization");

		context = new ContextView();
		context.setUser(user);
		context.setOrganizationUnit(orgUnit);
	}

	// -----------------------------
	// SHOW ACTIVITY
	// -----------------------------

	@Test
	void testShowActivityFR() throws Exception {
		mockMvc.perform(get(URL_ACTIVITY_FR).contextPath(CONTEXT_PATH).sessionAttr("CONTEXT", context))
				.andExpect(status().isOk()).andExpect(view().name("activityLog"))
				.andExpect(model().attributeExists("title")).andExpect(model().attributeExists("categories"))
				.andExpect(model().attributeExists("importances")).andExpect(model().attributeExists("searchView"))
				.andExpect(model().attributeExists("auditItems"));
	}

	@Test
	void testShowActivityEN() throws Exception {
		mockMvc.perform(get(URL_ACTIVITY_EN).contextPath(CONTEXT_PATH).sessionAttr("CONTEXT", context))
				.andExpect(status().isOk()).andExpect(view().name("activityLog"))
				.andExpect(model().attributeExists("title")).andExpect(model().attributeExists("categories"))
				.andExpect(model().attributeExists("importances")).andExpect(model().attributeExists("searchView"))
				.andExpect(model().attributeExists("auditItems"));
	}

	@Test
	void testShowActivity_userNull_redirectToLogin() throws Exception {
		mockMvc.perform(get(URL_ACTIVITY_EN).contextPath(CONTEXT_PATH)).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl(URL_LOGIN + defaultDb + "/login" ));
	}

	// -----------------------------
	// SEARCH
	// -----------------------------

	@Test
	void testSearchActivitiesFR() throws Exception {
		mockMvc.perform(post(URL_SEARCH_FR).contextPath(CONTEXT_PATH).sessionAttr("CONTEXT", context)
				.param("userId", "%").param("category", "ACSF").param("importance", "0").param("fromDate", "2025-12-19")
				.param("toDate", "2025-12-19").param("searchDescription", "").param("searchCriteria", ""))
				.andExpect(status().isOk()).andExpect(view().name("activityLog"))
				.andExpect(model().attributeExists("auditItems"));
	}

	@Test
	void testSearchActivitiesEN() throws Exception {
		mockMvc.perform(post(URL_SEARCH_EN).contextPath(CONTEXT_PATH).sessionAttr("CONTEXT", context)
				.param("userId", "%").param("category", "ACSF").param("importance", "0").param("fromDate", "2025-12-19")
				.param("toDate", "2025-12-19").param("searchDescription", "").param("searchCriteria", ""))
				.andExpect(status().isOk()).andExpect(view().name("activityLog"))
				.andExpect(model().attributeExists("auditItems"));
	}

	// -----------------------------
	// ADD MANUAL LOG
	// -----------------------------

	@Test
	void testShowAddManualLogUserNullRedirectToLogin() throws Exception {
		mockMvc.perform(get(URL_ADD_EN).contextPath(CONTEXT_PATH)).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl(URL_LOGIN + defaultDb + "/login" ));
	}

	@Test
	void testShowAddManualLogEN() throws Exception {
		mockMvc.perform(get(URL_ADD_EN).contextPath(CONTEXT_PATH).sessionAttr("CONTEXT", context))
				.andExpect(status().isOk()).andExpect(view().name("activityAdd"))
				.andExpect(model().attributeExists("manualLog")).andExpect(model().attributeExists("manualAuditItems"))
				.andExpect(model().attribute("manualLog", hasProperty("activityType")))
				.andExpect(model().attribute("manualLog", hasProperty("importance")))
				.andExpect(model().attribute("manualLog", hasProperty("userId")));
	}
}
