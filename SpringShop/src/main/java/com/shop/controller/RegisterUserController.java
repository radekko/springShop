package com.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.model.entity.domain.UserDTO;
import com.shop.model.entity.persistent.User;
import com.shop.service.UserService;

@Controller
public class RegisterUserController {

	private UserService userService;
	
	@Autowired
	public RegisterUserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new UserDTO());
		return "registerForm";
	}

	@RequestMapping(value = "/register", params = "save", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult errors, Model model) {
		if (errors.hasErrors())
			return "registerForm";

		User user = convertUserDTOtoUser(userDTO);
		
		if(userService.storeUserIfNotExist(user))
			return "successRegistered";
		else {
			model.addAttribute("alreadyExist", true);
			return "registerForm";
		}
	}
	
	private User convertUserDTOtoUser(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		return user;
	}

}
