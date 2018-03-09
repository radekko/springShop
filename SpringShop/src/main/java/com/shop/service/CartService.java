package com.shop.service;

import java.util.List;

import com.shop.model.LineItem;

public interface CartService {
	List<LineItem> getCart();
	void addItem(String amount, String uniqueCode, String price,String name);
	void makeOrder();
	void removeItem(String uniqueProductCode);
	String getUsername();
	void setUsername(String username);
}
