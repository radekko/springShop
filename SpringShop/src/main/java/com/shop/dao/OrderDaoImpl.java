package com.shop.dao;

import org.springframework.stereotype.Repository;

import com.shop.model.entity.persistent.Order;
import com.shop.pagination.EntityPage;

@Repository
public class OrderDaoImpl extends AbstractDaoWithPagination<Integer, Order> implements OrderDao {
	
	@Override
	public EntityPage<Order> getOrdersOnPage(int page, int maxProductOnPage) {
		return getItemsOnPage(page,maxProductOnPage,null);
	}
}
