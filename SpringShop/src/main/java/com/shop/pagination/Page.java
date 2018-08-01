package com.shop.pagination;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Page<T>
{
	private List<T> items;
	private List<Integer> navigationPages;

	public Page(List<T> items,List<Integer> navigationPages) {
		this.items = items;
		this.navigationPages = navigationPages;
	}
	
	public <U> Page<U> convertEntityToDTO(Function<T,U> convertFunction){
		List<U> dtoItems = items.stream().map(convertFunction::apply).collect(Collectors.toList());
		return new Page<U>(dtoItems, navigationPages);
	}
	
	public List<Integer> getNavigationPages() {
		return navigationPages;
	}
	
	public void setNavigationPages(List<Integer> navigationPages) {
		this.navigationPages = navigationPages;
	}
	
	public List<T> getItems() {
		return items;
	}
	
	public void setItems(List<T> items) {
		this.items = items;
	}
}
