package com.shop.dao;

import java.util.List;

import com.shop.model.LineItem;

public interface OrderDao {
	void saveOrder(List<LineItem> list,String username,int generatedNumber);
//	void saveOrder(Cart cart,User user);
}
