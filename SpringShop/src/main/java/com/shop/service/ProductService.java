package com.shop.service;

import java.util.List;

import com.shop.model.entity.persistent.Category;
import com.shop.model.entity.persistent.Product;
import com.shop.pagination.EntityPage;

public interface ProductService {
	void addProduct(Product product);
	List<Product> findAllProduct();
	EntityPage<Product> getPaginateProducts(int page,Category category);
}
