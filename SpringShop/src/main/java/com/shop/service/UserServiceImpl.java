package com.shop.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.UserDao;
import com.shop.model.User;

@Service/*("employeeService")*/
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
	

	@Override
	public void updateUser(User employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUserBySsn(String ssn) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserBySsn(String ssn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserSsnUnique(Integer id, String ssn) {
		// TODO Auto-generated method stub
		return false;
	}



}
