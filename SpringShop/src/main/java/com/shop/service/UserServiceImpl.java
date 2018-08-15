package com.shop.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.UserDao;
import com.shop.model.entity.persistent.User;

@Service
@Transactional
public class UserServiceImpl implements UserService, Serializable {

	private static final long serialVersionUID = 1L;
    private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao dao) {
		this.userDao = dao;
	}

	@Override
	public void saveUser(User user) {
		userDao.addUser(user);
	}
	
	@Override
	public boolean findIfUserExist(User login) {
		return userDao.findIfUserExist(login);
	}

	@Override
	public boolean checkIfPasswordIsValidWithUser(User user) {
		return userDao.checkIfPasswordIsValidWithUser(user);
	}
	
	@Override
	public User getByUsername(String userName) {
		return userDao.getByUsername(userName);
	}

}
