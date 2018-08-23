package com.shop.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.shop.model.entity.persistent.IEntity;

public abstract class AbstractDaoWithPagination<PK extends Serializable, T> extends AbstractDao<PK, T>{
	
	public List<T> getItemsOnPage(int page, int size,IEntity groupEntity) {
		return selectEntityToCurrentPage(page,size,groupEntity);
	}
	
	public List<T> getItemsOnPage(int page, int size) {
		return selectEntityToCurrentPage(page,size,null);
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> selectEntityToCurrentPage(int from, int to,IEntity groupEntity) {
		Criteria selectCriteria = createEntityCriteria();
		
		if(groupEntity != null)
			selectCriteria.add(Restrictions.eq(extractGroupingFieldName(groupEntity), groupEntity));
		
		selectCriteria.setFirstResult(from);
		selectCriteria.setMaxResults(to);
		return selectCriteria.list();
	}
}
