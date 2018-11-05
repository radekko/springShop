package com.shop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shop.model.entity.persistent.Category;

@Repository
public class CategoryDaoImpl extends AbstractDao<Integer, Category> implements CategoryDao {

	@Override
	public List<Category> findAllCategories() {
		return getAll();
	}
	@Override
	public Category getCategoryByName(String categoryName) {
		return selectUniqueEntityWithWhere("categoryName",categoryName);
	}
}
