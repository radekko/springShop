package com.shop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.shop.model.entity.persistent.IEntity;
import com.shop.model.entity.persistent.Order;
import com.shop.pagination.EntityPage;

@Repository
public class OrderDaoImpl extends AbstractDao<Integer, Order> implements OrderDao {

	@Override
	public EntityPage<Order> getOrdersOnPage(int page, int maxItemsOnPage, boolean isRealized) {
		CriteriaQuery<Order> query = selectOrders(null,isRealized);
		List<Order> list = selectPage(query,page,maxItemsOnPage);
		int totalRecords = Math.toIntExact(countRecords(null,isRealized));
		return new EntityPage<Order>(list,page,totalRecords,maxItemsOnPage);
	}

	@Override
	public EntityPage<Order> getOrdersOnPageForUser(int page, int maxItemsOnPage, IEntity userGroupField, boolean isRealized) {
		CriteriaQuery<Order> query = selectOrders(userGroupField,isRealized);
		List<Order> list = selectPage(query,page,maxItemsOnPage);
		int totalRecords = Math.toIntExact(countRecords(userGroupField,isRealized));
		return new EntityPage<Order>(list,page,totalRecords,maxItemsOnPage);
	}
	
	@Override
	public void changeRealizedFlag(String orderIdentifier, boolean isRealized) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<Order> criteria = builder.createCriteriaUpdate(Order.class);
		Root<Order> root = criteria.from(Order.class);
		criteria.set(root.get("isRealized"), isRealized);
		criteria.where(builder.equal(root.get("orderIdentifier"), orderIdentifier));
		em.createQuery(criteria).executeUpdate();
	}
	
	private CriteriaQuery<Order> selectOrders(IEntity userGroupField, boolean isRealized) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> query = cb.createQuery(clazz);
		Root<Order> root= query.from(clazz);
		query.select(root);
		
		root.fetch("setOfLineItems", JoinType.LEFT);
		query.distinct(true);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(root.get("isRealized"), isRealized));
		
		if(userGroupField != null) {
			predicates.add(
				cb.equal(root.get(userGroupField.getClassNameStartWithLowerCase()), userGroupField));
		}
		
		return query.where(cb.and(predicates.toArray(new Predicate[] {})));
	}
	
	private Long countRecords(IEntity groupEntity, boolean isRealized) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Order> root= query.from(clazz);
		query.select(cb.count(root));
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(root.get("isRealized"), isRealized));
		
		if(groupEntity != null) {
			predicates.add(
					cb.equal(root.get(groupEntity.getClassNameStartWithLowerCase()), groupEntity));
		}
		query.where(cb.and(predicates.toArray(new Predicate[] {})));
		return em.createQuery(query).getResultList().get(0);
	}
}
