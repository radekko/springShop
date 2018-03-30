package com.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.model.entity.persistent.User;
import com.shop.service.UserService;

@Controller
public class RegisterUserController {

	@Autowired
	UserService service;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new User());
		return "registerForm";
	}

	@RequestMapping(value = "/register", params = "save", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult errors, Model model) {
		if (errors.hasErrors())
			return "registerForm";

		if (service.findIfExist(user)) {
			model.addAttribute("alreadyExist", "User with chosen nickname already exist in database. Chose another.");
			return "registerForm";
		}

		else
			service.saveUser(user);

		return "successRegistered";
	}

	@RequestMapping(value = "/register", params = "back", method = RequestMethod.POST)
	public String backToLoginPage() {
		return "redirect:/";
	}

}
