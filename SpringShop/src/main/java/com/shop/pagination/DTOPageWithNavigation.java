package com.shop.pagination;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DTOPageWithNavigation<T> {

	private List<T> items;
	private List<Integer> navigationPages;
	
	public <T2> DTOPageWithNavigation(EntityPage<T2> entityPage,int maxNavigationPages,
			Function<T2,T> convertFunction) {
		this.items = convertEntityToDTO(entityPage,convertFunction);
		this.navigationPages = create(entityPage.getPage(),
				maxNavigationPages,entityPage.getTotalRecords(),
				entityPage.getMaxProductOnPage());
	}
	
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public List<Integer> getNavigationPages() {
		return navigationPages;
	}
	public void setNavigationPages(List<Integer> navigationPages) {
		this.navigationPages = navigationPages;
	}
	
	private List<Integer> create(int page, int maxNavigationPages, int totalRecords, int maxProductOnPage) {
		return NavigationPagesCreator
				.createNavigationPages(page, maxNavigationPages, totalRecords, maxProductOnPage);
	}

	private <T2> List<T> convertEntityToDTO(EntityPage<T2> entityPage,Function<T2,T> convertFunction) {
		return entityPage.getItems().stream().map(convertFunction::apply).collect(Collectors.toList());
	}
	
	
}
