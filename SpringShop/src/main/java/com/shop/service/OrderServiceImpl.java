package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.OrderDao;
import com.shop.dao.ProductDao;
import com.shop.dao.UserDao;
import com.shop.model.dto.LineItemDTO;
import com.shop.model.entity.LineItem;
import com.shop.model.entity.Order;
import com.shop.model.entity.User;
import com.shop.pagination.EntityPage;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	private ProductDao productDao;
	private UserDao userDao;
	private OrderDao orderDao;
	private AuthenticationService authenticationService;
	
	@Autowired
	public OrderServiceImpl(ProductDao productDao, UserDao userDao, OrderDao orderDao, AuthenticationService authenticationService) {
		this.productDao = productDao;
		this.userDao = userDao;
		this.orderDao = orderDao;
		this.authenticationService = authenticationService;
	}

	@Override
	public EntityPage<Order> getPaginateOrders(int page, int maxOrdersOnPage, boolean isRealized) {
		return orderDao.getOrdersOnPage(page,maxOrdersOnPage,isRealized);
	}

	@Override
	public EntityPage<Order> getPaginateOrdersForUser(int page, int maxOrdersOnPage, String username, boolean isRealized) {
		return orderDao.getOrdersOnPageForUser(page,maxOrdersOnPage,userDao.getByUsername(username),isRealized);
	}
	
	@Override
	public void saveOrder(List<LineItemDTO> orderList, String generatedNumber) {
		User supportedUser = userDao.getByUsername(authenticationService.getCurrentLoggedUsername());
		Order order = prepareOrder(orderList, generatedNumber, supportedUser);
		orderDao.save(order);
	}

	@Override
	public List<User> getUsersWhichMakeOrder() {
		return userDao.getUsersWhichMakeOrder();
	}
	
	@Override
	public void acomplish(String orderIdentifier) {
		orderDao.changeRealizedFlag(orderIdentifier,true);
	}
	
	private Order prepareOrder(List<LineItemDTO> orderList, String generatedNumber, User supportedUser) {
		Order order =  new Order();
		order.setUser(supportedUser);
		order.setOrderIdentifier(generatedNumber);
		
		for(LineItemDTO item: orderList)
			order.addToSetOfLineItems(convertLineItemDTOtoLineItem(item));
		return order;
	}
	
	private LineItem convertLineItemDTOtoLineItem(LineItemDTO itemDTO) {
		LineItem item = new LineItem();
		item.setProductAmount(itemDTO.getAmount());
		item.setProductPrice(itemDTO.getCurrentPrice());
		item.setProduct(productDao.getByUniqueCode(itemDTO.getUniqueProductCode()));
		return item;
	}

}
