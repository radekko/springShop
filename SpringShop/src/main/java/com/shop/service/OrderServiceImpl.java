package com.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.OrderDao;
import com.shop.dao.ProductDao;
import com.shop.dao.UserDao;
import com.shop.model.entity.domain.LineItemDTO;
import com.shop.model.entity.domain.OrderDTO;
import com.shop.model.entity.persistent.Order;
import com.shop.model.entity.persistent.LineItem;
import com.shop.model.entity.persistent.User;
import com.shop.pagination.DTOPageWithNavigation;
import com.shop.pagination.EntityPage;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	private ProductDao productDao;
	private UserDao userDao;
	private OrderDao orderDao;
	
	@Value("${com.shop.service.OrderService.maxOrdersOnSite}")
	private Integer maxOrdersOnPage;
	
	@Value("${com.shop.service.OrderService.maxNavigationPage}")
	private int maxNavigationPages;
	
	@Autowired
	public OrderServiceImpl(ProductDao productDao, UserDao userDao, OrderDao orderDao) {
		this.productDao = productDao;
		this.userDao = userDao;
		this.orderDao = orderDao;
	}

	@Override
	public DTOPageWithNavigation<OrderDTO> getPaginateOrders(int page) {
		EntityPage<Order> pageWithOrders = orderDao.getOrdersOnPage(page,maxOrdersOnPage);
		
		DTOPageWithNavigation<OrderDTO> ordersPageWithNavigation =
				new DTOPageWithNavigation<OrderDTO>(pageWithOrders,maxNavigationPages, this::convertOrderToOrderDTO);
		return ordersPageWithNavigation;
	}
	
	@Override
	public void saveOrder(List<LineItemDTO> orderList, String generatedNumber) {
		User supportedUser = userDao.getByUsername(getUsername());
		Order order =  new Order(supportedUser, generatedNumber);
		
		for(LineItemDTO item: orderList)
			order.addToSetOfDetails(convertLineItemDTOtoLieItem(item));
		
		orderDao.save(order);
	}
	
	private OrderDTO convertOrderToOrderDTO(Order o) {
		List<LineItemDTO> items = o.getSetOfDetails().stream()
				.map(this::convertOrderDetailsToLineItem).collect(Collectors.toList());
		return new OrderDTO(o.getUserId().getUsername(),o.getOrderIdentifier(),items);
	}
	
	private LineItemDTO convertOrderDetailsToLineItem(LineItem p) {
	    return new LineItemDTO(
	    		p.getProduct().getName(),
	    		p.getProduct().getUniqueProductCode(),
	    		p.getProductPrice(),
	    		p.getProductAmount());
	}

	private LineItem convertLineItemDTOtoLieItem(LineItemDTO i) {
		return new LineItem(i.getAmount(), i.getCurrentPrice(),productDao.getByUniqueCode(i.getUniqueProductCode()));
	}
	
	private String getUsername() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}
	
}
