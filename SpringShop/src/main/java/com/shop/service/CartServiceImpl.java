package com.shop.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.shop.dao.OrderDao;
import com.shop.dao.ProductDao;
import com.shop.dao.UserDao;
import com.shop.model.LineItem;

@Service
@Transactional
@Scope(value = WebApplicationContext.SCOPE_SESSION,
proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService, Serializable {
	
	private static final long serialVersionUID = 1L;

	//zamiast listy set?
	private List<LineItem> cartWithChosenProducts = new ArrayList<LineItem>();

	@Autowired
    private OrderDao orderDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductDao productDao;

	private String username;
	
	public CartServiceImpl() {
	}
	
	@Override
	public List<LineItem> getCart() {
		return cartWithChosenProducts;
	}

	
	//if cart is list
	//---------------------------------------------------------------------------------------------------------------
	@Override
	public void addToCart(String amount, String uniqueCode,
			String price,String name) {
		LineItem itemToAdd = createLineItem(amount, uniqueCode, price, name);
		
		int index = cartWithChosenProducts.indexOf(itemToAdd);

		if(existInCart(index))
			addUpdatedItem(itemToAdd.getAmount(), index);
		else 
			addItem(itemToAdd);
	}
	
	private boolean existInCart(int index) {
		return index != -1;
	}

	private void addItem(LineItem itemToAddOrUpdate) {
		cartWithChosenProducts.add(itemToAddOrUpdate);
	}

	private void addUpdatedItem(int amount, int index) {
		LineItem itemToUpdate = cartWithChosenProducts.get(index);
		int newAmount = itemToUpdate.getAmount() + amount;
		itemToUpdate.setAmount(newAmount);
		cartWithChosenProducts.set(index,itemToUpdate);
	}
	//---------------------------------------------------------------------------------------------------------------
	
	@Override
	public void storeCartToDatabase() {
		// z product dao pobierz Product na podstawie line item
//		orderDao.saveOrder(cart.getChosenProducts(),userDao.getByUsername(username),1);
	}
	
	private LineItem createLineItem(String amount, String uniqueProductCode,
			String currentPrice, String name) {
		LineItem item = new LineItem(name, uniqueProductCode, Double.valueOf(currentPrice), Integer.valueOf(amount));
		return item;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public void setUsername(String username) {
		this.username = username;
	}


	

}
