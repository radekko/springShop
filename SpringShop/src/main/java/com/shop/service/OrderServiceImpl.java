package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.OrderDao;
import com.shop.dao.ProductDao;
import com.shop.dao.UserDao;
import com.shop.model.entity.domain.LineItem;
import com.shop.model.entity.persistent.Order;
import com.shop.model.entity.persistent.User;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	private ProductDao productDao;
	private UserDao userDao;
	private OrderDao orderDao;
	
	@Autowired
	public OrderServiceImpl(ProductDao productDao, UserDao userDao, OrderDao orderDao) {
		this.productDao = productDao;
		this.userDao = userDao;
		this.orderDao = orderDao;
	}

	@Override
	public void saveOrder(List<LineItem> orderList, String username, String generatedNumber) {
		User supportedUser = userDao.getByUsername(username);
		for(LineItem item: orderList) {
			Order order = convertLineItemToOrder(generatedNumber, supportedUser, item);
			orderDao.save(order);
		}
	}
	
	private Order convertLineItemToOrder(String generatedNumber, User supportedUser, LineItem item) {
		Order order = new Order();
		order.setProductAmount(item.getAmount());
		order.setProductPrice(item.getCurrentPrice());
		order.addProduct(productDao.getByUniqueCode(item.getUniqueProductCode()));
		order.setUser(supportedUser);
		order.setOrderIdentifier(generatedNumber);
		return order;
	}
	
}
