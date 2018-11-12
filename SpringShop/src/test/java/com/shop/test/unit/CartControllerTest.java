package com.shop.test.unit;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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

import com.shop.controller.CartController;
import com.shop.model.dto.LineItemDTO;
import com.shop.service.CartService;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {
	
	@Mock
	private CartService cartService;
	
	@InjectMocks
	private CartController cartController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(cartController).build();
	}
	
	@Test
	public void testIfReturnProperlyView() throws Exception{
		mockMvc.perform(get("/main/displayCart"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("orders", "totalPrice"))
		.andExpect(view().name("user/cartForm"));
	}
	
	@Test
	public void addToCart() throws Exception{
		willDoNothing().given(cartService).addItem(any(LineItemDTO.class));
		
		mockMvc.perform(post("/main/displayOffer")
		.param("categoryName", "categoryName"))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/main/displayOffer"));
	}
	
	@Test
	public void testMakeOrder() throws Exception{
		given(cartService.makeOrder()).willReturn(true);
		
		mockMvc.perform(get("/main/displayCart")
		.param("order", "Make order"))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attribute("message", true))
		.andExpect(view().name("redirect:/main/displayOffer"));
	}
	
	@Test
	public void testMakeOrderError() throws Exception{
		given(cartService.makeOrder()).willReturn(false);
		
		mockMvc.perform(get("/main/displayCart")
		.param("order", "Make order"))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attribute("message", false))
		.andExpect(view().name("redirect:/main/displayOffer"));
	}
	
	@Test
	public void testBackToOfferAfterPresButtonBack() throws Exception{
		mockMvc.perform(get("/main/displayCart").param("back", "Back to offer"))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/main/displayOffer"));
	}
	
	@Test
	public void testClearCart() throws Exception{
		mockMvc.perform(get("/main/displayCart").param("clear", "Clear cart"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("user/cartForm"));
	}
	
	@Test
	public void testDeleteLineItem() throws Exception{
		mockMvc.perform(delete("/main/displayCart")
		.param("_method", "delete")
		.param("uniqueProductCode", "code"))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/main/displayCart"));
	}
	
}