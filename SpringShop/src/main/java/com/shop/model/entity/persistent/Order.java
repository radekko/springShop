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
	private Set<LineItem> setOfDetails = new TreeSet<LineItem>();
	
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
	public Set<LineItem> getSetOfDetails() {
		return setOfDetails;
	}
	public void setSetOfDetails(Set<LineItem> setOfDetails) {
		this.setOfDetails = setOfDetails;
	}
	public void addToSetOfDetails(LineItem orderDetails) {
		this.setOfDetails.add(orderDetails);
		orderDetails.setOrder(this);
	}
}
