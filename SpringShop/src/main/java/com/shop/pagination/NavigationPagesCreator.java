package com.shop.pagination;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NavigationPagesCreator {

	private static final int VALUE_OF_EMPTY_FIELD = -1;
	
	public <E> List<Integer> create(EntityPage<E> entityPage, int maxNavigationPages) {
		return create(entityPage.getPage(),maxNavigationPages, entityPage.getTotalRecords(),entityPage.getMaxItemsOnPage());
	}
	
	public List<Integer> create(int numberOfChosenPage, int maxNavigationPages, int totalRecrods,int maxProductOnPage) {
		
		List<Integer> navigationPages = new ArrayList<Integer>();
		int totalNavPagesToDisplay = calcTotalNavPagesToDisplay(maxProductOnPage,totalRecrods);
		
		if(totalNavPagesToDisplay <= maxNavigationPages) 
			p1234N(navigationPages, totalNavPagesToDisplay);
		 else {
			addFirstNavPage(navigationPages);
			addCentralNavPages(navigationPages, numberOfChosenPage, maxNavigationPages, totalNavPagesToDisplay);
			addLastNavPage(navigationPages, totalNavPagesToDisplay);
			addEmptySigns(navigationPages);
		 }

		return navigationPages;
	}
	
	private int calcTotalNavPagesToDisplay(int maxProductOnPage,int totalRecords) {
		return totalRecords % maxProductOnPage == 0 ? totalRecords/maxProductOnPage : (totalRecords/maxProductOnPage) + 1;
	}

	private void addEmptySigns(List<Integer> navigationPages) {
		checkIfAddEmptySignOnTheEnd(navigationPages);
		checkIfAddEmptySignOnTheBeginning(navigationPages);
	}

	private void checkIfAddEmptySignOnTheEnd(List<Integer> navigationPages) {
		int lastIndex = navigationPages.size() - 1;

		int nextToLastEl = navigationPages.get(lastIndex - 1);
		int lastEl = navigationPages.get(lastIndex);
		
		if(lastEl != nextToLastEl + 1)
			navigationPages.add(lastIndex,VALUE_OF_EMPTY_FIELD);
	}
	
	private void checkIfAddEmptySignOnTheBeginning(List<Integer> navigationPages) {
		if(navigationPages.get(1) != 2)
			navigationPages.add(1, VALUE_OF_EMPTY_FIELD);
	}

	private void addLastNavPage(List<Integer> navigationPages, int total) {
		navigationPages.add(total);
	}

	private void addFirstNavPage(List<Integer> navigationPages) {
		navigationPages.add(1);
	}

	private void addCentralNavPages(List<Integer> navigationPages, int cur,
			int maxNav, int total) {

		if (cur <= 3) 
			p1234xN(navigationPages, maxNav);
		else if (cur > 3 && cur < total - (maxNav - 3)) 
			p1x345xN(navigationPages, cur, maxNav);
		else 
			p1x789N(navigationPages, maxNav, total);
	}

	private void p1x789N(List<Integer> navigationPages, int maxNav, int total) {
		for (int i = total - (maxNav - 2); i < total; i++)
			navigationPages.add(i);
	}

	private void p1x345xN(List<Integer> navigationPages, int cur, int maxNav) {
		for (int i = cur - 1; i < maxNav + (cur - 3); i++)
			navigationPages.add(i);
	}

	private void p1234xN(List<Integer> navigationPages, int maxNav) {
		for (int i = 2; i < maxNav; i++)
			navigationPages.add(i);
	}

	private void p1234N(List<Integer> navigationPages, int total) {
		for (int i = 1; i <= total; i++)
			navigationPages.add(i);
	}
}
