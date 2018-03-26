package com.shop.service;

import java.util.ArrayList;
import java.util.List;

public class NavigationPagesCreator {

	public static List<Integer> createNavigationPages(int numberOfChosenPage, int maxNavigationPages, int totalPages) {
		List<Integer> navigationPages = new ArrayList<Integer>();
		int start = numberOfChosenPage;
		int count = maxNavigationPages;
		int total = totalPages;
		navigationPages.add(1);
System.out.println("Total: " + total);
		if (numberOfChosenPage <= count - total + count ) { //1
			start = 2;
			for (int i = 1; i <= count - 1; i++) {
				navigationPages.add(start);
				if(start ==  totalPages)
					break;
				start++;
			}
		} else if (numberOfChosenPage >= total - numberOfChosenPage + count ) {  //3
			start = total - (count - 2);
			for (int i = 1; i <= count - 1; i++) {
				navigationPages.add(start);
				if(start ==  totalPages)
					break;
				start++;
			}
		}
		else {  //2
			start = numberOfChosenPage - 1;
			for (int i = 1; i <= count - 1; i++) {
				navigationPages.add(start);
				if(start ==  totalPages)
					break;
				start++;
			}
		}
		return navigationPages;
	}
	
}
