package com.shop.dao;

import java.util.List;

import org.hibernate.Query;
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
		Query query= getSession().createQuery("from Category where categoryName=:categoryName");
		query.setParameter("categoryName", categoryName);
		return (Category) query.uniqueResult(); 
	}
	@Override
	public Category getFirstCategory() {
		return getFirstResult();
	}
}
