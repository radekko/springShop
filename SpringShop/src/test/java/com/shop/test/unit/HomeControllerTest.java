package com.shop.test.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.shop.controller.HomePageController;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
	
	private MockMvc mockMvc;
	private HomePageController homePageController;
	
	@Before
	public void setUp() throws Exception {
		homePageController = new HomePageController();
		mockMvc = standaloneSetup(homePageController).build();
	}
	
	@Test
	public void testHomePage() throws Exception{
		mockMvc.perform(get("/"))
		.andExpect(view().name("home"));
	}
}
