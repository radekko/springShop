package com.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.entity.domain.LineItemDTO;
import com.shop.model.entity.persistent.Category;
import com.shop.model.entity.persistent.Product;
import com.shop.pagination.EntityPage;
import com.shop.pagination.NavigationPagesCreator;
import com.shop.service.CartService;
import com.shop.service.CategoryService;
import com.shop.service.OfferService;

@Controller
@RequestMapping(value = "/main/displayOffer")
public class MainPageController {
	
	private OfferService offerService;
	private CartService cartService;
	private CategoryService categoryService;
	
	private final static int INITIAL_STOCK_AMOUNT = 0;

	@Value("${com.shop.controller.MainPageController.maxNavigationPage}")
	private int maxNavigationPages;
	@Value("${com.shop.controller.MainPageController.maxProductOnPage}")
	private int maxProductOnPage;
	
	@Autowired
	public MainPageController(OfferService offerService, CartService cartService, CategoryService categoryService) {
		this.offerService = offerService;
		this.cartService = cartService;
		this.categoryService = categoryService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String afterSelectCategory(
	    @RequestParam(value = "categoryName")  String categoryName,
	    @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	    Model model){

		EntityPage<LineItemDTO> pageToDisplay = new EntityPage<LineItemDTO>(
				offerService.getPaginateOfferForClient(page,categoryName,maxProductOnPage),this::convertProductToLineItemDTO);
		
		model.addAttribute("offer", pageToDisplay);
		model.addAttribute("navigationPages",
				NavigationPagesCreator.create(page, maxNavigationPages, pageToDisplay.getTotalRecords(), maxProductOnPage));
		model.addAttribute("categoriesList", categoryService.getAllCategories());
		model.addAttribute("categoryName", categoryName);
		model.addAttribute(new LineItemDTO());
		model.addAttribute(new Category());
		return "mainForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String addProductToCart(@Valid LineItemDTO lineItem,BindingResult errors,
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
	
	private LineItemDTO convertProductToLineItemDTO(Product p) {
	    return new LineItemDTO(p.getName(),p.getUniqueProductCode(),p.getPrice(),INITIAL_STOCK_AMOUNT);
	}
}
