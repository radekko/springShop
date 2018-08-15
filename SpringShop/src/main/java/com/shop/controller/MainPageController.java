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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.entity.domain.LineItem;
import com.shop.model.entity.persistent.Category;
import com.shop.service.CartService;
import com.shop.service.CategoryService;
import com.shop.service.OfferService;

@Controller
@RequestMapping(value = "/main/displayOffer")
public class MainPageController {
	
	private OfferService offerService;
	private CartService cartService;
	private CategoryService categoryService;
	
	@Autowired
	public MainPageController(OfferService offerService, CartService cartService, CategoryService categoryService) {
		this.offerService = offerService;
		this.cartService = cartService;
		this.categoryService = categoryService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String afterSelectCategory(
		@ModelAttribute("username") String username, 
	    @RequestParam(value = "categoryName")  String categoryName,
	    @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	    Model model){
		if(!"".equals(username))
			cartService.setUsername(username);

		model.addAttribute("categoriesList", categoryService.getAllCategories());
		model.addAttribute("offer", offerService.getPaginateOfferForClient(page,categoryName));
		model.addAttribute("categoryName", categoryName);
		model.addAttribute(new LineItem());
		model.addAttribute(new Category());
		return "mainForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String addProductToCart(@Valid LineItem lineItem,BindingResult errors,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "categoryName") String categoryName,
			RedirectAttributes redirectAttributes,
			Model model){	
		
		if(errors.hasErrors())
			redirectAttributes.addFlashAttribute("error", "Amount must be a natural number");
		else {
			cartService.addItem(lineItem);
			redirectAttributes.addFlashAttribute("currentChosenName", lineItem.getName());
			redirectAttributes.addFlashAttribute("currentChosenAmount", lineItem.getAmount());
		}
		
		redirectAttributes.addAttribute("categoryName", categoryName);
		redirectAttributes.addAttribute("page", page);
		return "redirect:/main/displayOffer";
	}

}
