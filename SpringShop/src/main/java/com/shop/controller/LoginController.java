package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.model.User;
import com.shop.service.CartService;
import com.shop.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	UserService service;
	
    @Autowired
    CartService cartService;

	public LoginController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showLoginForm(Model model) {
		model.addAttribute(new User());
		return "loginForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String afterIntruduceLoginData(@ModelAttribute User user, Model model) {
		if (!service.findIfExist(user))
		{
			model.addAttribute("notExist", "Invalid username or password");
			return "loginForm";
		}
		cartService.setUsername(user.getUsername());
		return "redirect:/after";
	}

}
