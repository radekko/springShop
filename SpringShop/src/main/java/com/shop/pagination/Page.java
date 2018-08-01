package com.shop.pagination;

import java.util.List;

public class Page<T>
{
	private List<T> items;
	private List<Integer> navigationPages;

	public Page(List<T> items,List<Integer> navigationPages) {
		this.items = items;
		this.navigationPages = navigationPages;
	}
	public List<Integer> getNavigationPages() {
		return navigationPages;
	}
	public void setNavigationPages(List<Integer> navigationPages) {
		this.navigationPages = navigationPages;
	}
	public List<?> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
}
