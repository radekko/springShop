package com.shop.test.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.shop.service.NavigationPagesCreator;

public class NavigationPagesCreatorTest {

	@Test
	public void testCreateNavigationPages() throws Exception{
		
		assertThat(getNP(1),is(getResult(1,2,3,4,5)));
		
		assertThat(getNP(2),is(getResult(1,2,3,4,5)));

		assertThat(getNP(3),is(getResult(1,2,3,4,5)));
		
		assertThat(getNP(4),is(getResult(1,3,4,5,6)));
		
		assertThat(getNP(5),is(getResult(1,4,5,6,7)));

		assertThat(getNP(6),is(getResult(1,4,5,6,7)));
		
		assertThat(getNP(7),is(getResult(1,4,5,6,7)));
		
	}

	private List<Integer> getResult(int...a) {
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < a.length; i++)
			list.add(a[i]);
		
		return list;
	}

	private List<Integer> getNP(int currPage) {
		int maxNavigationPages = 5;
		int totalPages = 7;
		return NavigationPagesCreator.createNavigationPages(currPage, maxNavigationPages, totalPages);
	}
	
}
