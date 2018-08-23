package com.shop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shop.model.entity.persistent.Order;

@Repository
public class OrderDaoImpl extends AbstractDaoWithPagination<Integer, Order> implements OrderDao {
	
	@Override
	public List<Order> getOrdersOnPage(int page, int maxProductOnPage) {
		int startIndex = (page - 1 ) * maxProductOnPage;
		return getItemsOnPage(startIndex,maxProductOnPage);
	}
}
