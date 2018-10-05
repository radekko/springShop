package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.shop.model.entity.domain.Role;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private AuthenticationTrustResolver authenticationTrustResolver;
	
	@Autowired
	public AuthenticationServiceImpl(AuthenticationTrustResolver authenticationTrustResolver) {
		this.authenticationTrustResolver = authenticationTrustResolver;
	}

	@Override
	public Role getMainRole(Authentication authentication) {
		if (authenticationTrustResolver.isAnonymous(authentication))
			return Role.NONE;

		boolean hasAdminRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		
		return hasAdminRole ? Role.ROLE_ADMIN : Role.ROLE_USER;
	}

}