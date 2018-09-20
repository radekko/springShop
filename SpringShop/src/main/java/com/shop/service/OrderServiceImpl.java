package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.OrderDao;
import com.shop.dao.ProductDao;
import com.shop.dao.UserDao;
import com.shop.model.entity.domain.LineItemDTO;
import com.shop.model.entity.persistent.LineItem;
import com.shop.model.entity.persistent.Order;
import com.shop.model.entity.persistent.User;
import com.shop.pagination.EntityPage;

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
	public EntityPage<Order> getPaginateOrders(int page, int maxOrdersOnPage) {
		return orderDao.getOrdersOnPage(page,maxOrdersOnPage);
	}
	
	@Override
	public void saveOrder(List<LineItemDTO> orderList, String generatedNumber) {
		User supportedUser = userDao.getByUsername(getUsername());
		Order order =  new Order();
		order.setUser(supportedUser);
		order.setOrderIdentifier(generatedNumber);
		
		for(LineItemDTO item: orderList)
			order.addToSetOfLineItems(convertLineItemDTOtoLineItem(item));
		
		orderDao.save(order);
	}
	
	private LineItem convertLineItemDTOtoLineItem(LineItemDTO itemDTO) {
		LineItem item = new LineItem();
		item.setProductAmount(itemDTO.getAmount());
		item.setProductPrice(itemDTO.getCurrentPrice());
		item.setProduct(productDao.getByUniqueCode(itemDTO.getUniqueProductCode()));
		return item;
	}
	
	private String getUsername() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}
	
}
