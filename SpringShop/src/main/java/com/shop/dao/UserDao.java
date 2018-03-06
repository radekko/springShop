package com.shop.dao;

import com.shop.model.User;

public interface UserDao {
	void addUser(User user);
	boolean findIfUserExist(User login);
	User getByUsername(String username);
}
