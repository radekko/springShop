package com.shop.service;

import com.shop.model.entity.domain.Role;

public interface AuthenticationService {
	Role getMainRole();
	String getCurrentLoggedUsername();
}
