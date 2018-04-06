package com.shop.model.entity.persistent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
	
	@ManyToOne
    @JoinColumn(
            name = "username")
	private User userId;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Order_Product")
//	@JoinTable(
//		      name="Order_Product",
//		      joinColumns=@JoinColumn(name="Order_ID", referencedColumnName="orderId"),
//		      inverseJoinColumns=@JoinColumn(name="Product_ID", referencedColumnName="productId"))
	private List<Product> products = new ArrayList<Product>();
	private double productPrice;
	private int productAmount;
	private String orderIdentifier;
	public User getUserId() {
		return userId;
	}
	public void setUser(User userId) {
		this.userId = userId;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}
	public void addProduct(Product product) {
		products.add(product);
	}
	public String getOrderIdentifier() {
		return orderIdentifier;
	}
	public void setOrderIdentifier(String orderIdentifier) {
		this.orderIdentifier = orderIdentifier;
	}
	
}
