package com.shop.service;

import java.util.List;

import com.shop.model.entity.persistent.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	Category getFirstCategory();
}
