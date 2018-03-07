package com.shop.service;

import java.util.List;

import com.shop.model.LineItem;

public interface CartService {
	List<LineItem> getCart();
	void addToCart(String amount, String uniqueCode, String price,String name);
	void storeCartToDatabase();
	String getUsername();
	void setUsername(String username);
}
