package com.shop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.mappers.Mapper;
import com.shop.model.entity.domain.OrderDTO;
import com.shop.model.entity.domain.UserDTO;
import com.shop.model.entity.persistent.Order;
import com.shop.model.entity.persistent.User;
import com.shop.pagination.EntityPage;
import com.shop.pagination.NavigationPagesCreator;
import com.shop.service.OrderService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	private OrderService orderService;
	private NavigationPagesCreator navPagesCreator;
	private Mapper<Order, OrderDTO> orderMapper;
	private Mapper<User, UserDTO> userMapper;

	@Value("${com.shop.controller.OrderController.maxNavigationPage}")
	private int maxNavigationPages;

	@Value("${com.shop.controller.OrderController.maxOrdersOnPage}")
	private int maxOrdersOnPage;

	@Autowired
	public OrderController(OrderService orderService, NavigationPagesCreator navPagesCreator, 
			Mapper<Order, OrderDTO> orderMapper, Mapper<User, UserDTO> userMapper) {
		this.orderService = orderService;
		this.navPagesCreator = navPagesCreator;
		this.orderMapper = orderMapper;
		this.userMapper = userMapper;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String displayOrders(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model) {

		EntityPage<Order> paginateOrders;
		UserDTO chosenUser = new UserDTO();
		
		if(isUserSelected(username)) {
			chosenUser.setUsername(username);
			paginateOrders = orderService.getPaginateOrdersForUser(page, maxOrdersOnPage,username,false);
		}
		else
			paginateOrders = orderService.getPaginateOrders(page, maxOrdersOnPage,false);
			
		model.addAttribute("totalOrders",paginateOrders.getTotalRecords());
		model.addAttribute("orderDTO", mapEntityPageToDTO(paginateOrders));
		model.addAttribute("navigationPages", createNavPages(paginateOrders));
		model.addAttribute("usersList",getUsersWhichMakeAnyOrder());
		model.addAttribute(chosenUser);
		model.addAttribute("username", username);
		return "orderForm";
	}

	private List<Integer> createNavPages(EntityPage<Order> paginateOrders) {
		return navPagesCreator.create(paginateOrders, maxNavigationPages);
	}

	private List<OrderDTO> mapEntityPageToDTO(EntityPage<Order> paginateOrders) {
		return paginateOrders.getItems().stream()
				.map(e -> orderMapper.convertEntityToDTO(e)).collect(Collectors.toList());
	}

	private List<UserDTO> getUsersWhichMakeAnyOrder() {
		return orderService.getUsersWhichMakeOrder().stream()
				.map(e -> userMapper.convertEntityToDTO(e)).collect(Collectors.toList());
	}
	
	private boolean isUserSelected(String uName) {
		return uName != null && !uName.isEmpty();
	}
}
