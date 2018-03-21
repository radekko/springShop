package com.shop.controller;

import java.lang.reflect.Field;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.model.entity.domain.LineItem;
import com.shop.service.CartService;
import com.shop.service.OfferService;


@Controller
@RequestMapping(value = "/main")
public class MainPageController {
	
	@Autowired
	OfferService offerService;
	
	@Autowired
	CartService cartService;
	
	private static final String s = "";

	@RequestMapping(method = RequestMethod.GET)
	public String homePageAfterLogin(@ModelAttribute("username") String username,Model model) {
		if(!"".equals(username))
			cartService.setUsername(username);
		
		prepareModel(model,1);
		return "mainForm";
	}

	@RequestMapping(method = RequestMethod.GET,value = "/productList")
	public String homePageAfterSelectPage(Model model,@RequestParam("page") int page) {
		System.out.println("Page "+page);
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
//		model.addAttribute("offer", offerService.getOfferForClient());
		model.addAttribute("offer", offerService.getPaginationOfferForClient(page));
//		System.out.println(offerService.getPaginationOfferForClient(page).getNavigationPages());
		model.addAttribute("currentPath", "main");
	}
	private void addErrorMessageToModel(Model model) {
		model.addAttribute("error", "Amount must be a natural number");
	}
	
//	private String doa() {
//		RequestMapping req = null;
//		for (Field f: MainPageController.class.getFields()) {
//			req = f.getAnnotation(RequestMapping.class);
//			   if (req != null) {
//			       System.out.println(req.value());
//			       return req.value()[0];
//			   }
//			}
//		return "0";
//	}
}
