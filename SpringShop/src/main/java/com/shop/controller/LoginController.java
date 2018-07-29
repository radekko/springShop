package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.entity.persistent.User;
import com.shop.service.CategoryService;
import com.shop.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	private UserService userService;
	private CategoryService categoryService;
	
	@Autowired
	public LoginController(UserService service, CategoryService catService) {
		this.userService = service;
		this.categoryService = catService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showLoginForm(Model model) {
		model.addAttribute(new User());
		return "loginForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String afterIntruduceLoginData(@ModelAttribute User user,
			RedirectAttributes redirectAttributes, Model model) {
		if (!userService.checkIfPasswordIsValidWithUser(user)){
			model.addAttribute("notExist", "Invalid username or password");
			return "loginForm";
		}
		redirectAttributes.addFlashAttribute("username", user.getUsername());
		redirectAttributes.addAttribute("categoryName",  categoryService.getFirstCategory().getCategoryName());
    	redirectAttributes.addAttribute("page", 1);
		return "redirect:/main/displayOffer";
	}
}
