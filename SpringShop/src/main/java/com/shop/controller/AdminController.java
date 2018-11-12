package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
	
	@GetMapping
	public String displayAdminPage(){
		return "admin/adminForm";
	}
	
	@GetMapping(params = "ordersToAccept")
	public String displayOrdersToAccept(){
		return "redirect:/order";
	}
	
	@GetMapping(params = "ordersAccomplished")
    public String displayOrdersAccomplished() {
        return "redirect:/order/accomplished";
    }

	@GetMapping(params = "logout")
    public String logout(RedirectAttributes redirectAttributes) {
        return "redirect:/logout";
    }
	
}
