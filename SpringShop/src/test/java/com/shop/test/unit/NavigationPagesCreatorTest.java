package com.shop.test.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.shop.pagination.NavigationPagesCreator;

public class NavigationPagesCreatorTest {

	private List<Integer> npFirstCase;
	private List<Integer> npSecondCase;
	private List<Integer> npThirdCase;
	private int maxNavigationPages;
	private int totalItems;
	private int maxItemsOnPage;

	@Test
	public void testCreateNavigationPages() throws Exception{
		maxNavigationPages =  5;
		totalItems		    = 21;
		maxItemsOnPage   =  3;
		npFirstCase = Arrays.asList(1,2,3,4,-1,7);
    	npSecondCase = Arrays.asList(1,-1,3,4,5,-1,7);
    	npThirdCase = Arrays.asList(1,-1,4,5,6,7);
    	List<Integer> currCase;
    	
    	for(int i = 1; i <= 7; i++) {
    		if(i <= 3)
    			currCase = npFirstCase;
    		else if(i == 4)
    			currCase = npSecondCase;
    		else
    			currCase = npThirdCase;
    		
    		testIfCreatorReturnCorrectCaseForChosenPage(i,maxNavigationPages,totalItems,maxItemsOnPage,currCase);
    	}
	}
	
	@Test
	public void testCreateNavigationPagesWithoutEmptyNP() throws Exception{
		maxNavigationPages =  7;
		totalItems		    = 20;
		maxItemsOnPage   =  3;
		npFirstCase = Arrays.asList(1,2,3,4,5,6,7);
    	
    	for(int i = 1; i <= 7; i++)
    		testIfCreatorReturnCorrectCaseForChosenPage(i,maxNavigationPages,totalItems,maxItemsOnPage,npFirstCase);
	}
	
	private void testIfCreatorReturnCorrectCaseForChosenPage(int page, int maxNavigationPages,int totalItems,int maxItemsOnPage,List<Integer> expectNpCase) {
		assertThat(
				NavigationPagesCreator.create(page, maxNavigationPages, totalItems,maxItemsOnPage),
				equalTo(expectNpCase));
	}
	
}
