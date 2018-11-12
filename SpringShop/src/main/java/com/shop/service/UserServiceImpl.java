package com.shop.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.UserDao;
import com.shop.model.dto.UserDTO;
import com.shop.model.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService, Serializable {

	private static final long serialVersionUID = 1L;
	private final static String USER_ROLE = "USER";
    private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao dao) {
		this.userDao = dao;
	}
	
	@Override
	public boolean storeUserIfNotExist(UserDTO userDTO) {
		User user = convertUserDTOtoUser(userDTO);
		
		if (userDao.findIfUserExist(user))
			return false;

		user.setRole(USER_ROLE);
		userDao.saveUser(user);
		return true;
	}
	
	@Override
	public User getByUsername(String userName) {
		return userDao.getByUsername(userName);
	}
	
	private User convertUserDTOtoUser(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		return user;
	}
}