package com.shop.test.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.shop.pagination.NavigationPagesCreator;

public class NavigationPagesCreatorTest {

	private List<Integer> npFirstCase;
	private List<Integer> npSecondCase;
	private List<Integer> npThirdCase;
	private static final int maxNavigationPages =  5;
	private static final int totalItems		    = 21;
	private static final int maxProductOnPage   =  3;
	
    @Before
    public void setUp() {
    	npFirstCase = Arrays.asList(1,2,3,4,-1,7);
    	npSecondCase = Arrays.asList(1,-1,3,4,5,-1,7);
    	npThirdCase = Arrays.asList(1,-1,4,5,6,7);
    }
	
	@Test
	public void testCreateNavigationPages() throws Exception{
		testIfCreatorReturnCorrectCaseForChosenPage(1,npFirstCase);
		testIfCreatorReturnCorrectCaseForChosenPage(2,npFirstCase);
		testIfCreatorReturnCorrectCaseForChosenPage(3,npFirstCase);
		testIfCreatorReturnCorrectCaseForChosenPage(4,npSecondCase);
		testIfCreatorReturnCorrectCaseForChosenPage(5,npThirdCase);
		testIfCreatorReturnCorrectCaseForChosenPage(6,npThirdCase);
		testIfCreatorReturnCorrectCaseForChosenPage(7,npThirdCase);
	}
	
	private void testIfCreatorReturnCorrectCaseForChosenPage(int page, List<Integer> expectNpCase) {
		assertThat(getNPForChosenPage(page),equalTo(expectNpCase));
	}

	private List<Integer> getNPForChosenPage(int currPage) {
		return NavigationPagesCreator.createNavigationPages(currPage, maxNavigationPages, totalItems,maxProductOnPage);
	}
	
}
