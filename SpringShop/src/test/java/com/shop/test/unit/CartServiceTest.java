package com.shop.test.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Matchers.any;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.shop.model.entity.domain.LineItemDTO;
import com.shop.service.CartService;
import com.shop.service.CartServiceImpl;
import com.shop.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
	
	@Mock
	private OrderService orderService;
	
	private CartService cartService;
	
	private LineItemDTO firstItem;
	private static final String FIRST_ITEM_NAME        = "book";
	private static final String FIRST_ITEM_UNIQUE_CODE =   "aa";
	private static final double FIRST_ITEM_PRICE       =    5.0;
	private static final int    FIRST_ITEM_AMOUNT      =      2;
	
	private LineItemDTO secondItem;
	private static final String SECOND_ITEM_NAME        = "apple";
	private static final String SECOND_ITEM_UNIQUE_CODE =    "bb";
	private static final double SECOND_ITEM_PRICE       =    25.0;
	private static final int SECOND_ITEM_AMOUNT         =       3;
	
	@Before
	public void setUp() {
		cartService = new CartServiceImpl(orderService);
		firstItem = new LineItemDTO(FIRST_ITEM_NAME,FIRST_ITEM_UNIQUE_CODE,FIRST_ITEM_PRICE,FIRST_ITEM_AMOUNT);
		secondItem = new LineItemDTO(SECOND_ITEM_NAME,SECOND_ITEM_UNIQUE_CODE,SECOND_ITEM_PRICE,SECOND_ITEM_AMOUNT);
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
	
	@Test
	public void testComputeTotalPriceOfCart() throws Exception{
		addItemsToCart(firstItem,secondItem);
		assertEquals(FIRST_ITEM_PRICE * FIRST_ITEM_AMOUNT + SECOND_ITEM_PRICE * SECOND_ITEM_AMOUNT, 
				cartService.computeTotalPriceOfCart(),0);
	}
	
	@Test
	public void testMakeOrderIfCartHasItems() throws Exception{
		willDoNothing().given(orderService).saveOrder(Matchers.anyListOf(LineItemDTO.class), any(String.class));
		addItemsToCart(firstItem,secondItem);
		assertEquals(true, cartService.makeOrder());
	}
	
	@Test
	public void testMakeOrderIfCartIsEmpty() throws Exception{
		assertEquals(false, cartService.makeOrder());
	}
	
	private void addItemsToCart(LineItemDTO ...items) {
		for(LineItemDTO item : items)
			cartService.addItem(item);
	}
	
	private void addTheSameItemToCart(int nTimes,LineItemDTO item) {
		IntStream.range(0, nTimes).forEach(i -> cartService.addItem(item));
	}
	
	private int getCartSize() {
		return cartService.getSortedCart().size();
	}
	
	private int getItemAmount(int index) {
		return cartService.getSortedCart().get(index).getAmount().intValue();
	}
}
