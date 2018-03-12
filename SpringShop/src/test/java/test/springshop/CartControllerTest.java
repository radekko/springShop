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
import com.shop.service.CartService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
@WebAppConfiguration
public class CartControllerTest {

	@Autowired
	CartService cartService;
	
	String [] bookArray;
	String [] appleArray;
	String username;
	
	@Before
	public void initializeLineItems() {
		 bookArray = new String[] {"5", "aa", "2.0", "book"};
		 appleArray = new String[] {"7", "bb", "7.2", "apple"};
		 username = "testUser";
		 cartService.setUsername(username);
	}
	
	@Test
	public void testItemsAddintgToCartAreUnique() throws Exception{
		cartService.addItem(bookArray[0],bookArray[1],bookArray[2],bookArray[3]);
		cartService.addItem(bookArray[0],bookArray[1],bookArray[2],bookArray[3]);
		assertEquals(1, getCartSize());
	}
	
	@Test
	public void testItemsRemovingFromCart() throws Exception{
		cartService.addItem(bookArray[0],bookArray[1],bookArray[2],bookArray[3]);
		cartService.addItem(appleArray[0],appleArray[1],appleArray[2],appleArray[3]);
		assertEquals(2, cartService.getCart().size() );
		cartService.removeItem("bb");
		assertEquals(1, getCartSize());
	}
	
	@Test
	public void testClearCart() throws Exception{
		cartService.addItem(bookArray[0],bookArray[1],bookArray[2],bookArray[3]);
		cartService.addItem(appleArray[0],appleArray[1],appleArray[2],appleArray[3]);
		cartService.clearCart();
		assertEquals(0, getCartSize());
	}
	
	public int getCartSize() {
		return cartService.getCart().size();
	}
}
