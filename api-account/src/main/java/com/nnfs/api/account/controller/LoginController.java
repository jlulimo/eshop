package com.nnfs.api.account.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nnfs.api.account.dto.Result;

@Controller
public class LoginController {
//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@RequestMapping(value="login",method = RequestMethod.POST)
	public Result Login(@RequestParam(value="username") String username,
			@RequestParam(value="password") String password,
			@RequestParam(value="rememberMe") Boolean rememberMe){
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		token.getCredentials();
		try {
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
//			logger.error("Error authenticating.%s",e.getStackTrace());
			return null;
		}
		Result result = new Result();
		result.setCode(0);
		result.setMsg("login success.");
		return result;
	}
}
