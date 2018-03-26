package com.shop.model.entity.domain;

import java.util.List;

import org.springframework.stereotype.Component;

//@Component
public class PaginationResult<E> {

	private int totalRecords;
	private int currentPage;
	private List<E> entitiesOnChosenPage;
	private int maxResult;
	private int totalPages;
	private int maxNavigationPage;
	private List<Integer> navigationPages;

	public PaginationResult() {
	}
	
	public PaginationResult(PaginationResult<?> paginationResult, List<E> list) {
		this.entitiesOnChosenPage = list;
		this.totalRecords = paginationResult.totalRecords;
		this.currentPage = paginationResult.currentPage;
		this.maxResult = paginationResult.maxResult;
		this.totalPages = paginationResult.totalPages;
		this.maxNavigationPage = paginationResult.maxNavigationPage;
		this.navigationPages = paginationResult.navigationPages;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<E> getEntitiesOnChosenPage() {
		return entitiesOnChosenPage;
	}

	public void setEntitiesOnChosenPage(List<E> list) {
		this.entitiesOnChosenPage = list;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
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