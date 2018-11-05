package com.shop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.shop.model.entity.persistent.IEntity;
import com.shop.model.entity.persistent.Order;
import com.shop.pagination.EntityPage;

@Repository
public class OrderDaoImpl extends AbstractDaoWithPagination<Integer, Order> implements OrderDao {

	@Override
	public EntityPage<Order> getOrdersOnPage(int page, int maxItemsOnPage, boolean isRealized) {
		CriteriaQuery<Order> query = selectOrders(null,isRealized);
		List<Order> list = createPage(query,page,maxItemsOnPage);
		return new EntityPage<Order>(list,page,countTotalRecords(),maxItemsOnPage);
	}

	@Override
	public EntityPage<Order> getOrdersOnPageForUser(int page, int maxItemsOnPage, IEntity userGroupField, boolean isRealized) {
		CriteriaQuery<Order> query = selectOrders(userGroupField,isRealized);
		List<Order> list = createPage(query,page,maxItemsOnPage);
		return new EntityPage<Order>(list,page,countTotalRecordsInGroup(userGroupField),maxItemsOnPage);
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
}
