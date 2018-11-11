package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
	@GetMapping(path="/")
	public  String home(){
		return "home";
	}
	
	@GetMapping(path="/403")
	public  String unauthorizedAccess(){		
		return "403";
	}
	
}
