package com.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.model.User;
import com.shop.service.UserService;

@Controller
public class RegisterUserController {

    @Autowired
    UserService service;
    
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegistrationForm(Model model){
		model.addAttribute(new User());
		return "registerForm";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(@ModelAttribute("user")  @Valid User user, BindingResult errors)
	{	
		if(errors.hasErrors())
			return "registerForm";
		
		service.saveUser(user);
		
		return "successRegistered";
	}

}
