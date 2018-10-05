package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.entity.domain.UserDTO;
import com.shop.service.AuthenticationService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	private AuthenticationService authenticationService;
	private static final String AFTER_LOGIN_AS_ADMIN = "/order";
	private static final String AFTER_LOGIN_AS_USER = "/main/displayOffer";
			
	@Autowired
	public LoginController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(Model model, RedirectAttributes redirectAttributes) {
		switch (authenticationService.getMainRole(SecurityContextHolder.getContext().getAuthentication())) {
		case ROLE_ADMIN:
			return "redirect:" + AFTER_LOGIN_AS_ADMIN;
		case ROLE_USER:
			return "redirect:" + AFTER_LOGIN_AS_USER;
		default:
			model.addAttribute(new UserDTO());
			return "loginForm";
		}
	}
}
