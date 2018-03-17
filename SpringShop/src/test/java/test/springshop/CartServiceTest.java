package test.springshop;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.shop.config.RootConfig;
import com.shop.model.LineItem;
import com.shop.service.CartService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
@WebAppConfiguration
public class CartServiceTest {

	@Autowired
	private CartService cartService;
	
	private LineItem bookArray;
	private LineItem appleArray;
	private String username;
	private String BOOK_UNIQUE_CODE = "aa";
	private String APPLE_UNIQUE_CODE = "bb";
	
	@Before
	public void initializeLineItems() {
		 bookArray = new LineItem("book",BOOK_UNIQUE_CODE,5.0,2);
		 appleArray = new LineItem("apple",APPLE_UNIQUE_CODE,25.0,3);
		 username = "testUser";
		 cartService.setUsername(username);
	}
	
	@Test
	public void testItemsAddintgToCartAreUnique() throws Exception{
		cartService.addItem(bookArray);
		cartService.addItem(bookArray);
		assertEquals(1, getCartSize());
	}
	
	@Test
	public void testItemsRemovingFromCart() throws Exception{
		cartService.addItem(bookArray);
		cartService.addItem(appleArray);
		assertEquals(2, cartService.getCart().size() );
		cartService.removeItem(APPLE_UNIQUE_CODE);
		assertEquals(1, getCartSize());
	}
	
	@Test
	public void testClearCart() throws Exception{
		cartService.addItem(bookArray);
		cartService.addItem(bookArray);
		cartService.clearCart();
		assertEquals(0, getCartSize());
	}
	
	private int getCartSize() {
		return cartService.getCart().size();
	}
}
