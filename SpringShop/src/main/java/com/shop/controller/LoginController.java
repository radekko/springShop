package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.dto.UserDTO;
import com.shop.service.AuthenticationService;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

	private AuthenticationService authenticationService;
	private static final String AFTER_LOGIN_AS_ADMIN = "/admin";
	private static final String AFTER_LOGIN_AS_USER = "/main/displayOffer";
			
	@Autowired
	public LoginController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@GetMapping
	public String loginPage(Model model, RedirectAttributes redirectAttributes) {
		switch (authenticationService.getMainRole()) {
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