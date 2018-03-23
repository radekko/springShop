package com.shop.service;

import java.util.List;

import com.shop.dao.PaginationResult;
import com.shop.model.entity.persistent.Product;

public interface ProductService {
	void addProduct(Product product);
	List<Product> findAllProduct();
	PaginationResult paginateProducts(int page);
}
