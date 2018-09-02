package com.shop.model.entity.domain;

import java.util.List;

public class OrderDTO {

	private String username;
	private String orderIdentifier;
	private List<LineItemDTO> items;
	
	
	public OrderDTO(String username, String orderIdentifier, List<LineItemDTO> items) {
		this.username = username;
		this.orderIdentifier = orderIdentifier;
		this.items = items;
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
	public List<LineItemDTO> getItems() {
		return items;
	}
	public void setItems(List<LineItemDTO> items) {
		this.items = items;
	}
	
}
