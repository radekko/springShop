package com.shop.pagination;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
	
	public <T2> EntityPage(EntityPage<T2> entityPage,Function<T2,T> convertToDTOFunction) {
		this.items = convertEntityToDTO(entityPage,convertToDTOFunction);
		this.page = entityPage.page;
		this.totalRecords = entityPage.totalRecords;
		this.maxProductOnPage = entityPage.maxProductOnPage;
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
	public <T2> List<T2> mapToDTO(Function<T,T2> convertFunction) {
		return items.stream().map(convertFunction::apply).collect(Collectors.toList());
	}
	private <T2> List<T> convertEntityToDTO(EntityPage<T2> entityPage,Function<T2,T> convertFunction) {
		return entityPage.getItems().stream().map(convertFunction::apply).collect(Collectors.toList());
	}

}
