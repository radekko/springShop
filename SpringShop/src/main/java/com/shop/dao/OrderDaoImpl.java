package com.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.model.LineItem;
import com.shop.model.Order;
import com.shop.model.User;

@Repository
public class OrderDaoImpl extends AbstractDao<Integer, Order> implements OrderDao {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public void saveOrder(List<LineItem> orderList, String username, int generatedNumber) {
		User supportedUser = userDao.getByUsername(username);
		for(LineItem item: orderList) {
			Order order = new Order();
			order.setProductAmount(item.getAmount());
			order.setProductPrice(item.getCurrentPrice());
			order.addProduct(productDao.getByUniqueCode(item.getUniqueProductCode()));
			order.setUser(supportedUser);
			order.setOrderIdentifier(generatedNumber);
			save(order);
		}
	}

}
