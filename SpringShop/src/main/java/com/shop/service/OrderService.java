package com.shop.service;

import java.util.List;

import com.shop.model.entity.domain.LineItemDTO;
import com.shop.model.entity.persistent.Order;
import com.shop.pagination.EntityPage;

public interface OrderService {
	void saveOrder(List<LineItemDTO> orderList, String generatedNumber);
	EntityPage<Order> getPaginateOrders(int page, int maxOrdersOnPage);
}
