package com.shop.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaQuery;

public class AbstractDao<PK extends Serializable, T> extends AbstractQuery<T>{

	public AbstractDao() {}

	public List<T> getAll() {
		return em.createQuery(select()).getResultList();
	}
	
	public T selectUniqueEntityWithWhere(String columnName, String searchValue) {
		return em.createQuery(selectWhere(columnName,searchValue)).getSingleResult();
	}

	public List<T> selectListOfEntityWithWhere(String columnName, String searchValue){
		return em.createQuery(selectWhere(columnName,searchValue)).getResultList();
	}
	
	public List<T> selectListOfEntityWithMultiWhere(Map<String,String> values){
		return em.createQuery(selectWithWhereManyParam(values)).getResultList();
	}
	
	public void persist(T entity) {
		em.persist(entity);
	}

	public void save(T entity) {
		em.merge(entity);
	}

	public void delete(T entity) {
		em.remove(entity);
	}
	
	public List<T> selectPage(CriteriaQuery<T> query,int page, int itemsOnPage){
		int from = fromIndex(page,itemsOnPage);
		return em.createQuery(query).setFirstResult(from).setMaxResults(itemsOnPage).getResultList();
	}
	
	private int fromIndex(int page, int itemsOnPage) {
		return (page - 1 ) * itemsOnPage;
	}
}