package com.shop.dao;

import java.util.List;

public class PaginationResult {

	private Long totalRecords;
	private int currentPage;
	private List<?> entitiesOnChosenPage;
	private int maxResult;
	private Long totalPages;
	private int maxNavigationPage;
	private List<Integer> navigationPages;

	public PaginationResult() {
	}
	
	public PaginationResult(PaginationResult productList, List<?> list) {
		this.entitiesOnChosenPage = list;
		this.totalRecords = productList.totalRecords;
		this.currentPage = productList.currentPage;
		this.maxResult = productList.maxResult;
		this.totalPages = productList.totalPages;
		this.maxNavigationPage = productList.maxNavigationPage;
		this.navigationPages = productList.navigationPages;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<?> getEntitiesOnChosenPage() {
		return entitiesOnChosenPage;
	}

	public void setEntitiesOnChosenPage(List<?> list) {
		this.entitiesOnChosenPage = list;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public int getMaxNavigationPage() {
		return maxNavigationPage;
	}

	public void setMaxNavigationPage(int maxNavigationPage) {
		this.maxNavigationPage = maxNavigationPage;
	}

	public List<Integer> getNavigationPages() {
		return navigationPages;
	}

	public void setNavigationPages(List<Integer> navigationPages) {
		this.navigationPages = navigationPages;
	}

}