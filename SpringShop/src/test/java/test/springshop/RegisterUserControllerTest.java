package test.springshop;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.shop.config.RootConfig;
import com.shop.config.WebConfig;
import com.shop.controller.RegisterUserController;
import com.shop.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
public class RegisterUserControllerTest {
	
	private MockMvc mockMvc;
	@Autowired
	UserService userService;
	
	@Before
	public void initializeLineItems() {
	}

	@Test
	public void testShowRegistrationForm() throws Exception {
		RegisterUserController registerController = new RegisterUserController();
		mockMvc = standaloneSetup(registerController).build();
		mockMvc.perform(get("/register"))
			.andExpect(view().name("registerForm")).andExpect(model().attributeExists("user"));
	}
	
	@Test
	public void testRegisterUserController() throws Exception {
		RegisterUserController registerController = new RegisterUserController();
		mockMvc = standaloneSetup(registerController).build();
		
		mockMvc.perform(
		        post("/register")
		        .param("user.username", "asd")
		        .param("user.password", "asd")
		        .param("user.email", "a@d"))
				.andDo(print())
		        .andExpect(status().isOk())  
		        .andExpect(model().attributeHasNoErrors("user"))
		        ;
	}
	
}
