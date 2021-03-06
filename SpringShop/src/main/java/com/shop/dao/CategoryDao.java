package com.shop.dao;

import java.util.List;

import com.shop.model.entity.Category;

public interface CategoryDao {
	List<Category> findAllCategories();
	Category getCategoryByName(String categoryName);
}
