package com.shop.test.unit;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import com.shop.utils.NavigationPagesCreator;

public class NavigationPagesCreatorTest {

	private List<Integer> list1;
	private List<Integer> list2;
	private List<Integer> list3;
	private int maxNavigationPages;
	private int totalPages;
	
    @Before
    public void setUp() {
    	list1 = new ArrayList<Integer>(Arrays.asList(1,2,3,4,-1,7));
    	list2 = new ArrayList<Integer>(Arrays.asList(1,-1,3,4,5,-1,7));
    	list3 = new ArrayList<Integer>(Arrays.asList(1,-1,4,5,6,7));
    	maxNavigationPages = 5;
    	totalPages = 7;
    }
	
	@Test
	public void testCreateNavigationPages() throws Exception{
		assertThat(getNP(1),getResult(list1));
		assertThat(getNP(2),getResult(list1));
		assertThat(getNP(3),getResult(list1));
		assertThat(getNP(4),getResult(list2));
		assertThat(getNP(5),getResult(list3));
		assertThat(getNP(6),getResult(list3));
		assertThat(getNP(7),getResult(list3));
	}

	private Matcher<List<Integer>> getResult(List<Integer> list) {
		return Matchers.is(list);
	}

	private List<Integer> getNP(int currPage) {
		return NavigationPagesCreator.createNavigationPages(currPage, maxNavigationPages, totalPages);
	}
	
}
