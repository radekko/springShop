package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.service.CartService;

@Controller
@RequestMapping(value = "/main")
public class CartController {

	@Autowired
	CartService cartService;
	
	@RequestMapping(value="/displayCart", method=RequestMethod.GET)
	public String displayCart(Model model)
	{
		model.addAttribute("orders", cartService.getCart());
		return "cartForm";
	}
	
	@RequestMapping(value="/displayCart", method=RequestMethod.POST)
	public String makeOrder(Model model)
	{
		model.addAttribute("orders", cartService.getCart());
		cartService.storeCartToDatabase();
		return "cartForm";
	}
}
