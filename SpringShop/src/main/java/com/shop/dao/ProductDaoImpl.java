package com.shop.dao;

import static java.lang.Math.toIntExact;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.shop.model.entity.persistent.IEntity;
import com.shop.model.entity.persistent.Product;
import com.shop.pagination.EntityPage;

@Repository
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao{

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
		List<Product> list = selectPage(query,page,maxItemsOnPage);
		return new EntityPage<Product>(list,page,countTotalRecordsInGroup(category),maxItemsOnPage);
	}
	
	private int countTotalRecordsInGroup(IEntity groupEntity) {
		return toIntExact(em.createQuery(countInGroupQuery(groupEntity)).getSingleResult());
	}
	
	private CriteriaQuery<Long> countInGroupQuery(IEntity groupEntity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Product> root= query.from(clazz);
		query.select(cb.count(root));
		
		if(groupEntity != null) {
			Predicate p = cb.and(
					cb.equal(root.get(groupEntity.getClassNameStartWithLowerCase()), groupEntity));
			query.where(p);
		}
		return query;
	}
	
}
