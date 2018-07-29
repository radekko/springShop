package com.shop.dao;

import com.shop.model.entity.persistent.Order;

public interface OrderDao {
	void save(Order order);
}
