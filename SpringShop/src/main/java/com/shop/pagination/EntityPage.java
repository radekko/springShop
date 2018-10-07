package com.shop.pagination;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EntityPage<E>
{
	private List<E> items;
	private int page;
	private int totalRecords;
	private int maxProductOnPage;
	
	public EntityPage(List<E> items, int page, int totalRecords, int maxProductOnPage) {
		this.items = items;
		this.page = page;
		this.totalRecords = totalRecords;
		this.maxProductOnPage = maxProductOnPage;
	}
	
	public <E2> EntityPage(EntityPage<E2> entityPage,Function<E2,E> convertToDTOFunction) {
		this.items = convertEntityToDTO(entityPage,convertToDTOFunction);
		this.page = entityPage.page;
		this.totalRecords = entityPage.totalRecords;
		this.maxProductOnPage = entityPage.maxProductOnPage;
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
	public int getMaxProductOnPage() {
		return maxProductOnPage;
	}
	public void setMaxProductOnPage(int maxProductOnPage) {
		this.maxProductOnPage = maxProductOnPage;
	}
	public <E2> List<E2> mapToDTO(Function<E,E2> convertFunction) {
		return items.stream().map(convertFunction::apply).collect(Collectors.toList());
	}
	private <E2> List<E> convertEntityToDTO(EntityPage<E2> entityPage,Function<E2,E> convertFunction) {
		return entityPage.getItems().stream().map(convertFunction::apply).collect(Collectors.toList());
	}

}
