package com.shop.model.entity.domain;

import java.util.List;

public class OrderDTO implements IDTO{

	private String username;
	private String orderIdentifier;
	private List<LineItemDTO> lineItemDTO;
	
	public OrderDTO(String username, String orderIdentifier, List<LineItemDTO> lineItemDTO) {
		this.username = username;
		this.orderIdentifier = orderIdentifier;
		this.lineItemDTO = lineItemDTO;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOrderIdentifier() {
		return orderIdentifier;
	}
	public void setOrderIdentifier(String orderIdentifier) {
		this.orderIdentifier = orderIdentifier;
	}
	public List<LineItemDTO> getLineItemDTO() {
		return lineItemDTO;
	}
	public void setLineItemDTO(List<LineItemDTO> lineItemDTO) {
		this.lineItemDTO = lineItemDTO;
	}
}
