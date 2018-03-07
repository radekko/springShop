package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.service.CartService;
import com.shop.service.ProductService;

@Controller
@RequestMapping(value = "/after")
public class AfterLogin {

	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;

	@RequestMapping(method = RequestMethod.GET)
	public String homePageAfterLogin(Model model) {
		addOfferToModel(model);
		return "afterLogin";
	}
	//tu dac line item z validacja
	@RequestMapping(method=RequestMethod.POST)
	public String addProductToCart(@RequestParam("amount") String amount,
			@RequestParam("uniqueProductCode") String uniqueProductCode,
			@RequestParam("name") String name,
			@RequestParam("price") String price, 
			Model model){	
		cartService.addToCart(amount,uniqueProductCode,price,name);
		addChosenProductToModel(amount, name, model);
		addOfferToModel(model);
		return "afterLogin";
	}

	@RequestMapping(value="/displayCart", method=RequestMethod.GET)
	public String displayCart(Model model)
	{
		model.addAttribute("orders", cartService.getCart());
		return "cartForm";
	}
	
	@RequestMapping(value="/displayCart", method=RequestMethod.POST)
	public String acceptOrder(Model model)
	{
		model.addAttribute("orders", cartService.getCart());
		cartService.storeCartToDatabase();
		return "cartForm";
	}
	
	private void addChosenProductToModel(String amount, String name, Model model) {
		model.addAttribute("currentChosenName", name);
		model.addAttribute("currentChosenAmount", amount);
	}
	
	private void addOfferToModel(Model model) {
		model.addAttribute("products", productService.findAllProduct());
	}

}
