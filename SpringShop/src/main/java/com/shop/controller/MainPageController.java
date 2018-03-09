package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.service.CartService;
import com.shop.service.OfferService;
import com.shop.utils.IntegerValidator;

@Controller
@RequestMapping(value = "/main")
public class MainPageController {
	
	@Autowired
	OfferService offerService;
	
	@Autowired
	CartService cartService;

	@RequestMapping(method = RequestMethod.GET)
	public String homePageAfterLogin(
			Model model) {
		addOfferToModel(model);
		return "mainForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String addProductToCart(
			@RequestParam("amount") String amount,
			@RequestParam("uniqueProductCode") String uniqueProductCode,
			@RequestParam("name") String name,
			@RequestParam("price") String price, 
			Model model){	
		
		if(IntegerValidator.isPositiveInteger(amount)) {
			cartService.addToCart(amount,uniqueProductCode,price,name);
			addChosenProductToModel(amount, name, model);
		} else
			addErrorMessageToModel(model);
		
		addOfferToModel(model);
		return "mainForm";
	}

	private void addChosenProductToModel(String amount, String name, Model model) {
		model.addAttribute("currentChosenName", name);
		model.addAttribute("currentChosenAmount", amount);
	}
	
	private void addOfferToModel(Model model) {
		model.addAttribute("offer", offerService.getOfferForClient());
	}
	private void addErrorMessageToModel(Model model) {
		model.addAttribute("error", "Amount must be a natural number");
	}
}
