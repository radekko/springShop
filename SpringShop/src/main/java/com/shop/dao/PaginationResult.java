package com.shop.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

public class PaginationResult<E> {

	private Long totalRecords;
	private int currentPage;
	private List<?> entitiesOnChosenPage;
	private int maxResult;
	private Long totalPages;
	private int maxNavigationPage;
	private List<Integer> navigationPages;

	public PaginationResult(Criteria selectCriteria, Criteria projectionCriteria, int page, int maxResult,
			int maxNavigationPage) {
		
		this.entitiesOnChosenPage = selectEntityToCurrentPage(selectCriteria, page, maxResult);
		this.totalRecords = countTotalRecords(projectionCriteria);
		this.currentPage = page;
		this.maxResult = maxResult;
		this.totalPages = (this.totalRecords / this.maxResult) + 1;
		this.maxNavigationPage = maxNavigationPage;
		this.navigationPages = calcNavigationPages(page,maxNavigationPage);
	}

	private Long countTotalRecords(Criteria projectionCriteria) {
		projectionCriteria.setProjection(Projections.rowCount());
		return (Long) projectionCriteria.uniqueResult();
	}

	private List<?> selectEntityToCurrentPage(Criteria selectCriteria, int page, int maxResult) {
		int previousAmount = (page * maxResult) - maxResult;
		selectCriteria.setFirstResult(previousAmount);
		selectCriteria.setMaxResults(maxResult);
		return selectCriteria.list();
	}

	public PaginationResult(PaginationResult<?> productList, List<?> list) {
		this.entitiesOnChosenPage = list;
		this.totalRecords = productList.totalRecords;
		this.currentPage = productList.currentPage;
		this.maxResult = productList.maxResult;
		this.totalPages = productList.totalPages;
		this.maxNavigationPage = productList.maxNavigationPage;
		this.navigationPages = productList.navigationPages;
	}

	private List<Integer> calcNavigationPages(int page, int maxNavigationPage) {
		List<Integer> navigationPages = new ArrayList<Integer>();
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
				start++;
			}
		}
		return navigationPages;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public Long getTotxalRecords() {
		return totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<E> getList() {
		return (List<E>) entitiesOnChosenPage;
	}

	public void SetList(List<E> list) {
		this.entitiesOnChosenPage = list;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public List<Integer> getNavigationPages() {
		return navigationPages;
	}

}