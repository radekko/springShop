package com.shop.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AbstractQuery<T> {
	
	@PersistenceContext
	protected EntityManager em;
	protected final Class<T> clazz;
	
	
	@SuppressWarnings("unchecked")
	public AbstractQuery() {
		this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}
	
	public CriteriaQuery<T> select() {
	    CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(clazz);
	    return query.select(query.from(clazz));
	}
	
	public CriteriaQuery<T> selectWhere(String columnName, Object columnValue) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(clazz);
		Root<T> root= query.from(clazz);
		query.select(root);
		
		Predicate p = cb.and(
				cb.equal(root.get(columnName), columnValue));
		return query.where(p);
	}
	
	public CriteriaQuery<T> selectWithFetch(String fetchColumnName) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(clazz);
		Root<T> root= query.from(clazz);
		query.select(root);
		
		root.fetch(fetchColumnName, JoinType.LEFT);
		query.distinct(true);
		return query;
	}

	public CriteriaQuery<T> selectWithWhereManyParam(Map<String,String> values) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(clazz);
		Root<T> root= query.from(clazz);
		query.select(root);
		
		for (Map.Entry<String, String> entry : values.entrySet()) {
		    Predicate p = cb.and(
		    		cb.equal(root.get(entry.getKey()), entry.getValue()));
		    query.where(p);
		}
		return query;
	}
	
}
