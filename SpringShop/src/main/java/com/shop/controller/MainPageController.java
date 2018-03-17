package com.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.model.LineItem;
import com.shop.service.CartService;
import com.shop.service.OfferService;

@Controller
@RequestMapping(value = "/main")
public class MainPageController {
	
	@Autowired
	OfferService offerService;
	
	@Autowired
	CartService cartService;

	@RequestMapping(method = RequestMethod.GET)
	public String homePageAfterLogin(@ModelAttribute("username") String username,Model model) {

		if(!"".equals(username))
			cartService.setUsername(username);
		
		addOfferToModel(model);
		model.addAttribute(new LineItem());
		return "mainForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String addProductToCart(@Valid LineItem lineItem, BindingResult errors,Model model){	

		if(errors.hasErrors()) 
			addErrorMessageToModel(model);
		else {
			cartService.addItem(lineItem);
			addInfoAboutCurrentChosenProductToModel(lineItem, model);
		}
		
		addOfferToModel(model);
		model.addAttribute(new LineItem());
		return "mainForm";
	}

	private void addInfoAboutCurrentChosenProductToModel(LineItem lineItem, Model model) {
		model.addAttribute("currentChosenName", lineItem.getName());
		model.addAttribute("currentChosenAmount", lineItem.getAmount());
	}
	
	private void addOfferToModel(Model model) {
		model.addAttribute("offer", offerService.getOfferForClient());
	}
	private void addErrorMessageToModel(Model model) {
		model.addAttribute("error", "Amount must be a natural number");
	}
}
