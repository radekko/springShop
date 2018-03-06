package com.shop.model;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class ValidAmountBean {

	@NotNull
	int amount;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "amount";
	}
	
	
}
