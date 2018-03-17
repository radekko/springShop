package com.shop.service;

import java.io.Serializable;
import java.util.List;

import com.shop.model.LineItem;

public interface CartService extends Serializable{
	List<LineItem> getCart();
	void addItem(LineItem itemToAdd);
	void makeOrder();
	void removeItem(String uniqueProductCode);
	String getUsername();
	void setUsername(String username);
	void clearCart();
}
