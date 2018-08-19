package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String displayOrders(Model model, RedirectAttributes redirectAttributes) {
		int page = 1;
//		model.addAttribute("order", orderService.getPaginateOfferForClient(page));
		return "orderForm";
	}

}
