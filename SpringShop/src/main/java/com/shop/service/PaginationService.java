package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.AbstractDao;
import com.shop.model.entity.domain.PaginationResult;
import com.shop.utils.NavigationPagesCreator;

@Service
public class PaginationService<E> {
	
	@Autowired
	private PaginationResult<E> pr;
	private int totalRecords;
	private int totalPages;
	
	public PaginationResult<E> getPaginationResult(int page, int maxResult,int maxNavigationPage, AbstractDao<?, E>  ab) {
		pr.setCurrentPage(page);
		pr.setMaxResult(maxResult);
		pr.setMaxNavigationPage(maxNavigationPage);
		pr.setEntitiesOnChosenPage(getEntityToCurPage(page, maxResult,ab));
		
		totalRecords = countTotalRecords(ab);
		pr.setTotalRecords(totalRecords);
		totalPages = calcTotalPages(maxResult);
		pr.setTotalPages(totalPages);
		pr.setNavigationPages(getNavPages(page, maxNavigationPage));
		
		return pr;
	}

	private List<Integer> getNavPages(int page, int maxNavigationPage) {
		return NavigationPagesCreator.createNavigationPages(page,maxNavigationPage,totalPages);
	}

	private int calcTotalPages(int maxResult) {
		return totalRecords % maxResult == 0 ? totalRecords/maxResult : (totalRecords/maxResult) + 1;
	}

	private int countTotalRecords(AbstractDao<?, ?> ab) {
		return ab.countTotalRecords();
	}

	private List<E> getEntityToCurPage(int page, int maxResult, AbstractDao<?, E> ab) {
		int startIndex = (page - 1 ) * maxResult;
		return ab.selectEntityToCurrentPage(startIndex, maxResult);
	}

}
