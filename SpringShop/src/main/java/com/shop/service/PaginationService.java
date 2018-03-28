package com.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.dao.AbstractDao;
import com.shop.model.entity.domain.PaginationResult;

@Service
public class PaginationService<E> {
	
	private PaginationResult<E> pr;
	private int totalRecords;
	private int totalPages;
	
	public PaginationResult<E> getPaginationResult(int page, int maxResult,int maxNavigationPage, AbstractDao<?, E>  ab) {
		pr = new PaginationResult<E>();
		pr.setCurrentPage(page);
		pr.setMaxResult(maxResult);
		pr.setMaxNavigationPage(maxNavigationPage);
		pr.setEntitiesOnChosenPage(selectEntityToCurrentPage(page, maxResult,ab));
		
		totalRecords = countTotalRecords(ab);
		pr.setTotalRecords(totalRecords);
		totalPages = (totalRecords % maxResult == 0 ? totalRecords/maxResult : (totalRecords/maxResult) + 1);
		pr.setTotalPages(totalPages);
		pr.setNavigationPages(NavigationPagesCreator.createNavigationPages(page,maxNavigationPage,totalPages));
		
		return pr;
	}

	private int countTotalRecords(AbstractDao<?, ?> ab) {
		return ab.countTotalRecords();
	}

	private List<E> selectEntityToCurrentPage(int page, int maxResult, AbstractDao<?, E> ab) {
		int startIndex = (page - 1 ) * maxResult;
		return ab.selectEntityToCurrentPage(startIndex, maxResult);
	}

}
