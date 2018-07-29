package com.shop.test.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import com.shop.controller.CartController;
import com.shop.service.CartService;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
@WebAppConfiguration
public class CartControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private CartService cartService;
	
	@InjectMocks
	private CartController cartController;
	
	@Before
	public void setUp() {
		mockMvc = standaloneSetup(cartController).build();
	}
	
	@Test
	public void testIfReturnProperlyView() throws Exception{
		mockMvc.perform(get("/main/displayCart"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("cartForm"));
	}
	
//	@Test
//	public void testIfProperlyReturnToOfferAfterPresButtonBack() throws Exception{
//		mockMvc.perform(post("/main/displayCart").param("back", "Back to offer"))
//		.andDo(print())
//		.andExpect(status().is3xxRedirection())
//		.andExpect(view().name("redirect:/main/displayOffer"));
//	}
//	
	@Test
	public void testIfProperlyClearCart() throws Exception{
		mockMvc.perform(post("/main/displayCart").param("clear", "Clear cart"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("cartForm"));
	}
	
}

