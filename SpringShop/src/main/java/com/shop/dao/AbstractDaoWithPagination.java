package com.shop.dao;

import java.io.Serializable;
import java.util.List;

import com.shop.model.entity.persistent.IEntity;
import com.shop.pagination.EntityPage;

public class AbstractDaoWithPagination<PK extends Serializable, T> extends AbstractDao<PK, T>{

	public EntityPage<T> createEntityPageIncludingGroup(int page, int itemsOnPage,IEntity groupEntity) {
		int from = fromIndex(page,itemsOnPage);
		List<T> entities = em.createQuery(selectWhere(extractGroupingFieldName(groupEntity),groupEntity))
				.setFirstResult(from).setMaxResults(itemsOnPage).getResultList();
		return new EntityPage<T>(entities,page,countTotalRecordsForGroup(groupEntity),itemsOnPage);
	}

	public EntityPage<T> createEntityPageIncludingFetch(int page, int itemsOnPage, String fetchColumnName) {
		int from = fromIndex(page,itemsOnPage);
		List<T> entities = em.createQuery(selectWithFetch(fetchColumnName))
				.setFirstResult(from).setMaxResults(itemsOnPage).getResultList();
		return new EntityPage<T>(entities,page,countTotalRecordsForGroup(null),itemsOnPage);
	}
	
	public EntityPage<T> createEntityPageIncludingFetchAndWhere(
			int page, int itemsOnPage, String fetchColumnName,String columnName, IEntity columnValue) {
		int from = fromIndex(page,itemsOnPage);
		List<T> entities = em.createQuery(selectWithWhereAndFetch(fetchColumnName, columnName, columnValue))
				.setFirstResult(from).setMaxResults(itemsOnPage).getResultList();
		return new EntityPage<T>(entities,page,countTotalRecordsForGroup(columnValue),itemsOnPage);
	}
	
	private int fromIndex(int page, int itemsOnPage) {
		return (page - 1 ) * itemsOnPage;
	}

}
