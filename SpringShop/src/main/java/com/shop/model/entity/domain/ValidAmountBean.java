package com.shop.model.entity.domain;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class ValidAmountBean {

	@NotNull
	Integer amount;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return amount.toString();
	}
	
	
}
