package com.shop.service;

import com.shop.model.entity.persistent.User;

public interface UserService {
	boolean findIfExist(User login);
	void saveUser(User employee);
}
