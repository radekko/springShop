package com.shop.dao;

import java.util.List;

import com.shop.model.entity.persistent.User;

public interface UserDao {
	void saveUser(User user);
	boolean findIfUserExist(User user);
	boolean checkIfPasswordIsValidWithUser(User user);
	User getByUsername(String username);
	List<User> getUsersWhichMakeOrder();
}
