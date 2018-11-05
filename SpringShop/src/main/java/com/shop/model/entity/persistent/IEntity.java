package com.shop.model.entity.persistent;

public interface IEntity {
	
	default String getClassNameStartWithLowerCase() {
		String fieldName = getClass().getSimpleName();
		return Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
	}
}
