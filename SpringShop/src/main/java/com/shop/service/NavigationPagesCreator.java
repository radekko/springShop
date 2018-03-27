package com.shop.service;

import java.util.ArrayList;
import java.util.List;

public class NavigationPagesCreator {

	public static List<Integer> createNavigationPages(int numberOfChosenPage, int maxNavigationPages, int totalPages) {
		List<Integer> navigationPages = new ArrayList<Integer>();
		int cur = numberOfChosenPage;
		int maxNav = maxNavigationPages;
		int total = totalPages;
		
		
		if(total <= maxNav) {
			for(int i=1; i<=total; i++)
				navigationPages.add(i);
		}
		else {
			navigationPages.add(1);
			
			//1
			if(cur<=2) {
				for(int i=2; i<maxNav; i++){
					navigationPages.add(i);
				}
			}
			//2
			else if(cur>2 && cur < total- ( maxNav - 2)) {
				for(int i=cur; i<maxNav + (cur - 2) ; i++) {
					navigationPages.add(i);
				}
			} //3
			else {
				for(int i= total - (maxNav - 2); i<total; i++) {
					navigationPages.add(i);
				}
			}
			navigationPages.add(totalPages);
		}

		return navigationPages;
	}
	
}
