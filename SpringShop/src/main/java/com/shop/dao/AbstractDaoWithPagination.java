package com.shop.dao;

import static java.lang.Math.toIntExact;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.shop.model.entity.persistent.IEntity;

public abstract class AbstractDaoWithPagination<PK extends Serializable, T> extends AbstractDao<PK, T>{
	
	public List<T> getItemsOnPage(int page, int size,IEntity groupEntity) {
		return selectEntityToCurrentPage(page,size,groupEntity);
	}
	
	public int countTotalRecordsForGroup(IEntity groupEntity) {
		Criteria projectionCriteria = createEntityCriteria();
		projectionCriteria.add(Restrictions.eq(extractGroupingFieldName(groupEntity), groupEntity));
		projectionCriteria.setProjection(Projections.rowCount());
		Long l = (Long) projectionCriteria.uniqueResult();
		return toIntExact(l);
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
	
	public <E> List<E> selectFirstResult() {
		return selectEntityToCurrentPage(0,1,null);
	}
	
	private String extractGroupingFieldName(IEntity groupEntity) {
		String fieldName = groupEntity.getClass().getSimpleName();
		return Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
	}
}
