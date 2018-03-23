package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.dao.PaginationResult;
import com.shop.dao.ProductDao;
import com.shop.model.entity.persistent.Product;

@Service
public class PaginationService {
	
	PaginationResult pr;
	private ProductDao ab;
	private Long count;
	

	public <T> PaginationService( ) {
		pr = new PaginationResult();
	}

	public PaginationResult getPaginationResult(int page, int maxResult,int maxNavigationPage, ProductDao  ab) {
		List<Product> l = selectEntityToCurrentPage(page, maxResult,ab);
		System.out.println("size: "+ l.size());
		pr.setEntitiesOnChosenPage(l);
		count = countTotalRecords(ab);
		pr.setTotalRecords(count);
		pr.setCurrentPage(page);
		pr.setMaxResult(maxResult);
		pr.setTotalPages((count/ maxResult) + 1);
		pr.setMaxNavigationPage(maxNavigationPage);
		pr.setNavigationPages(calcNavigationPages(page,maxNavigationPage));
		this.ab =  ab;
		
		return pr;
	}

	private Long countTotalRecords(ProductDao ab) {
		return ab.countTotalRecords();
	}

	private List<Product> selectEntityToCurrentPage(int page, int maxResult, ProductDao ab) {
		return  ab.selectEntityToCurrentPage(page, maxResult);
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
