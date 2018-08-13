package com.shop.test.unit;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.shop.config.RootConfig;
import com.shop.model.entity.domain.LineItem;
import com.shop.service.CartService;
import com.shop.service.CartServiceImpl;
import com.shop.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
@WebAppConfiguration
public class CartServiceTest {
	
	@Mock
	private OrderService orderService;
	
	private CartService cartService;
	
	private LineItem firstItem;
	private static final String FIRST_ITEM_NAME        = "book";
	private static final String FIRST_ITEM_UNIQUE_CODE =   "aa";
	private static final double FIRST_ITEM_PRICE       =    5.0;
	private static final int    FIRST_ITEM_AMOUNT      =      2;
	
	private LineItem secondItem;
	private static final String SECOND_ITEM_NAME        = "apple";
	private static final String SECOND_ITEM_UNIQUE_CODE =    "bb";
	private static final double SECOND_ITEM_PRICE       =    25.0;
	private static final int SECOND_ITEM_AMOUNT         =       3;
	
	@Before
	public void setUp() {
		cartService = new CartServiceImpl(orderService);
		firstItem = new LineItem(FIRST_ITEM_NAME,FIRST_ITEM_UNIQUE_CODE,FIRST_ITEM_PRICE,FIRST_ITEM_AMOUNT);
		secondItem = new LineItem(SECOND_ITEM_NAME,SECOND_ITEM_UNIQUE_CODE,SECOND_ITEM_PRICE,SECOND_ITEM_AMOUNT);
	}

	@Test
	public void testIfItemsAddintgToCartAreUnique() throws Exception{
		addTheSameItemToCart(3,firstItem);
		assertEquals(1, getCartSize());
		
		addTheSameItemToCart(5,secondItem);
		assertEquals(2, getCartSize());
	}
	
	@Test
	public void testIfIsIncrementingAmountWhenAddingProductWhichIsInCart() throws Exception{
		int nTimes=4;
		addTheSameItemToCart(nTimes,firstItem);
		assertEquals(nTimes*FIRST_ITEM_AMOUNT, getItemAmount(0));
		
		nTimes=5;
		addTheSameItemToCart(nTimes,secondItem);
		//getItemAmount(0) - due to sorting
		assertEquals(nTimes*SECOND_ITEM_AMOUNT, getItemAmount(0));
	}
	
	@Test
	public void testRemovingItemsFromCart() throws Exception{
		addItemsToCart(firstItem,secondItem);
		assertEquals(2, cartService.getSortedCart().size());
		
		cartService.removeItem(SECOND_ITEM_UNIQUE_CODE);
		assertEquals(1, getCartSize());
		
		cartService.removeItem(FIRST_ITEM_UNIQUE_CODE);
		assertEquals(0, getCartSize());
	}
	
	@Test
	public void testClearingCart() throws Exception{
		addItemsToCart(firstItem,secondItem);
		cartService.clearCart();
		assertEquals(0, getCartSize());
	}
	
	private void addItemsToCart(LineItem ...items) {
		for(LineItem item : items)
			cartService.addItem(item);
	}
	
	private void addTheSameItemToCart(int nTimes,LineItem item) {
		IntStream.range(0, nTimes).forEach(i -> cartService.addItem(item));
	}
	
	private int getCartSize() {
		return cartService.getSortedCart().size();
	}
	
	private int getItemAmount(int index) {
		return cartService.getSortedCart().get(index).getAmount().intValue();
	}
}
