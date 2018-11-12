package com.shop.model.entity;

public interface IEntity {
	
	default String getClassNameStartWithLowerCase() {
		String fieldName = getClass().getSimpleName();
		return Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
	}
}
