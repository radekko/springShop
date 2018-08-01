package com.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.model.entity.domain.LineItem;
import com.shop.model.entity.persistent.Product;
import com.shop.pagination.Page;
/* Service which convert products to line items and display as offer*/
@Service
@Transactional
public class OfferServiceImpl implements OfferService {

    private ProductService productService;
	private CategoryService categoryService;
	private final static int INITIAL_STOCK_AMOUNT = 0;
	
	@Autowired 
	public OfferServiceImpl(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@Override
	public List<LineItem> getOfferForClient() {
		return productService.findAllProduct().stream().map(this::convertToLineItem).collect(Collectors.toList());
	}
	
	@Override
	public Page<LineItem> getPaginationOfferForClient(int page,String categoryName) {
		Page<Product> pageWithProducts = productService.getPaginateProducts(page,categoryService.getCategoryByName(categoryName));
		List<LineItem> lineItemsList = pageWithProducts.getItems().stream().map(p -> convertToLineItem((Product) p)).collect(Collectors.toList());
		Page<LineItem> pageWithLineItems = new Page<LineItem>(lineItemsList,pageWithProducts.getNavigationPages());
		return pageWithLineItems;
	}

	private LineItem convertToLineItem(Product p) {
	    return new LineItem(p.getName(),p.getUniqueProductCode(),p.getPrice(),INITIAL_STOCK_AMOUNT);
	}

}
