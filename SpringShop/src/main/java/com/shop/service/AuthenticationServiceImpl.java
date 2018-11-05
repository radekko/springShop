package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.shop.config.Role;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private AuthenticationTrustResolver authenticationTrustResolver;
	
	@Autowired
	public AuthenticationServiceImpl(AuthenticationTrustResolver authenticationTrustResolver) {
		this.authenticationTrustResolver = authenticationTrustResolver;
	}
	
	@Override
	public String getCurrentLoggedUsername() {
		UserDetails userDetails = (UserDetails) getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}

	@Override
	public Role getMainRole() {
		Authentication authentication = getAuthentication();
		if (authenticationTrustResolver.isAnonymous(authentication))
			return Role.NONE;

		boolean hasAdminRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		
		return hasAdminRole ? Role.ROLE_ADMIN : Role.ROLE_USER;
	}
	
	private Authentication  getAuthentication(){
		return SecurityContextHolder.getContext().getAuthentication();
	}

}