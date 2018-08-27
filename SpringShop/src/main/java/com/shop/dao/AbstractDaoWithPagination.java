package com.shop.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.shop.model.entity.persistent.IEntity;
import com.shop.pagination.EntityPage;

public class AbstractDaoWithPagination<PK extends Serializable, T> extends AbstractDao<PK, T>{

	public EntityPage<T> getItemsOnPage(int page, int itemsOnPage,IEntity groupEntity) {
		int startIndex = (page - 1 ) * itemsOnPage;
		List<T> entities = selectEntityToCurrentPage(startIndex,itemsOnPage,groupEntity);
		return new EntityPage<T>(entities,page,countTotalRecords(groupEntity),itemsOnPage);
	}
	
	@SuppressWarnings("unchecked")
	private List<T> selectEntityToCurrentPage(int from, int to,IEntity groupEntity) {
		Criteria selectCriteria = createEntityCriteria();
		if(groupEntity != null)
			selectCriteria.add(Restrictions.eq(extractGroupingFieldName(groupEntity), groupEntity));
		
		selectCriteria.setFirstResult(from);
		selectCriteria.setMaxResults(to);
		return selectCriteria.list();
	}
}
