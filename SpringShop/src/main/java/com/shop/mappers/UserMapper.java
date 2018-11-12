package com.shop.mappers;

import org.springframework.stereotype.Component;

import com.shop.model.dto.UserDTO;
import com.shop.model.entity.User;

@Component
public class UserMapper implements Mapper<User, UserDTO>{

	@Override
	public UserDTO convertEntityToDTO(User e) {
		return new UserDTO(e.getUsername(), e.getEmail());
	}

}
