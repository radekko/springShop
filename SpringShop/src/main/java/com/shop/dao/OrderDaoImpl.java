package com.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.model.entity.domain.LineItem;
import com.shop.model.entity.persistent.Order;
import com.shop.model.entity.persistent.User;

@Repository
public class OrderDaoImpl extends AbstractDao<Integer, Order> implements OrderDao {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public void saveOrder(List<LineItem> orderList, String username, String generatedNumber) {
		User supportedUser = userDao.getByUsername(username);
		for(LineItem item: orderList) {
			Order order = convertLineItemToOrder(generatedNumber, supportedUser, item);
			save(order);
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
