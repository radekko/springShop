package com.shop.dao;

import java.util.List;

import com.shop.model.entity.persistent.Category;
import com.shop.model.entity.persistent.IEntity;
import com.shop.model.entity.persistent.Product;

public interface ProductDao {
	void addProduct(Product product);
	List<Product> findAllProduct();
	Product getByUniqueCode(String uniquecode);
	List<Product> getProductsOnPage(int page, int maxProductOnSite, Category category);
	int countTotalRecordsForGroup(IEntity groupEntity);
}
