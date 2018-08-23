package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.service.OrderService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String displayOrders(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("order", orderService.getPaginateOrders(page));
		return "orderForm";
	}

}
