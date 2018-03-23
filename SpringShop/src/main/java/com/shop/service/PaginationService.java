package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.dao.AbstractDao;
import com.shop.dao.PaginationResult;

@Service
public class PaginationService<E> {
	
	PaginationResult pr;
	private Long count;
	

	public PaginationService( ) {
		pr = new PaginationResult();
	}

	public PaginationResult getPaginationResult(int page, int maxResult,int maxNavigationPage, AbstractDao<?, E>  ab) {
		List<E> l = (List<E>) selectEntityToCurrentPage(page, maxResult,ab);
		pr.setEntitiesOnChosenPage(l);
		count = countTotalRecords(ab);
		pr.setTotalRecords(count);
		pr.setCurrentPage(page);
		pr.setMaxResult(maxResult);
		pr.setTotalPages((count/ maxResult) + 1);
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
		int start = pr.getCurrentPage();
		int count = maxNavigationPage;
		navigationPages.add(1);

		if (pr.getCurrentPage() > 0 && pr.getCurrentPage() < 4) {
			start = 2;
			for (int i = 1; i <= count; i++) {
				if (start > pr.getTotalPages())
					break;
				
				navigationPages.add(start);
				start++;
			}
		} else {
			start = pr.getCurrentPage();
			for (int i = 1; i <= count; i++) {
				if (start >= pr.getTotalPages())
					break;
				
				navigationPages.add(start);
				start++;
			}
		}
		return navigationPages;
	}

}
