package com.shop.service;

import com.shop.config.security.Role;

public interface AuthenticationService {
	Role getMainRole();
	String getCurrentLoggedUsername();
}
