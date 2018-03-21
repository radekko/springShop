package com.shop.dao;

import java.util.List;

import com.shop.model.entity.persistent.Product;

public interface ProductDao {
	void addProduct(Product product);
	List<Product> findAllProduct();
	PaginationResult<Product> paginateProducts(int page);
	Product getByUniqueCode(String uniquecode);
}
