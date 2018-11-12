package com.shop.service;

import java.util.List;

import com.shop.model.entity.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	Category getCategoryByName(String categoryName);
}
