package com.nnfs.api.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nnfs.api.account.service.AccountService;

@Controller
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	
}
