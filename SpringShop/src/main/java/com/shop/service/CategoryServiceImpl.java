package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.CategoryDao;
import com.shop.model.entity.persistent.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private CategoryDao cad;
	
	@Autowired
	public CategoryServiceImpl(CategoryDao cad) {
		this.cad = cad;
	}

	@Override
	public List<Category> getAllCategories() {
		return cad.findAllCategories();
	}
	
	@Override
	public Category getFirstCategory() {
		return cad.getFirstCategory();
	}
	@Override
	public Category getCategoryByName(String categoryName) {
		return cad.getCategoryByName(categoryName);
	}
}
