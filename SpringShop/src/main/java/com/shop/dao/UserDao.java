package com.shop.dao;

import com.shop.model.entity.persistent.User;

public interface UserDao {
	void addUser(User user);
	boolean findIfUserExist(User user);
	boolean checkIfPasswordIsValidWithUser(User user);
	User getByUsername(String username);
}
