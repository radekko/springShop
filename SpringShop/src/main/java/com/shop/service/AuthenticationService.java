package com.shop.service;

import org.springframework.security.core.Authentication;

import com.shop.model.entity.domain.Role;

public interface AuthenticationService {
	Role getMainRole(Authentication authentication);
}
