package com.shop.service;

import java.util.List;

import com.shop.model.dto.LineItemDTO;
import com.shop.model.entity.Order;
import com.shop.model.entity.User;
import com.shop.pagination.EntityPage;

public interface OrderService {
	void saveOrder(List<LineItemDTO> orderList, String generatedNumber);
	List<User> getUsersWhichMakeOrder();
	EntityPage<Order> getPaginateOrders(int page, int maxOrdersOnPage, boolean isRealized);
	EntityPage<Order> getPaginateOrdersForUser(int page, int maxOrdersOnPage, String username, boolean isRealized);
	void acomplish(String orderIdentifier);
}
