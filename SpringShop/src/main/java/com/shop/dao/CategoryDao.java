package com.shop.dao;

import java.util.List;

import com.shop.model.entity.persistent.Category;

public interface CategoryDao {
	List<Category> findAllCategories();
	Category getCategoryByName(String categoryName);
	Category getFirstCategory();
}
