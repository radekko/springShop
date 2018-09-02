package com.shop.model.entity.domain;

import java.io.Serializable;

import javax.validation.constraints.Min;

public class LineItemDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String uniqueProductCode;
	private double currentPrice;
	@Min(value = 1L)
	private Integer amount;
	
	public LineItemDTO() {}
	public LineItemDTO(String name, String uniqueProductCode, double currentPrice, int amount) {
		this.name = name;
		this.uniqueProductCode = uniqueProductCode;
		this.currentPrice = currentPrice;
		this.amount = amount;
	}
	
	public LineItemDTO(LineItemDTO li) {
		this.name = li.name;
		this.uniqueProductCode = li.uniqueProductCode;
		this.currentPrice = li.currentPrice;
		this.amount = li.amount;
	}
	
	public String getUniqueProductCode() {
		return uniqueProductCode;
	}
	
	public void setUniqueProductCode(String uniqueProductCode) {
		this.uniqueProductCode = uniqueProductCode;
	}
	
	public double getCurrentPrice() {
		return currentPrice;
	}
	
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getTotalCost() {
		return this.currentPrice * this.amount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uniqueProductCode == null) ? 0 : uniqueProductCode.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineItemDTO other = (LineItemDTO) obj;
		if (uniqueProductCode == null) {
			if (other.uniqueProductCode != null)
				return false;
		} else if (!uniqueProductCode.equals(other.uniqueProductCode))
			return false;
		return true;
	}
}
