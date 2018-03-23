package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.dao.AbstractDao;
import com.shop.dao.PaginationResult;

@Service
public class PaginationService<E> {
	
	private PaginationResult<E> pr;
	private Long count;
	private Long totalPages;
	

	public PaginationService( ) {
		pr = new PaginationResult<E>();
	}

	public PaginationResult<E> getPaginationResult(int page, int maxResult,int maxNavigationPage, AbstractDao<?, E>  ab) {
		pr.setEntitiesOnChosenPage(selectEntityToCurrentPage(page, maxResult,ab));
		count = countTotalRecords(ab);
		pr.setTotalRecords(count);
		pr.setCurrentPage(page);
		pr.setMaxResult(maxResult);
		totalPages = (count/ maxResult) + 1;
		pr.setTotalPages(totalPages);
		pr.setMaxNavigationPage(maxNavigationPage);
		pr.setNavigationPages(calcNavigationPages(page,maxNavigationPage));
		
		return pr;
	}

	private Long countTotalRecords(AbstractDao<?, ?> ab) {
		return ab.countTotalRecords();
	}

	private List<E> selectEntityToCurrentPage(int page, int maxResult, AbstractDao<?, E> ab) {
		return ab.selectEntityToCurrentPage(page, maxResult);
	}

	private List<Integer> calcNavigationPages(int page, int maxNavigationPage) {
		List<Integer> navigationPages = new ArrayList<Integer>();
		int start = page;
		int count = maxNavigationPage;
		navigationPages.add(1);

		if (page > 0 && page < 4) {
			start = 2;
			for (int i = 1; i <= count; i++) {
				if (start > totalPages)
					break;
				
				navigationPages.add(start);
				start++;
			}
		} else {
			start = page;
			for (int i = 1; i <= count; i++) {
				if (start >= totalPages)
					break;
				
				navigationPages.add(start);
				start++;
			}
		}
		return navigationPages;
	}

}
