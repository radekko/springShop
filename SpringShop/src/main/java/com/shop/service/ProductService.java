package com.shop.service;

import java.util.List;

import com.shop.model.entity.Category;
import com.shop.model.entity.Product;
import com.shop.pagination.EntityPage;

public interface ProductService {
	void addProduct(Product product);
	List<Product> findAllProduct();
	EntityPage<Product> getPaginateProducts(int page, Category category, int maxProductOnPage);
}
