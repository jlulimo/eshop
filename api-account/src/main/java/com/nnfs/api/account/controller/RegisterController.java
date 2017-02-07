package com.nnfs.api.account.controller;

import java.util.UUID;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nnfs.api.account.constant.PromptMsg;
import com.nnfs.api.account.dto.AccountDto;
import com.nnfs.api.account.dto.Result;
import com.nnfs.api.account.service.AccountService;

public class RegisterController {
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public Result register(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, @RequestParam(value = "confirmPwd") String confirmPwd,
			@RequestParam(value = "email") String email) {
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountId(UUID.randomUUID().toString());
		accountDto.setEnable(true);
		accountDto.setName(username);
		accountDto.setEmail(email);
		String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
		accountDto.setSalt(salt);
		SimpleHash hash = new SimpleHash("md5", accountDto.getPassword(), accountDto.getName() + salt, 2);
		String encodedPassword = hash.toHex();
		accountDto.setPassword(encodedPassword);

		AccountDto addedAccount = accountService.registerAccount(accountDto);
		if (null != addedAccount) {
			Result result = new Result();
			result.setCode(PromptMsg.regist_success.getCode());
			result.setMsg(PromptMsg.regist_success.getMsg());
			return result;
		}
		return null;
	}
}
