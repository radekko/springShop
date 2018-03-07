package com.shop.dao;

import java.util.ArrayList;
import java.util.List;

import com.shop.model.LineItem;
import com.shop.model.User;

public interface OrderDao {
	void saveOrder(List<LineItem> list,String username,int generatedNumber);
//	void saveOrder(Cart cart,User user);
}
