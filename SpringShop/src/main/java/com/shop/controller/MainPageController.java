package com.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.model.entity.domain.LineItem;
import com.shop.service.CartService;
import com.shop.service.OfferService;
import com.shop.utils.AnnotationExtractor;


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
		
		prepareModel(model,1);
		return "mainForm";
	}

	@RequestMapping(method = RequestMethod.GET,value = "/productList")
	public String homePageAfterSelectPage(Model model,@RequestParam("page") int page) {
		prepareModel(model,page);
		return "mainForm";
	}
	
	@RequestMapping(method=RequestMethod.POST,value={"", "/productList"})
	public String addProductToCart(@Valid LineItem lineItem,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			BindingResult errors,Model model){	

		if(errors.hasErrors()) 
			addErrorMessageToModel(model);
		else {
			cartService.addItem(lineItem);
			addInfoAboutCurrentChosenProductToModel(lineItem, model);
		}
		
		prepareModel(model,page);
		return "mainForm"; 
	}
	
	private void prepareModel(Model model, int page) {
		addOfferToModel(model,page);
		model.addAttribute(new LineItem());
	}

	private void addInfoAboutCurrentChosenProductToModel(LineItem lineItem, Model model) {
		model.addAttribute("currentChosenName", lineItem.getName());
		model.addAttribute("currentChosenAmount", lineItem.getAmount());
	}
	
	private void addOfferToModel(Model model, int page) {
		model.addAttribute("offer", offerService.getPaginationOfferForClient(page));
		model.addAttribute("currentPath", AnnotationExtractor.extractPathToController(getClass()));
	}
	private void addErrorMessageToModel(Model model) {
		model.addAttribute("error", "Amount must be a natural number");
	}

}
