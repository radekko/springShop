package com.shop.model.entity.persistent;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
	
	@ManyToOne
    @JoinColumn(name = "userId")
	private User userId;
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
	@OrderBy("productPrice")
	private Set<LineItem> setOfLineItems = new TreeSet<LineItem>();
	
	private String orderIdentifier;
	
	public User getUserId() {
		return userId;
	}
	public void setUser(User user) {
		this.userId = user;
	}
	public String getOrderIdentifier() {
		return orderIdentifier;
	}
	public void setOrderIdentifier(String orderIdentifier) {
		this.orderIdentifier = orderIdentifier;
	}
	public Set<LineItem> getSetOfLineItems() {
		return setOfLineItems;
	}
	public void setSetOfLineItems(Set<LineItem> setOfLineItems) {
		this.setOfLineItems = setOfLineItems;
	}
	public void addToSetOfLineItems(LineItem orderDetails) {
		this.setOfLineItems.add(orderDetails);
		orderDetails.setOrder(this);
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", setOfDetails=" + setOfLineItems
				+ ", orderIdentifier=" + orderIdentifier + "]";
	}
}
