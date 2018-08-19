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
import com.shop.model.entity.domain.LineItem;
import com.shop.model.entity.persistent.Order;
import com.shop.model.entity.persistent.OrderDetails;
import com.shop.model.entity.persistent.User;
import com.shop.pagination.DTOPageWithNavigation;

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
	//TODO: do it	
	@Override
	public DTOPageWithNavigation<LineItem> getPaginateOrders(int page) {
//		EntityPage<Order> pageWithProducts = 
//				productService.getPaginateProducts(page,categoryService.getCategoryByName(categoryName));
//		
//		DTOPageWithNavigation<LineItem> lineItemsPageWithNavigation =
//				new DTOPageWithNavigation<LineItem>(pageWithProducts,maxNavigationPages, this::convertProductToLineItem);
		return null;
	}

	@Override
	public void saveOrder(List<LineItem> orderList, String generatedNumber) {
		User supportedUser = userDao.getByUsername(getUsername());
		Order order =  new Order();
		order.setUserId(supportedUser);
		order.setOrderIdentifier(generatedNumber);
		
		for(LineItem item: orderList)
			order.addToSetOfDetails(convertLineItemToOrderDetails(item));
		
		orderDao.save(order);
	}
	
	private OrderDetails convertLineItemToOrderDetails(LineItem item) {
		OrderDetails order = new OrderDetails();
		order.setProductAmount(item.getAmount());
		order.setProductPrice(item.getCurrentPrice());
		order.addProduct(productDao.getByUniqueCode(item.getUniqueProductCode()));
		return order;
	}
	
	private String getUsername() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}
	
}
