package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.CategoryDao;
import com.shop.model.entity.Category;

@Service
@Transactional(readOnly=true)
public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao;
	
	@Autowired
	public CategoryServiceImpl(CategoryDao cad) {
		this.categoryDao = cad;
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.findAllCategories();
	}
	
	@Override
	public Category getCategoryByName(String categoryName) {
		return categoryDao.getCategoryByName(categoryName);
	}
}
