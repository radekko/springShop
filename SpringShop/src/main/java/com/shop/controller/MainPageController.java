package com.shop.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.mappers.IMapper;
import com.shop.model.entity.domain.LineItemDTO;
import com.shop.model.entity.persistent.Category;
import com.shop.model.entity.persistent.Product;
import com.shop.pagination.EntityPage;
import com.shop.pagination.NavigationPagesCreator;
import com.shop.service.CategoryService;
import com.shop.service.OfferService;

@Controller
@RequestMapping(value = "/main/displayOffer")
public class MainPageController {
	
	private OfferService offerService;
	private CategoryService categoryService;
	private NavigationPagesCreator navPagesCreator;
	private IMapper<Product, LineItemDTO> mapper;
	
	@Value("${com.shop.controller.MainPageController.maxNavigationPage}")
	private int maxNavigationPages;
	@Value("${com.shop.controller.MainPageController.maxProductOnPage}")
	private int maxProductOnPage;
	
	@Autowired
	public MainPageController(OfferService offerService, CategoryService categoryService,
			NavigationPagesCreator navPagesCreator, IMapper<Product, LineItemDTO> mapper) {
		this.offerService = offerService;
		this.categoryService = categoryService;
		this.navPagesCreator = navPagesCreator;
		this.mapper = mapper;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String afterSelectCategory(
	    @RequestParam(value = "categoryName")  Optional<String> categoryNameParam,
	    @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	    Model model){

		String categoryName = (categoryNameParam.filter(s -> !s.isEmpty()).isPresent()) ?
						categoryNameParam.get() : categoryService.getFirstCategory().getCategoryName();
				
		EntityPage<Product> paginateOffer = offerService.getPaginateOfferForClient(page,categoryName,maxProductOnPage);
		
		List<Integer> navPages = navPagesCreator.create(paginateOffer,maxNavigationPages);
		List<LineItemDTO> orderDTOlist = paginateOffer.getItems().stream()
				.map(e -> mapper.convertEntityToDTO(e)).collect(Collectors.toList());
		
		model.addAttribute("offer", orderDTOlist);
		model.addAttribute("navigationPages",navPages);
		model.addAttribute("categoriesList", categoryService.getAllCategories());
		model.addAttribute("categoryName", categoryName);
		model.addAttribute(new LineItemDTO());
		model.addAttribute(new Category());
		return "mainForm";
	}
}
