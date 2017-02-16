package com.nnfs.api.account.controller;

import java.util.UUID;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nnfs.api.account.constant.PromptMsg;
import com.nnfs.api.account.dto.AccountDto;
import com.nnfs.api.account.dto.Result;
import com.nnfs.api.account.model.RegisterModel;
import com.nnfs.api.account.service.AccountService;

@Controller
public class RegisterController {
	@Autowired
	private AccountService accountService;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Result register(@RequestBody RegisterModel registerModel) {
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountId(UUID.randomUUID().toString());
		accountDto.setEnable(true);
		accountDto.setName(registerModel.getUsername());
		accountDto.setEmail(registerModel.getEmail());
		String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
		accountDto.setSalt(salt);
		SimpleHash hash = new SimpleHash("md5", registerModel.getPassword(), accountDto.getName() + salt, 2);
		String encodedPassword = hash.toHex();
		accountDto.setPassword(encodedPassword);

		AccountDto addedAccount = accountService.registerAccount(accountDto);
		if (null != addedAccount) {
			Result result = new Result();
			result.setCode(PromptMsg.ACCOUNT_ADD_SUCCESS.getCode());
			result.setMsg(PromptMsg.ACCOUNT_ADD_SUCCESS.getMsg());
			return result;
		}
		return null;
	}
}
