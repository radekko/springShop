package com.shop.model.entity.persistent;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
	
	@ManyToOne
    @JoinColumn(name = "username")
	private User user;
	
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
	private Set<OrderDetails> setOfDetails = new HashSet<OrderDetails>();
	
	private String orderIdentifier;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOrderIdentifier() {
		return orderIdentifier;
	}
	public void setOrderIdentifier(String orderIdentifier) {
		this.orderIdentifier = orderIdentifier;
	}
	public Set<OrderDetails> getSetOfDetails() {
		return setOfDetails;
	}
	public void setSetOfDetails(Set<OrderDetails> setOfDetails) {
		this.setOfDetails = setOfDetails;
	}
	public void addToSetOfDetails(OrderDetails orderDetails) {
		this.setOfDetails.add(orderDetails);
		orderDetails.setOrder(this);
	}
}
