package com.shop.service;

import java.util.List;

import com.shop.model.entity.domain.PaginationResult;
import com.shop.model.entity.persistent.Product;

public interface ProductService {
	void addProduct(Product product);
	List<Product> findAllProduct();
	PaginationResult<Product> getPaginateProducts(int page);
}
