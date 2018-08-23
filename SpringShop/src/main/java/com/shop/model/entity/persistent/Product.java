package com.shop.model.entity.persistent;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
	private List<OrderDetails> orders;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private Category category;
	
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
