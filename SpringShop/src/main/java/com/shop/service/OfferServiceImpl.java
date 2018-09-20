package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.model.entity.persistent.Product;
import com.shop.pagination.EntityPage;

@Service
@Transactional(readOnly=true)
public class OfferServiceImpl implements OfferService {

    private ProductService productService;
	private CategoryService categoryService;

	@Autowired 
	public OfferServiceImpl(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@Override
	public EntityPage<Product> getPaginateOfferForClient(int page,String categoryName, int maxProductOnPage) {
		return productService.getPaginateProducts(page,categoryService.getCategoryByName(categoryName),maxProductOnPage);
	}
	
}
