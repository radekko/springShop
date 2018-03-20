package com.shop.test.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import com.shop.controller.HomePageController;

public class HomeControllerTest {
	@Test
	public void testHomePage() throws Exception{
		HomePageController homeController = new HomePageController();
		MockMvc mock = standaloneSetup(homeController).build();
		mock.perform(get("/")).andExpect(view().name("home"));
	}
}
