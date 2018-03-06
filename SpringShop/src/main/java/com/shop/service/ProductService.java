package com.shop.service;

import java.util.List;

import com.shop.model.Product;

public interface ProductService {
	void addProduct(Product product);
	List<Product> findAllProduct();
}
