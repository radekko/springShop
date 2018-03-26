package com.shop.test.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.shop.service.NavigationPagesCreator;

public class NavigationPagesCreatorTest {

	private List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
	private List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(1,3,4,5,6));
	private List<Integer> list3 = new ArrayList<Integer>(Arrays.asList(1,4,5,6,7));
	private int maxNavigationPages = 5;
	private int totalPages = 7;
	
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
//		int maxNavigationPages = 5;
//		int totalPages = 7;
		return NavigationPagesCreator.createNavigationPages(currPage, maxNavigationPages, totalPages);
	}
	
}
