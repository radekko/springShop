package com.shop.service;

import java.io.Serializable;
import java.util.List;

import com.shop.model.entity.domain.LineItem;

public interface CartService extends Serializable{
	List<LineItem> getSortedCart();
	void addItem(LineItem itemToAdd);
	Boolean makeOrder();
	void removeItem(String uniqueProductCode);
	void clearCart();
	double computeTotalPriceOfCart();
}
