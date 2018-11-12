package com.shop.service;

import java.io.Serializable;
import java.util.List;

import com.shop.model.dto.LineItemDTO;

public interface CartService extends Serializable{
	List<LineItemDTO> getSortedCart();
	void addItem(LineItemDTO itemToAdd);
	Boolean makeOrder();
	void removeItem(String uniqueProductCode);
	void clearCart();
	double computeTotalPriceOfCart();
}
