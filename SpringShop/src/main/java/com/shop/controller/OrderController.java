package com.shop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.mappers.Mapper;
import com.shop.model.dto.OrderDTO;
import com.shop.model.dto.UserDTO;
import com.shop.model.entity.Order;
import com.shop.model.entity.User;
import com.shop.pagination.EntityPage;
import com.shop.pagination.NavigationPagesCreator;
import com.shop.service.OrderService;

@Controller
@RequestMapping(path = "/order")
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
	
	@GetMapping
	public String displayOrdersToAccomplish(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model) {
	
		boolean isRealized = false;
		EntityPage<Order> paginateOrders = getOrdersToDisplay(username, page, isRealized);
		UserDTO chosenUser = getChosenUser(username);
		
		prepareModel(model, paginateOrders, username, chosenUser);
		return "admin/orderForm";
	}

	@GetMapping(path = "/accomplished")
	public String displayOrdersAccomplished(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model) {

		boolean isRealized = true;
		EntityPage<Order> paginateOrders = getOrdersToDisplay(username, page, isRealized);
		UserDTO chosenUser = getChosenUser(username);
		
		prepareModel(model, paginateOrders, username, chosenUser);
		return "admin/accomplishedOrderForm";
	}
	
	@PostMapping
	public String accomplishOrder(
		@RequestParam("orderIdentifier") String orderIdentifier,
		@RequestParam(value = "page", required = false, defaultValue = "1") int page,
		@RequestParam(value = "username", required = false) String username,
		RedirectAttributes redirectAttributes,
		Model model) {
		
		orderService.acomplish(orderIdentifier);
		redirectAttributes.addAttribute("page", page);
		redirectAttributes.addAttribute("username", username);
		return "redirect:/order";	
	}
	
	private void prepareModel(Model model, EntityPage<Order> paginateOrders, String username, UserDTO chosenUser) {
		model.addAttribute("totalOrders",paginateOrders.getTotalRecords());
		model.addAttribute("orderDTO", mapEntityPageToDTO(paginateOrders));
		model.addAttribute("navigationPages", createNavPages(paginateOrders));
		model.addAttribute("usersList",getUsersWhichMakeAnyOrder());
		model.addAttribute(chosenUser);
		model.addAttribute("username", username);
	}
	
	private UserDTO getChosenUser(String username) {
		UserDTO chosenUser = new UserDTO();
		
		if(checkIfUserWasSelected(username))
			chosenUser.setUsername(username);
		return chosenUser;
	}

	private boolean checkIfUserWasSelected(String username) {
		if(isContainValue(username))
			return true;
		return false;
	}
	
	private EntityPage<Order> getOrdersToDisplay(String username, int page, boolean isRealized) {
		if(isContainValue(username))
			return orderService.getPaginateOrdersForUser(page, maxOrdersOnPage,username,isRealized);
		else
			return orderService.getPaginateOrders(page, maxOrdersOnPage,isRealized);
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
	
	private boolean isContainValue(String s) {
		return s != null && !s.isEmpty();
	}
}
