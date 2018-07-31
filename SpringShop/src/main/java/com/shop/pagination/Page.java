package com.shop.pagination;

import java.util.List;

public class Page
{
	private List<?> items;
	private List<Integer> navigationPages;

	public Page(List<?> items,List<Integer> navigationPages) {
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
	public void setItems(List<?> items) {
		this.items = items;
	}
}
