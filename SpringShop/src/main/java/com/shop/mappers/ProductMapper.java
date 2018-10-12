package com.shop.mappers;

import org.springframework.stereotype.Component;

import com.shop.model.entity.domain.LineItemDTO;
import com.shop.model.entity.persistent.Product;

@Component
public class ProductMapper implements IMapper<Product, LineItemDTO> {

	private final static int INITIAL_STOCK_AMOUNT = 0;
	
	@Override
	public LineItemDTO convertEntityToDTO(Product p) {
		return new LineItemDTO(p.getName(),p.getUniqueProductCode(),p.getPrice(),INITIAL_STOCK_AMOUNT);
	}

	@Override
	public Product convertDTOToEntity(LineItemDTO l) {
		return new Product();
	}

}
