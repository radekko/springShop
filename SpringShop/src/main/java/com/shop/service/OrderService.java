package com.shop.service;

import java.util.List;

import com.shop.model.entity.domain.LineItem;

public interface OrderService {
	void saveOrder(List<LineItem> orderList, String username, String generatedNumber);
}
