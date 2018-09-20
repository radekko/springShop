package com.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shop.model.entity.persistent.User;

@Repository
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	
	@Override
	public void saveUser(User user) {
		persist(user);
	}

	@Override
	public boolean findIfUserExist(User user) {
		List<User> resultList = selectListOfEntityWithWhere("username",user.getUsername());
		return resultList.isEmpty() ? false: true;
	}
	
	@Override
	public boolean checkIfPasswordIsValidWithUser(User user) {	
		Map<String,String> params = new HashMap<String,String>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		
		List<?> resultList = selectListOfEntityWithMultiWhere(params);
		return resultList.isEmpty() ? false: true;
	}
	
	@Override
	public User getByUsername(String username) {
		return selectUniqueEntityWithWhere("username",username);
	}

}
