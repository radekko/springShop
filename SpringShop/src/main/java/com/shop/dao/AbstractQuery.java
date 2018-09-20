package com.shop.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.shop.model.entity.persistent.IEntity;

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
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(clazz);
		Root<T> root= query.from(clazz);
		query.select(root);
		
		Predicate p = em.getCriteriaBuilder().and(
				em.getCriteriaBuilder().equal(root.get(columnName), columnValue));
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

	public CriteriaQuery<T> selectWhereMapParam(Map<String,String> values) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(clazz);
		Root<T> root= query.from(clazz);
		query.select(root);
		
		for (Map.Entry<String, String> entry : values.entrySet()) {
		    Predicate p = em.getCriteriaBuilder().and(
		    		em.getCriteriaBuilder().equal(root.get(entry.getKey()), entry.getValue()));
		    query.where(p);
		}
		return query;
	}
	
	public CriteriaQuery<Long> countForGroupQuery(IEntity groupEntity) {
		CriteriaQuery<Long> query = em.getCriteriaBuilder().createQuery(Long.class);
		Root<T> root= query.from(clazz);
		query.select(em.getCriteriaBuilder().count(root));
		if(groupEntity != null) {
			Predicate p = em.getCriteriaBuilder().and(
					em.getCriteriaBuilder().equal(root.get(extractGroupingFieldName(groupEntity)), groupEntity));
			query.where(p);
		}
		return query;
	}
	
	protected String extractGroupingFieldName(IEntity groupEntity) {
		String fieldName = groupEntity.getClass().getSimpleName();
		return Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
	}
	
}
