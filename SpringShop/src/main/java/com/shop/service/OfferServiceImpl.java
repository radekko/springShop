package com.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.model.entity.domain.LineItem;
import com.shop.model.entity.persistent.Product;
import com.shop.pagination.DTOPageWithNavigation;
import com.shop.pagination.EntityPage;

@Service
@Transactional(readOnly=true)
public class OfferServiceImpl implements OfferService {

    private ProductService productService;
	private CategoryService categoryService;
	private final static int INITIAL_STOCK_AMOUNT = 0;
	
	@Value("${com.shop.service.OfferService.maxNavigationPage}")
	private int maxNavigationPages;
	
	@Autowired 
	public OfferServiceImpl(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@Override
	public List<LineItem> getOfferForClient() {
		return productService.findAllProduct().stream().map(this::convertProductToLineItem).collect(Collectors.toList());
	}

	@Override
	public DTOPageWithNavigation<LineItem> getPaginateOfferForClient(int page,String categoryName) {
		EntityPage<Product> pageWithProducts = 
				productService.getPaginateProducts(page,categoryService.getCategoryByName(categoryName));
		
		DTOPageWithNavigation<LineItem> lineItemsPageWithNavigation =
				new DTOPageWithNavigation<LineItem>(pageWithProducts,maxNavigationPages, this::convertProductToLineItem);
		return lineItemsPageWithNavigation;
	}

	private LineItem convertProductToLineItem(Product p) {
	    return new LineItem(p.getName(),p.getUniqueProductCode(),p.getPrice(),INITIAL_STOCK_AMOUNT);
	}
}
