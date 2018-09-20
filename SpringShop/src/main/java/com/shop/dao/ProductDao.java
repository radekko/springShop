package com.shop.dao;

import java.util.List;

import com.shop.model.entity.persistent.Category;
import com.shop.model.entity.persistent.IEntity;
import com.shop.model.entity.persistent.Product;
import com.shop.pagination.EntityPage;

public interface ProductDao {
	void addProduct(Product product);
	List<Product> findAllProduct();
	Product getByUniqueCode(String uniquecode);
	EntityPage<Product> getPageInCategory(int page, int maxProductOnSite, Category category);
	int countTotalRecordsForGroup(IEntity groupEntity);
}
