package com.shop.service;

import java.util.List;

import com.shop.model.entity.domain.LineItem;
import com.shop.model.entity.domain.OrderDTO;
import com.shop.pagination.DTOPageWithNavigation;

public interface OrderService {
	void saveOrder(List<LineItem> orderList, String generatedNumber);
	DTOPageWithNavigation<OrderDTO> getPaginateOrders(int page);
}
