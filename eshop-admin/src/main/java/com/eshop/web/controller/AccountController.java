package com.eshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {
	
	@RequestMapping("/")
	public String home(){
		return "/account/login";
	}
	
	@RequestMapping("/register")
	public String register(){
		return "account/register";
	}
}
