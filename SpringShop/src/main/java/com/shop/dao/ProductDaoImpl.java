package com.shop.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.shop.model.entity.persistent.Category;
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
	public Product getByUniqueCode(String uniquecode) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("uniqueProductCode", uniquecode));
		return (Product) criteria.uniqueResult();
	}
	
	@Override
	public EntityPage<Product> getProductsOnPage(int page, int maxProductOnPage, Category category) {
		int startIndex = (page - 1 ) * maxProductOnPage;
		List<Product> productsOnPage = getItemsOnPage(startIndex,maxProductOnPage,category);
		return new EntityPage<Product>(
				productsOnPage,page,countTotalRecordsForGroup(category),maxProductOnPage);
	}
	
}
