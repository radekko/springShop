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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.entity.domain.LineItemDTO;
import com.shop.model.entity.domain.OrderDTO;
import com.shop.model.entity.persistent.LineItem;
import com.shop.model.entity.persistent.Order;
import com.shop.pagination.EntityPage;
import com.shop.pagination.NavigationPagesCreator;
import com.shop.service.OrderService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	private OrderService orderService;

	@Value("${com.shop.controller.OrderController.maxNavigationPage}")
	private int maxNavigationPages;

	@Value("${com.shop.controller.OrderController.maxOrdersOnPage}")
	private int maxOrdersOnPage;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String displayOrders(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model, RedirectAttributes redirectAttributes) {
		EntityPage<OrderDTO> pageToDisplay = new EntityPage<OrderDTO>(
				orderService.getPaginateOrders(page,maxOrdersOnPage),this::convertOrderToDTO);
		
		model.addAttribute("orderDTO", pageToDisplay);
		model.addAttribute("navigationPages",
				NavigationPagesCreator.create(page, maxNavigationPages, pageToDisplay.getTotalRecords(),maxOrdersOnPage));
		
		return "orderForm";
	}

	private OrderDTO convertOrderToDTO(Order o) {
		List<LineItemDTO> items = o.getSetOfLineItems().stream()
				.map(this::convertLineItemToDTO).collect(Collectors.toList());
		return new OrderDTO(o.getUserId().getUsername(),o.getOrderIdentifier(),items);
	}
	
	private LineItemDTO convertLineItemToDTO(LineItem p) {
	    return new LineItemDTO(
	    		p.getProduct().getName(),
	    		p.getProduct().getUniqueProductCode(),
	    		p.getProductPrice(),
	    		p.getProductAmount());
	}
}
