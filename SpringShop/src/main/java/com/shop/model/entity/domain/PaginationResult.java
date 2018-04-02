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
	
	public PaginationResult(int totalRecords, int currentPage, List<E> entitiesOnChosenPage, int maxResult,
			int totalPages, int maxNavigationPage, List<Integer> navigationPages) {
		this.totalRecords = totalRecords;
		this.currentPage = currentPage;
		this.setEntitiesOnChosenPage(entitiesOnChosenPage);
		this.maxResult = maxResult;
		this.totalPages = totalPages;
		this.maxNavigationPage = maxNavigationPage;
		this.navigationPages = navigationPages;
	}

	public PaginationResult(PaginationResult<?> paginationResult, List<E> list) {
		this.setEntitiesOnChosenPage(list);
		this.totalRecords = paginationResult.totalRecords;
		this.currentPage = paginationResult.currentPage;
		this.maxResult = paginationResult.maxResult;
		this.totalPages = paginationResult.totalPages;
		this.maxNavigationPage = paginationResult.maxNavigationPage;
		this.navigationPages = paginationResult.navigationPages;
	}

	public List<E> getEntitiesOnChosenPage() {
		return entitiesOnChosenPage;
	}

	public void setEntitiesOnChosenPage(List<E> entitiesOnChosenPage) {
		this.entitiesOnChosenPage = entitiesOnChosenPage;
	}
}