package com.shop.service;

import com.shop.model.dto.UserDTO;
import com.shop.model.entity.User;

public interface UserService {
	User getByUsername(String userName);
	boolean storeUserIfNotExist(UserDTO user);
}
