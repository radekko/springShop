package com.shop.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shop.model.dto.LineItemDTO;
import com.shop.model.dto.OrderDTO;
import com.shop.model.entity.LineItem;
import com.shop.model.entity.Order;

@Component
public class OrderMapper implements Mapper<Order, OrderDTO> {

	@Override
	public OrderDTO convertEntityToDTO(Order o) {
		List<LineItemDTO> items = o.getSetOfLineItems().stream()
				.map(this::convertLineItemToDTO).collect(Collectors.toList());
		return new OrderDTO(o.getUser().getUsername(),o.getOrderIdentifier(),items);
	}

	private LineItemDTO convertLineItemToDTO(LineItem p) {
	    return new LineItemDTO(
	    		p.getProduct().getName(),
	    		p.getProduct().getUniqueProductCode(),
	    		p.getProductPrice(),
	    		p.getProductAmount());
	}

}
