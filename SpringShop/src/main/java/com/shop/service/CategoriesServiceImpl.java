package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.CategoryDao;
import com.shop.model.entity.persistent.Category;

@Service
@Transactional
public class CategoriesServiceImpl implements CategoryService {

	@Autowired
	CategoryDao cad;
	
	@Override
	public List<Category> getAllCategories() {
		return cad.findAllCategories();
	}
	
	@Override
	public Category getFirstCategory() {
		return cad.getFirstCategory();
	}
}