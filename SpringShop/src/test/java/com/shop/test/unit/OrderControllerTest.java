package com.shop.test.unit;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
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
import org.springframework.test.web.servlet.MockMvc;

import com.shop.controller.OrderController;
import com.shop.mappers.Mapper;
import com.shop.model.entity.domain.OrderDTO;
import com.shop.model.entity.persistent.Order;
import com.shop.pagination.EntityPage;
import com.shop.pagination.NavigationPagesCreator;
import com.shop.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private OrderService orderService;
	
	@Mock
	private NavigationPagesCreator navPagesCreator;
	
	@Mock
	private Mapper<Order, OrderDTO> mapper;
	
	@InjectMocks
	private OrderController orderController;

	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(orderController).build();
	}
	
	@Test
	public void testShowOrderForm() throws Exception {
		given(orderService.getPaginateOrders(anyInt(),anyInt()))
		.willReturn(preparePaginateOrders());
		
		mockMvc.perform(get("/order"))
			.andExpect(view().name("orderForm"))
			.andExpect(model().attributeExists("orderDTO"))
			.andExpect(model().attributeExists("navigationPages"));
	}
	
	private EntityPage<Order> preparePaginateOrders(){
		List<Order> list = new ArrayList<>();
		list.add(new Order());
		
		EntityPage<Order> idList = new EntityPage<Order>();
		idList.setItems(list);
		return idList;
	}
}
