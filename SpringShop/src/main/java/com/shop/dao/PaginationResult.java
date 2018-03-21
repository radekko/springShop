package com.shop.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

public class PaginationResult<E> {

	private int totalRecords;
	private int currentPage;
	private List list;
	private int maxResult;
	private int totalPages;

	private int maxNavigationPage;

	private List<Integer> navigationPages;

	public <E> PaginationResult(Criteria selectCriteria, Criteria projectionCriteria, int page, int maxResult,
			int maxNavigationPage) {
		int previousAmount = (page * maxResult) - maxResult;
		selectCriteria.setFirstResult(previousAmount);
		selectCriteria.setMaxResults(maxResult);
		List<E> currPrintedPage = selectCriteria.list();

		projectionCriteria.setProjection(Projections.rowCount());
		Long count = (Long) projectionCriteria.uniqueResult();
		Integer i = (int) (long) count;

		this.totalRecords = i;
		this.currentPage = page;
		this.list = currPrintedPage;
		this.maxResult = maxResult;

		this.totalPages = (this.totalRecords / this.maxResult) + 1;
		this.maxNavigationPage = maxNavigationPage;

		if (maxNavigationPage < totalPages) {
			this.maxNavigationPage = maxNavigationPage;
		}

		this.calcNavigationPages();
	}

	public PaginationResult(PaginationResult<E> p, List list) {
		this.list = list;
		this.totalRecords = p.totalRecords;
		this.currentPage = p.currentPage;
		this.maxResult = p.maxResult;
		this.totalPages = p.totalPages;
		this.maxNavigationPage = p.maxNavigationPage;
		this.navigationPages = p.navigationPages;
	}

	private void calcNavigationPages() {
		this.navigationPages = new ArrayList<Integer>();
		int start = currentPage;
		int count = maxNavigationPage;

		navigationPages.add(1);

		if (currentPage > 0 && currentPage < 4) {
			start = 2;
			for (int i = 1; i <= count; i++) {
				if (start > totalPages)
					break;
				
				navigationPages.add(start);
				start++;
			}
		} else {
			start = currentPage;
			for (int i = 1; i <= count; i++) {
				if (start >= totalPages)
					break;
				
				navigationPages.add(start);
				System.out.println("start: "+start + " totalPages: " + totalPages);
				start++;
			}
		}

	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<E> getList() {
		return list;
	}

	public void SetList(List<E> list) {
		this.list = list;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public List<Integer> getNavigationPages() {
		return navigationPages;
	}

}