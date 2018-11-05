package com.shop.test.unit;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.shop.config.Role;
import com.shop.controller.LoginController;
import com.shop.service.AuthenticationService;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest{

	private static final String AFTER_LOGIN_AS_ADMIN = "/order";
	private static final String AFTER_LOGIN_AS_USER = "/main/displayOffer";
	
	@Mock
	private AuthenticationService authenticationService;
	
	@InjectMocks
	private LoginController loginController;

	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(loginController).build();
	}

	@Test
	public void testShowRegistrationFormIfNotAuthenticated() throws Exception {
		given(authenticationService.getMainRole()).willReturn(Role.NONE);
		mockMvc.perform(get("/login"))
			.andExpect(view().name("loginForm"));
	}
	
	@Test
	public void testShowValidFormIfRoleIsAdmin() throws Exception {
		given(authenticationService.getMainRole()).willReturn(Role.ROLE_ADMIN);
		mockMvc.perform(get("/login"))
		.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:"+AFTER_LOGIN_AS_ADMIN));
	}
	
	@Test
	public void testShowValidFormIfRoleIsUser() throws Exception {
		given(authenticationService.getMainRole()).willReturn(Role.ROLE_USER);
		mockMvc.perform(get("/login"))
		.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:"+AFTER_LOGIN_AS_USER));
	}

}
