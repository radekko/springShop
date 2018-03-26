package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.dao.AbstractDao;
import com.shop.model.entity.domain.PaginationResult;

@Service
public class PaginationService<E> {
	
	private PaginationResult<E> pr;
	private int totalRecords;
	private int totalPages;
	

	public PaginationService( ) {
		pr = new PaginationResult<E>();
	}

	public PaginationResult<E> getPaginationResult(int page, int maxResult,int maxNavigationPage, AbstractDao<?, E>  ab) {
		pr.setEntitiesOnChosenPage(selectEntityToCurrentPage(page, maxResult,ab));
		totalRecords = countTotalRecords(ab);
		totalPages = (totalRecords/ maxResult) + 1;
		
		pr.setTotalRecords(totalRecords);
		pr.setCurrentPage(page);
		pr.setMaxResult(maxResult);
		pr.setTotalPages(totalPages);
		pr.setMaxNavigationPage(maxNavigationPage);
		pr.setNavigationPages(calcNavigationPages(page,maxNavigationPage));
		
		return pr;
	}

	private int countTotalRecords(AbstractDao<?, ?> ab) {
		return ab.countTotalRecords();
	}

	private List<E> selectEntityToCurrentPage(int page, int maxResult, AbstractDao<?, E> ab) {
		int startIndex = (page - 1 ) * maxResult;
		return ab.selectEntityToCurrentPage(startIndex, maxResult);
	}

	private List<Integer> calcNavigationPages(int page, int maxNavigationPage) {
		List<Integer> navigationPages = new ArrayList<Integer>();
		int start = page;
		int count = maxNavigationPage;
		int total = totalPages;
		navigationPages.add(1);

		if (page <= maxNavigationPage - total + maxNavigationPage ) { //1
			start = 2;
			for (int i = 1; i <= maxNavigationPage - 1; i++) {
				navigationPages.add(start);
				start++;
			}
		} else if (page >= total - page + maxNavigationPage ) {  //3
			start = total - (maxNavigationPage - 2);
			for (int i = 1; i <= maxNavigationPage - 1; i++) {
				navigationPages.add(start);
				start++;
			}
		}
		else {  //2
			start = page - 1;
			for (int i = 1; i <= maxNavigationPage - 1; i++) {
				navigationPages.add(start);
				start++;
			}
		}
		return navigationPages;
	}

}
