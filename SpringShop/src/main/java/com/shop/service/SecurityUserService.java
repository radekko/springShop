package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.config.SecurityUser;
import com.shop.model.entity.persistent.User;

@Service
@Transactional
public class SecurityUserService implements UserDetailsService {

    private UserService userService;
    
    @Autowired
	public SecurityUserService(UserService userService) {
		this.userService = userService;
	}
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = userService.getByUsername(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("User "
	                    + username + " was not found in the database");
	        }
	        UserDetails userDetails = new SecurityUser(user);
	        return userDetails;
	}
}
