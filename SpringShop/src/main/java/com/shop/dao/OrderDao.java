package com.shop.dao;

import java.util.List;

import com.shop.model.entity.domain.LineItem;

public interface OrderDao {
	void saveOrder(List<LineItem> list,String username,String generatedNumber);
}
