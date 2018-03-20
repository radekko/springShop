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
	
	@Autowired
    private UserDao dao;

	@Override
	public void saveUser(User user) {
		dao.addUser(user);
	}
	
	@Override
	public boolean findIfExist(User login) {
		return dao.findIfUserExist(login);
	}

}
