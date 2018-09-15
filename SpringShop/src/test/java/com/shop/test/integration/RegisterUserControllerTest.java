package com.shop.test.integration;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.shop.controller.RegisterUserController;
import com.shop.model.entity.persistent.User;
import com.shop.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class RegisterUserControllerTest {
	
	@Mock
	private UserService userService;
	
	private MockMvc mockMvc;
	private RegisterUserController registerController;
	
	private static final String REGISTER_FORM_NAME            =      "registerForm";
	private static final String SUCCESSED_REGISTER_FORM_NAME  = "successRegistered";
	private static final String VALID_USER                    =              "asd2";
	private static final String VALID_PASSWORD                =              "asd2";
	private static final String INVALID_EMAIL                 =               "aaa";
	private static final String VALID_EMAIL                   =              "a@aa";
	
	@Before
	public void setUp() {
		registerController = new RegisterUserController(userService);
		mockMvc = standaloneSetup(registerController).build();
	}
	
	@Test
	public void testRegisterIsSuccess() throws Exception {
		given(userService.storeUserIfNotExist(any(User.class))).willReturn(true);
		
		mockMvc.perform(post("/register")
				.param("save", "Save Changes")
                .param("username", VALID_USER)
                .param("password", VALID_PASSWORD)
                .param("email", VALID_EMAIL))
				.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(SUCCESSED_REGISTER_FORM_NAME))
                .andExpect(model().attributeHasNoErrors("userDTO"));
	}
	
	@Test
	public void testRegisterIsFailed() throws Exception {
		mockMvc.perform(post("/register")
				.param("save", "Save Changes")
                .param("username", VALID_USER)
                .param("password", VALID_PASSWORD)
                .param("email", INVALID_EMAIL))
				.andDo(print())
                .andExpect(model().attributeHasFieldErrors("userDTO", "email"))
                .andExpect(status().isOk())
                .andExpect(view().name(REGISTER_FORM_NAME));
	}

	@Test
	public void testShowRegistrationForm() throws Exception {
		mockMvc.perform(get("/register"))
			.andExpect(view().name(REGISTER_FORM_NAME))
			.andExpect(model().attributeExists("userDTO"));
	}
	
}
