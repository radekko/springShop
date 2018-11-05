package com.shop.model.entity.persistent;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Order implements IEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
	
	@ManyToOne
    @JoinColumn(name = "userId")
	private User user;
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
	@OrderBy("productPrice")
	private Set<LineItem> setOfLineItems = new TreeSet<LineItem>();
	
	private String orderIdentifier;
	
	@Column(nullable=false, columnDefinition="boolean default false")
	private boolean isRealized;
	
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
	public boolean isRealized() {
		return isRealized;
	}
	public void setRealized(boolean isRealized) {
		this.isRealized = isRealized;
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
}
