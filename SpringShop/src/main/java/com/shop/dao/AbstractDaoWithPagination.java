package com.shop.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

public class AbstractDaoWithPagination<PK extends Serializable, T> extends AbstractDao<PK, T>{

	public List<T> createPage(CriteriaQuery<T> query,int page, int itemsOnPage){
		int from = fromIndex(page,itemsOnPage);
		return em.createQuery(query).setFirstResult(from).setMaxResults(itemsOnPage).getResultList();
	}
	
	private int fromIndex(int page, int itemsOnPage) {
		return (page - 1 ) * itemsOnPage;
	}

}
