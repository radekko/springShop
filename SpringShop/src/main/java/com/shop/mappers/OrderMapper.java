package com.shop.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shop.model.entity.domain.LineItemDTO;
import com.shop.model.entity.domain.OrderDTO;
import com.shop.model.entity.persistent.LineItem;
import com.shop.model.entity.persistent.Order;

@Component
public class OrderMapper implements IMapper<Order, OrderDTO> {

	@Override
	public OrderDTO convertEntityToDTO(Order o) {
		List<LineItemDTO> items = o.getSetOfLineItems().stream()
				.map(this::convertLineItemToDTO).collect(Collectors.toList());
		return new OrderDTO(o.getUserId().getUsername(),o.getOrderIdentifier(),items);
	}

	@Override
	public Order convertDTOToEntity(OrderDTO d) {
		return new Order();
	}

	private LineItemDTO convertLineItemToDTO(LineItem p) {
	    return new LineItemDTO(
	    		p.getProduct().getName(),
	    		p.getProduct().getUniqueProductCode(),
	    		p.getProductPrice(),
	    		p.getProductAmount());
	}

}