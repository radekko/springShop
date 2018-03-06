package com.shop.service;

import java.util.List;

import com.shop.model.User;

public interface UserService {
	
	boolean findIfExist(User login);

	void saveUser(User employee);

	void updateUser(User employee);

	void deleteUserBySsn(String ssn);

	List<User> findAllUsers();

	User findUserBySsn(String ssn);

	boolean isUserSsnUnique(Integer id, String ssn);

	
}
