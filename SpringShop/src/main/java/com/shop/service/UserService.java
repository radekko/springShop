package com.shop.service;

import com.shop.model.entity.persistent.User;

public interface UserService {
	User getByUsername(String userName);
	boolean storeUserIfNotExist(User user);
}
