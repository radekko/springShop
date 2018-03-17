package springshop.test.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.shop.config.RootConfig;
import com.shop.controller.RegisterUserController;
import com.shop.model.User;
import com.shop.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
public class RegisterUserControllerTest {
	
	private MockMvc mockMvc;
	@Autowired
	UserService userService;
	 @Autowired
	    private WebApplicationContext wac;
	
	@Before
	public void initializeLineItems() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testRegisterUserController() throws Exception {
		this.mockMvc.perform(post("/register")
                .param("username", "asd2").param("password", "").param("email", "a"))
                .andExpect(
                    model().attributeHasFieldErrors(
                        "user", "password", "email"
                    )
                )
                .andExpect(status().isOk())
                .andExpect(view().name("/register"));
    
	
	}

//	@Test
//	public void testRegisterUserController() throws Exception {
//		RegisterUserController registerController = new RegisterUserController();
//		mockMvc = standaloneSetup(registerController).build();
//
//	        
//		mockMvc.perform(
//		        post("/register")
//		        .param("user.username", "asd")
//		        .param("user.password", "asd")
//		        .param("user.email", "a@d")
//				.flashAttr("user", new User()))
//				.andDo(print())
//		        .andExpect(status().isOk())  
//		        .andExpect(model().attributeHasNoErrors("user"))
//		        ;
//	}
//	

//	@Test
//	public void testShowRegistrationForm() throws Exception {
//		RegisterUserController registerController = new RegisterUserController();
//		mockMvc = standaloneSetup(registerController).build();
//		mockMvc.perform(get("/register"))
//			.andExpect(view().name("registerForm")).andExpect(model().attributeExists("user"));
//	}
//	

	
}
