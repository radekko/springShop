package com.shop.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import com.shop.model.entity.persistent.IEntity;
import com.shop.model.entity.persistent.Product;
import com.shop.pagination.EntityPage;

@Repository
public class ProductDaoImpl extends AbstractDaoWithPagination<Integer, Product> implements ProductDao{

	@Override
	public void addProduct(Product product) {
	}

	@Override
	public List<Product> findAllProduct() {
		return getAll();
	}
	@Override
	public Product getByUniqueCode(String uniqueProductcode) {
		return selectUniqueEntityWithWhere("uniqueProductCode",uniqueProductcode);
	}
	
	@Override
	public EntityPage<Product> getPageInCategory(int page, int maxItemsOnPage, IEntity category) {
		CriteriaQuery<Product> query = selectWhere(category.getClassNameStartWithLowerCase(),category);
		List<Product> list = createPage(query,page,maxItemsOnPage);
		return new EntityPage<Product>(list,page,countTotalRecordsInGroup(category),maxItemsOnPage);
	}
	
}
