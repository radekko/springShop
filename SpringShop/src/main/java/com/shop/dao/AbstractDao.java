package com.shop.dao;

import static java.lang.Math.toIntExact;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shop.model.entity.persistent.IEntity;

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
	
	public int countTotalRecords() {
		return countTotalRecordsInGroup(null);
	}
	
	public int countTotalRecordsInGroup(IEntity groupEntity) {
		return toIntExact(em.createQuery(countInGroup(groupEntity)).getSingleResult());
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
}
