package com.shop.service;

import com.shop.config.Role;

public interface AuthenticationService {
	Role getMainRole();
	String getCurrentLoggedUsername();
}
