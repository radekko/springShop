package com.shop.dao;

import java.util.List;

import com.shop.model.entity.persistent.Order;

public interface OrderDao {
	void save(Order order);
	List<Order> getOrdersOnPage(int page, int maxProductOnPage);
	int countTotalRecords();
}
