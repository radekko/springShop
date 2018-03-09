package com.shop.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.shop.dao.OrderDao;
import com.shop.model.LineItem;

@Service
@Transactional
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private OrderDao orderDao;

	private Map<String, LineItem> cartWithChosenProducts = new TreeMap<String, LineItem>();

	private String username;

	public CartServiceImpl() {}

	@Override
	public List<LineItem> getCart() {
		return new ArrayList<LineItem>(cartWithChosenProducts.values());
	}

	@Override
	public void addItem(String amount, String uniqueProductCode, String price, String name) {
		LineItem itemToAdd = createItem(amount, uniqueProductCode, price, name);

		if (isInCart(uniqueProductCode))
			updateCart(itemToAdd);
		else
			addItemToCart(itemToAdd);
	}

	@Override
	public void removeItem(String uniqueProductCode) {
		cartWithChosenProducts.remove(uniqueProductCode);
	}

	@Override
	public void makeOrder() {
		storeCartToDatabase();
		clearCart();
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	
	private void clearCart() {
		cartWithChosenProducts.clear();
	}
	
	private void updateCart(LineItem itemToUpdate) {
		addItemToCart(increaseAmount(itemToUpdate));
	}

	private void addItemToCart(LineItem itemToAdd) {
		cartWithChosenProducts.put(itemToAdd.getUniqueProductCode(), itemToAdd);
	}

	private LineItem increaseAmount(LineItem itemToUpdate) {
		LineItem editedItem = cartWithChosenProducts.get(itemToUpdate.getUniqueProductCode());
		editedItem.setAmount(editedItem.getAmount() + itemToUpdate.getAmount());
		return editedItem;
	}

	private boolean isInCart(String uniqueProductCode) {
		return cartWithChosenProducts.containsKey(uniqueProductCode);
	}

	private void storeCartToDatabase() {
		orderDao.saveOrder(getCart(), username, 1);
	}

	private LineItem createItem(String amount, String uniqueProductCode, String currentPrice, String name) {
		LineItem item = new LineItem(name, uniqueProductCode, Double.valueOf(currentPrice), Integer.valueOf(amount));
		return item;
	}

}
