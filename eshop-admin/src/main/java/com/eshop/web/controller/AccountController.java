package com.eshop.web.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import com.alibaba.fastjson.JSON;
import com.eshop.web.model.AccountModel;
import com.eshop.web.model.LoginModel;
import com.eshop.web.model.RegisterModel;
import com.eshop.web.model.ResultModel;
import com.eshop.web.util.HttpUtil;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
@RequestMapping(value="account")
public class AccountController {

	@RequestMapping(value={"/","/index"})
	String index(Model model) {
		model.addAttribute("now", LocalDateTime.now());
		return "/account/index";
	}

	@GetMapping("properties")
	@ResponseBody
	java.util.Properties properties() {
		return System.getProperties();
	}

	@RequestMapping("/login")
	public String home() {
		return "/account/login";
	}

	@RequestMapping("/register")
	public String register() {
		return "account/register";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard() {
		return "account/dashboard";
	}
	
	

	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel doRegister(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, @RequestParam(value = "confirmPwd") String confirmPwd,
			@RequestParam(value = "email") String email) {
		ResultModel resultModel = null;
		RegisterModel registerModel = new RegisterModel();
		registerModel.setUsername(username);
		registerModel.setPassword(password);
		registerModel.setConfirmPwd(confirmPwd);
		registerModel.setEmail(email);

		// build a request
		Request request = new Request.Builder().url(HttpUtil.ACCOUNT_BASE_URL + "/register")
				.post(RequestBody.create(HttpUtil.JSON_TYPE, JSON.toJSONString(registerModel))).build();
		try {
			Response response = HttpUtil.getHttpClient().newCall(request).execute();
			if (response.isSuccessful()) {
				String resp = response.body().string();
				resultModel = JSON.parseObject(resp, ResultModel.class);
				return resultModel;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultModel;
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel doLogin(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, @RequestParam(value = "rememberMe") Boolean rememberMe) {
		ResultModel resultModel = null;
		LoginModel loginModel = new LoginModel();
		loginModel.setUsername(username);
		loginModel.setPassword(password);
		loginModel.setRememberMe(rememberMe);
		// build a request
		Request request = new Request.Builder().url(HttpUtil.ACCOUNT_BASE_URL + "/login")
				.post(RequestBody.create(HttpUtil.JSON_TYPE, JSON.toJSONString(loginModel))).build();
		try {
			Response response = HttpUtil.getHttpClient().newCall(request).execute();
			if (response.isSuccessful()) {
				String resp = response.body().string();
				resultModel = JSON.parseObject(resp, ResultModel.class);
				return resultModel;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultModel;
	}

	@RequestMapping(value = "/menuInfo", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getMenuInfo() {
		ResultModel resultModel = new ResultModel();
		resultModel.setCode(0);
		resultModel.setData(5);
		return resultModel;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getUsers(){
		ResultModel resultModel = new ResultModel();
		List<AccountModel> users = new ArrayList<>();
		AccountModel accountModel= new AccountModel();
		accountModel.setName("mark");
		accountModel.setGroupName("dev");
		users.add(accountModel);
		resultModel.setCode(0);
		resultModel.setData(users);
		return resultModel;
	}

}
