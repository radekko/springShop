package com.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.dao.AbstractDao;
import com.shop.model.entity.domain.PaginationResult;
import com.shop.model.entity.persistent.IEntity;
import com.shop.utils.NavigationPagesCreator;

@Service
public class PaginationServiceImpl<E> implements PaginationService<E> {
	
	private int totalRecords;
	private int totalPages;
	
	@Override
	public PaginationResult<E> getPaginationResult(int page, int maxResult,int maxNavigationPage, AbstractDao<?, E>  ab,
			String groupColumnName, IEntity groupEntity) {
		totalRecords = countTotalRecords(ab, groupColumnName,groupEntity);
		totalPages = calcTotalPages(maxResult);

		if(page > totalPages)
			page = totalPages;
		
		return new PaginationResult<E>(page,totalPages,totalRecords,maxResult,maxNavigationPage,
				getEntityToCurPage(page, maxResult,ab,groupColumnName,groupEntity),getNavPages(page, maxNavigationPage));
	}

	private List<Integer> getNavPages(int page, int maxNavigationPage) {
		return NavigationPagesCreator.createNavigationPages(page,maxNavigationPage,totalPages);
	}

	private int calcTotalPages(int maxResult) {
		return totalRecords % maxResult == 0 ? totalRecords/maxResult : (totalRecords/maxResult) + 1;
	}

	private int countTotalRecords(AbstractDao<?, ?> ab,String groupColumnName,IEntity groupEntity) {
		return ab.countTotalRecords(groupColumnName,groupEntity);
	}

	private List<E> getEntityToCurPage(int page, int maxResult, AbstractDao<?, E> ab,String groupColumnName,IEntity groupEntity) {
		int startIndex = (page - 1 ) * maxResult;
		return ab.selectEntityToCurrentPage(startIndex, maxResult,groupColumnName,groupEntity);
	}

}
