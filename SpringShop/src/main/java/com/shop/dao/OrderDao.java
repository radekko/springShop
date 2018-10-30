package com.shop.dao;

import com.shop.model.entity.persistent.IEntity;
import com.shop.model.entity.persistent.Order;
import com.shop.pagination.EntityPage;

public interface OrderDao {
	void save(Order order);
	EntityPage<Order> getOrdersOnPage(int page, int maxProductOnPage);
	EntityPage<Order> getOrdersOnPageForUser(int page, int maxProductOnPage, IEntity groupEntity);
	int countTotalRecordsForGroup(IEntity groupEntity);
}
