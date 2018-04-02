package com.shop.model.entity.domain;

import java.util.List;

public class PaginationResult<E> {

	private int totalRecords;
	private int currentPage;
	private List<E> entitiesOnChosenPage;
	private int maxResult;
	private int totalPages;
	private int maxNavigationPage;
	private List<Integer> navigationPages;

	public PaginationResult() {}
	
	public PaginationResult(int currentPage,int totalPages, int totalRecords, int maxResult,
			 int maxNavigationPage,List<E> entitiesOnChosenPage, List<Integer> navigationPages) {
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.totalRecords = totalRecords;
		this.maxResult = maxResult;
		this.maxNavigationPage = maxNavigationPage;
		this.setEntitiesOnChosenPage(entitiesOnChosenPage);
		this.navigationPages = navigationPages;
	}

	public PaginationResult(PaginationResult<?> p, List<E> list) {
		this(p.currentPage,p.totalPages,p.totalRecords, p.maxResult,p.maxNavigationPage,list,p.navigationPages);
	}

	public List<E> getEntitiesOnChosenPage() {
		return entitiesOnChosenPage;
	}

	public void setEntitiesOnChosenPage(List<E> entitiesOnChosenPage) {
		this.entitiesOnChosenPage = entitiesOnChosenPage;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getMaxNavigationPage() {
		return maxNavigationPage;
	}

	public List<Integer> getNavigationPages() {
		return navigationPages;
	}
}