package com.shop.service;

import java.util.List;

import com.shop.model.entity.domain.LineItemDTO;
import com.shop.model.entity.persistent.Order;
import com.shop.model.entity.persistent.User;
import com.shop.pagination.EntityPage;

public interface OrderService {
	void saveOrder(List<LineItemDTO> orderList, String generatedNumber);
	EntityPage<Order> getPaginateOrders(int page, int maxOrdersOnPage);
	EntityPage<Order> getPaginateOrdersForUser(int page, int maxOrdersOnPage, String username);
	List<User> getUsersWhichMakeOrder();
}
