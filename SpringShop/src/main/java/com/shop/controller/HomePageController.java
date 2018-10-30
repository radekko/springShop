package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public  String home(){
		return "home";
	}
	
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public  String unauthorizedAccess(){		
		return "403";
	}
	
}
