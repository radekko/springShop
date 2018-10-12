package com.shop.pagination;

import java.util.List;

public class EntityPage<E>
{
	private List<E> items;
	private int page;
	private int totalRecords;
	private int maxItemsOnPage;
	
	public EntityPage() {}

	public EntityPage(List<E> items, int page, int totalRecords, int maxItemsOnPage) {
		this.items = items;
		this.page = page;
		this.totalRecords = totalRecords;
		this.maxItemsOnPage = maxItemsOnPage;
	}

	public List<E> getItems() {
		return items;
	}
	public void setItems(List<E> items) {
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
	public int getMaxItemsOnPage() {
		return maxItemsOnPage;
	}
	public void setMaxItemsOnPage(int maxItemsOnPage) {
		this.maxItemsOnPage = maxItemsOnPage;
	}
}
