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

import com.shop.mappers.IMapper;
import com.shop.model.entity.domain.OrderDTO;
import com.shop.model.entity.persistent.Order;
import com.shop.pagination.EntityPage;
import com.shop.pagination.NavigationPagesCreator;
import com.shop.service.OrderService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	private OrderService orderService;
	private NavigationPagesCreator navPagesCreator;
	private IMapper<Order, OrderDTO> mapper;

	@Value("${com.shop.controller.OrderController.maxNavigationPage}")
	private int maxNavigationPages;

	@Value("${com.shop.controller.OrderController.maxOrdersOnPage}")
	private int maxOrdersOnPage;

	@Autowired
	public OrderController(OrderService orderService, NavigationPagesCreator navPagesCreator, IMapper<Order, OrderDTO> mapper) {
		this.orderService = orderService;
		this.navPagesCreator = navPagesCreator;
		this.mapper = mapper;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String displayOrders(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model) {

		EntityPage<Order> paginateOrders = orderService.getPaginateOrders(page, maxOrdersOnPage);
		List<Integer> navPages = navPagesCreator.create(paginateOrders, maxNavigationPages);
		List<OrderDTO> orderDTOlist = paginateOrders.getItems().stream()
				.map(e -> mapper.convertEntityToDTO(e)).collect(Collectors.toList());

		model.addAttribute("orderDTO", orderDTOlist);
		model.addAttribute("navigationPages", navPages);
		return "orderForm";
	}

}
