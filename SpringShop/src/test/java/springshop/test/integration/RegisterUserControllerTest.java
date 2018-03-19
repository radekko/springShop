package springshop.test.integration;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.shop.config.RootConfig;
import com.shop.controller.RegisterUserController;
import com.shop.service.UserService;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
public class RegisterUserControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private RegisterUserController registerController;
	
	private final static String REGISTER_FORM_NAME = "registerForm";
	
	@Before
	public void initializeLineItems() {
		mockMvc = standaloneSetup(registerController).build();
	}
	
	@Test
	public void testRegisterIsSuccess() throws Exception {
		mockMvc.perform(post("/register")
                .param("username", "asd2").param("password", "asd2").param("email", "a@aa"))
				.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("successRegistered"))
                .andExpect(model().attributeHasNoErrors("user"));
	}
	
	@Test
	public void testRegisterIsFailed() throws Exception {
		mockMvc.perform(post("/register")
                .param("username", "asd2").param("password", "asd2").param("email", "aaa"))
				.andDo(print())
                .andExpect( model().attributeHasFieldErrors("user", "email"))
                .andExpect(status().isOk())
                .andExpect(view().name(REGISTER_FORM_NAME));
	}

	@Test
	public void testShowRegistrationForm() throws Exception {
		RegisterUserController registerController = new RegisterUserController();
		mockMvc = standaloneSetup(registerController).build();
		mockMvc.perform(get("/register"))
			.andExpect(view().name(REGISTER_FORM_NAME)).andExpect(model().attributeExists("user"));
	}
	

	
}
