package com.shop.service;

import java.util.List;

import com.shop.model.entity.persistent.Category;
import com.shop.model.entity.persistent.Product;
import com.shop.pagination.Page;

public interface ProductService {
	void addProduct(Product product);
	List<Product> findAllProduct();
	Page getPaginateProducts(int page,Category category);
}
