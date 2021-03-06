package com.shop.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY")
public class Category implements IEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
	private String categoryName;

	public Category() {}
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@OneToMany(mappedBy = "category")
	private List<Product> products;

	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return categoryName;
	}
	
}
