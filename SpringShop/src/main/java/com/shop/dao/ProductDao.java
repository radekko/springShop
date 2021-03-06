package com.shop.dao;

import java.util.List;

import com.shop.model.entity.IEntity;
import com.shop.model.entity.Product;
import com.shop.pagination.EntityPage;

public interface ProductDao {
	void addProduct(Product product);
	List<Product> findAllProduct();
	Product getByUniqueCode(String uniquecode);
	EntityPage<Product> getPageInCategory(int page, int maxItemsOnPage, IEntity category);
}
