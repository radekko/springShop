package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.entity.domain.Role;
import com.shop.model.entity.domain.UserDTO;
import com.shop.service.CategoryService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	private AuthenticationTrustResolver authenticationTrustResolver;
	private CategoryService categoryService;
	private static final String AFTER_LOGIN_AS_ADMIN = "/order";
	private static final String AFTER_LOGIN_AS_USER = "/main/displayOffer";
			

	@Autowired
	public LoginController(CategoryService catService, AuthenticationTrustResolver authenticationTrustResolver) {
		this.categoryService = catService;
		this.authenticationTrustResolver = authenticationTrustResolver;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(Model model, RedirectAttributes redirectAttributes) {
		switch (getMainRole()) {
		case ROLE_ADMIN:
			redirectAttributes.addAttribute("page", 1);
			return "redirect:" + AFTER_LOGIN_AS_ADMIN;
		case ROLE_USER:
			setRedirectAttributes(redirectAttributes);
			return "redirect:" + AFTER_LOGIN_AS_USER;
		default:
			model.addAttribute(new UserDTO());
			return "loginForm";
		}
	}

	private void setRedirectAttributes(RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("categoryName", categoryService.getFirstCategory().getCategoryName());
		redirectAttributes.addAttribute("page", 1);
	}

	private Role getMainRole() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authenticationTrustResolver.isAnonymous(authentication))
			return Role.NONE;

		boolean hasAdminRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		
		return hasAdminRole ? Role.ROLE_ADMIN : Role.ROLE_USER;
	}
}
