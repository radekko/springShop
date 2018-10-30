package com.shop.test.unit;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MockMvc;

import com.shop.controller.MainPageController;
import com.shop.mappers.Mapper;
import com.shop.model.entity.domain.OrderDTO;
import com.shop.model.entity.persistent.Order;
import com.shop.model.entity.persistent.Product;
import com.shop.pagination.EntityPage;
import com.shop.pagination.NavigationPagesCreator;
import com.shop.service.CartService;
import com.shop.service.CategoryService;
import com.shop.service.OfferService;

@RunWith(MockitoJUnitRunner.class)
public class MainPageControllerTest {

	@Mock
	private OfferService offerService;
	
	@Mock
	private CartService cartService;
	
	@Mock
	private CategoryService categoryService;
	
	@Mock
	private NavigationPagesCreator navPagesCreator;
	
	@Mock
	private Mapper<Order, OrderDTO> mapper;
	
	@InjectMocks
	private MainPageController mainPageController;

	private MockMvc mockMvc;
	
	@Value("${com.shop.controller.MainPageController.maxNavigationPage}")
	private int maxNavigationPages;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(mainPageController).build();
	}
	
	@Test
	public void testShowMainPageForm() throws Exception {
		given(offerService.getPaginateOfferForClient(anyInt(),anyString(),anyInt())).willReturn(preparePaginateOrders());
		
		mockMvc.perform(get("/main/displayOffer")
			.param("categoryName", "categoryName"))
			.andExpect(view().name("mainForm"))
			.andExpect(model().attributeExists("offer"))
			.andExpect(model().attributeExists("navigationPages"));
	}
	
	private EntityPage<Product> preparePaginateOrders(){
		List<Product> list = new ArrayList<>();
		list.add(new Product());
		
		EntityPage<Product> idList = new EntityPage<Product>();
		idList.setItems(list);
		return idList;
	}
}
