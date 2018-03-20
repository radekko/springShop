package com.shop.model.entity.persistent;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT")
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
	private String uniqueProductCode;
	private String name;
	private double price;
	@ManyToMany(mappedBy="products")
	private List<Order> orders;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUniqueProductCode() {
		return uniqueProductCode;
	}
	public void setUniqueProductCode(String uniqueProductCode) {
		this.uniqueProductCode = uniqueProductCode;
	}

}
