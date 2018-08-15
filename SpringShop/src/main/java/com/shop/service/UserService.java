package com.shop.service;

import com.shop.model.entity.persistent.User;

public interface UserService {
	boolean findIfUserExist(User user);
	boolean checkIfPasswordIsValidWithUser(User user);
	void saveUser(User user);
	User getByUsername(String userName);
}
