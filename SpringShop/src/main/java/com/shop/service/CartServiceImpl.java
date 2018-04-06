package com.shop.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.shop.dao.OrderDao;
import com.shop.model.entity.domain.LineItem;

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
		List<LineItem> lineItemsList = new ArrayList<LineItem>(cartWithChosenProducts.values());
		sortItemsInCart(lineItemsList);
		return lineItemsList;
	}

	@Override
	public void addItem(LineItem itemToAdd) {
		if (isInCart(itemToAdd.getUniqueProductCode()))
			updateCart(itemToAdd);
		else
			addItemToCart(itemToAdd);
	}

	@Override
	public void removeItem(String uniqueProductCode) {
		cartWithChosenProducts.remove(uniqueProductCode);
	}

	@Override
	public Boolean makeOrder() {
		if(getCart().isEmpty())
			return false;
		
		orderDao.saveOrder(getCart(), username, UUID.randomUUID().toString());
		clearCart();
		return true;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public void clearCart() {
		cartWithChosenProducts.clear();
	}
	
	@Override
	public double getTotalPriceOfCart() {
		return cartWithChosenProducts.values().stream()
				.map(LineItem::getTotalCost).mapToDouble(Double::doubleValue).sum();
	}

	private void sortItemsInCart(List<LineItem> lineItemsList) {
		lineItemsList.sort((o1, o2)->o1.getName().compareTo(o2.getName()));
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
}