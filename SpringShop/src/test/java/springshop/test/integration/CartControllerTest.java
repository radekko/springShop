package springshop.test.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.shop.config.RootConfig;
import com.shop.config.WebConfig;
import com.shop.controller.CartController;
import com.shop.service.CartService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class, WebConfig.class})
@WebAppConfiguration
public class CartControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	CartService cartService;
	
	@InjectMocks
	CartController cartController;
	
//	@Autowired MockHttpSession session;
	
	@Before
	public void initializeLineItems() {
		mockMvc = standaloneSetup(cartController).build();
	}
	
	@Test
	public void testItemsAddintgToCartAreUnique() throws Exception{
		mockMvc.perform(get("/main/displayCart"))
		.andDo(print())
		.andExpect(status().isOk())
//		.andExpect(view().name("cartForm"))
		;
	}
	
}

