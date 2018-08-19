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

@Entity
public class OrderDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailsId;
	
	@ManyToOne
    @JoinColumn(name="orderId", nullable=false)
	private Order order;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		      name="OrderDetais_Product",
		      joinColumns=@JoinColumn(name="OrderDetails_ID", referencedColumnName="orderDetailsId"),
		      inverseJoinColumns=@JoinColumn(name="Product_ID", referencedColumnName="productId"))
	private List<Product> products = new ArrayList<Product>();
	private double productPrice;
	private int productAmount;
	
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
