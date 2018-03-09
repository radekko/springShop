package com.shop.model;

import java.io.Serializable;

public class LineItem implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String uniqueProductCode;
	private double currentPrice;
	private int amount;
	public String getUniqueProductCode() {
		return uniqueProductCode;
	}
	public void setUniqueProductCode(String uniqueProductCode) {
		this.uniqueProductCode = uniqueProductCode;
	}
	public LineItem() {}
	public LineItem(String name, String uniqueProductCode, double currentPrice, int amount) {
		this.name = name;
		this.uniqueProductCode = uniqueProductCode;
		this.currentPrice = currentPrice;
		this.amount = amount;
	}
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		LineItem other = (LineItem) obj;
		if (uniqueProductCode == null) {
			if (other.uniqueProductCode != null)
				return false;
		} else if (!uniqueProductCode.equals(other.uniqueProductCode))
			return false;
		return true;
	}
}
