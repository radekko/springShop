package com.shop.model.entity.persistent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderDetails implements Comparable<OrderDetails> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderDetailsId;

	@ManyToOne
	@JoinColumn(name = "orderId", nullable = false)
	private Order order;

	@ManyToOne
	private Product product;
	private double productPrice;
	private int productAmount;

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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int compareTo(OrderDetails o) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;

		if (this.productPrice < o.productPrice) 
			return BEFORE;
		if (this.productPrice > o.productPrice)
			return AFTER;

		if (this.productAmount < o.productAmount) 
			return BEFORE;
		if (this.productAmount > o.productAmount)
			return AFTER;
		
		int comparison = this.product.compareTo(o.product);
		    if (comparison != EQUAL) return comparison;
		
		return EQUAL;
	}
}
