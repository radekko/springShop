package com.shop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.mappers.Mapper;
import com.shop.model.dto.LineItemDTO;
import com.shop.model.entity.Category;
import com.shop.model.entity.Product;
import com.shop.pagination.EntityPage;
import com.shop.pagination.NavigationPagesCreator;
import com.shop.service.CategoryService;
import com.shop.service.OfferService;

@Controller
@RequestMapping(path = "/main/displayOffer")
public class OfferController {
	
	private OfferService offerService;
	private CategoryService categoryService;
	private NavigationPagesCreator navPagesCreator;
	private Mapper<Product, LineItemDTO> mapper;
	
	@Value("${com.shop.controller.MainPageController.maxNavigationPage}")
	private int maxNavigationPages;
	@Value("${com.shop.controller.MainPageController.maxProductOnPage}")
	private int maxProductOnPage;
	
	@Autowired
	public OfferController(OfferService offerService, CategoryService categoryService,
			NavigationPagesCreator navPagesCreator, Mapper<Product, LineItemDTO> mapper) {
		this.offerService = offerService;
		this.categoryService = categoryService;
		this.navPagesCreator = navPagesCreator;
		this.mapper = mapper;
	}

	@GetMapping
	public String displayMainPage(
	    @RequestParam(value = "categoryName", required = false) String categoryName,
	    @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	    Model model){
		
		Category category = new Category();
		
		if(isCategorySelected(categoryName)) {
			category.setCategoryName(categoryName);
			EntityPage<Product> paginateOffer = offerService.getPaginateOfferForClient(page,categoryName,maxProductOnPage);
			
			model.addAttribute("offer", createOfferToDisplay(paginateOffer));
			model.addAttribute("navigationPages",createNavPages(paginateOffer));
			model.addAttribute("categoryName", categoryName);
			model.addAttribute(new LineItemDTO());
		}
		
		model.addAttribute(category);
		model.addAttribute("categoriesList", categoryService.getAllCategories());
		return "user/offerForm";
	}

	private boolean isCategorySelected(String cName) {
		return cName != null && !cName.isEmpty();
	}

	private List<Integer> createNavPages(EntityPage<Product> paginateOffer) {
		return navPagesCreator.create(paginateOffer,maxNavigationPages);
	}

	private List<LineItemDTO> createOfferToDisplay(EntityPage<Product> paginateOffer) {
		return paginateOffer.getItems().stream().map(e -> mapper.convertEntityToDTO(e)).collect(Collectors.toList());
	}
	
}
