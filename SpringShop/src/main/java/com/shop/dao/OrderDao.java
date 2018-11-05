package com.shop.dao;

import com.shop.model.entity.persistent.IEntity;
import com.shop.model.entity.persistent.Order;
import com.shop.pagination.EntityPage;

public interface OrderDao {
	void save(Order order);
	EntityPage<Order> getOrdersOnPage(int page, int maxProductOnPage, boolean isRealized);
	EntityPage<Order> getOrdersOnPageForUser(int page, int maxProductOnPage, IEntity groupEntity, boolean isRealized);
	int countTotalRecordsInGroup(IEntity groupEntity);
}
