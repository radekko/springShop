package com.shop.dao;

import java.util.List;

import com.shop.model.entity.persistent.Product;

public interface ProductDao {
	void addProduct(Product product);
	List<Product> findAllProduct();
	Product getByUniqueCode(String uniquecode);
	List<Product> selectEntityToCurrentPage(int page, int maxResult);
	Long countTotalRecords();
}
