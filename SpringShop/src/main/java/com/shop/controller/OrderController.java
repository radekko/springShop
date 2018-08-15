package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	  @RequestMapping(method = RequestMethod.GET)
	    public String loginPage(Model model,RedirectAttributes redirectAttributes) {
		  return "orderForm";
	    }
	  
}