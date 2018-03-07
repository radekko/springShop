package com.shop.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

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

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
    private OrderDao orderDao;
	
	private Map<String,LineItem> cartWithChosenProducts = new TreeMap<String,LineItem>();
	
	private String username;
	
	public CartServiceImpl() {
	}

	@Override
	public Collection<LineItem> getCart() {
		return cartWithChosenProducts.values();
	}

	@Override
	public void addToCart(String amount, String uniqueProductCode, String price, String name) {
		LineItem itemToAdd = createItem(amount, uniqueProductCode, price, name);
		
		if(ifItemInCart(uniqueProductCode)) 
			updateCart(itemToAdd);
		else
			addItemToCart(itemToAdd);
	}

	private void updateCart(LineItem itemToUpdate) {
		addItemToCart(updateAmountInItem(itemToUpdate));
	}
	
	private void addItemToCart(LineItem itemToAdd) {
		cartWithChosenProducts.put(itemToAdd.getUniqueProductCode(), itemToAdd);
	}

	private LineItem updateAmountInItem(LineItem itemToUpdate) {
		LineItem editedItem = cartWithChosenProducts.get(itemToUpdate.getUniqueProductCode());
		editedItem.setAmount(editedItem.getAmount()+itemToUpdate.getAmount());
		return editedItem;
	}

	private boolean ifItemInCart(String uniqueProductCode) {
		return cartWithChosenProducts.containsKey(uniqueProductCode);
	}

	//if cart is list
	//---------------------------------------------------------------------------------------------------------------
//	private List<LineItem> cartWithChosenProducts = new ArrayList<LineItem>();
	
//	@Override
//	public List<LineItem> getCart() {
//		return cartWithChosenProducts;
//	}

//	@Override
//	public void addToCart(String amount, String uniqueCode,
//			String price,String name) {
//		LineItem itemToAdd = createLineItem(amount, uniqueCode, price, name);
//		
//		int index = cartWithChosenProducts.indexOf(itemToAdd);
//
//		if(existInCart(index))
//			addUpdatedItem(itemToAdd.getAmount(), index);
//		else 
//			addItem(itemToAdd);
//	}
//	
//	private boolean existInCart(int index) {
//		return index != -1;
//	}
//
//	private void addItem(LineItem itemToAddOrUpdate) {
//		cartWithChosenProducts.add(itemToAddOrUpdate);
//	}
//
//	private void addUpdatedItem(int amount, int index) {
//		LineItem itemToUpdate = cartWithChosenProducts.get(index);
//		int newAmount = itemToUpdate.getAmount() + amount;
//		itemToUpdate.setAmount(newAmount);
//		cartWithChosenProducts.set(index,itemToUpdate);
//	}
	//---------------------------------------------------------------------------------------------------------------
	
	@Override
	public void storeCartToDatabase() {
		// z product dao pobierz Product na podstawie line item
//		orderDao.saveOrder(cart.getChosenProducts(),userDao.getByUsername(username),1);
	}
	
	private LineItem createItem(String amount, String uniqueProductCode,
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
