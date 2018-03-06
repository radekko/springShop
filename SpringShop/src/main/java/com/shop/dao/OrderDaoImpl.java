package com.shop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shop.model.LineItem;
import com.shop.model.Order;
import com.shop.model.User;

@Repository
public class OrderDaoImpl extends AbstractDao<Integer, Order> implements OrderDao {

	@Override
	public void saveOrder(List<LineItem> orderList,User user,int generatedNumber) {
		
		for(LineItem item: orderList) {
			Order order = new Order();
			order.setProductAmount(item.getAmount());
			order.setProductPrice(item.getCurrentPrice());
//			order.addProduct(item.getProduct());
			order.setUserId(user);
			order.setOrderIdentifier(generatedNumber);
//			save(order);
			merge(order);
		}
	}

}
