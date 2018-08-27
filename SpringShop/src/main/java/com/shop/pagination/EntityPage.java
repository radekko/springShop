package com.shop.pagination;

import java.util.List;

public class EntityPage<T>
{
	private List<T> items;
	private int page;
	private int totalRecords;
	private int maxProductOnPage;
	
	public EntityPage(List<T> items, int page, int totalRecords, int maxProductOnPage) {
		this.items = items;
		this.page = page;
		this.totalRecords = totalRecords;
		this.maxProductOnPage = maxProductOnPage;
	}
	
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getMaxProductOnPage() {
		return maxProductOnPage;
	}
	public void setMaxProductOnPage(int maxProductOnPage) {
		this.maxProductOnPage = maxProductOnPage;
	}
}
