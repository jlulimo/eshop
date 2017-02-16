package com.eshop.web.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.eshop.web.model.RegisterModel;
import com.eshop.web.model.ResultModel;
import com.eshop.web.util.HttpUtil;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
public class AccountController {

	@RequestMapping("/")
	public String home() {
		return "/account/login";
	}

	@RequestMapping("/register")
	public String register() {
		return "account/register";
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
		Request request = new Request.Builder().url(HttpUtil.BASE_URL + "/register")
				.post(RequestBody.create(HttpUtil.JSON, JSON.toJSONString(registerModel))).build();
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
		RequestBody formBody = new FormBody.Builder().add("username", username).add("password", password)
				.add("rememberMe", String.valueOf(rememberMe)).build();
		Request request = new Request.Builder().url(HttpUtil.BASE_URL + "/login").post(formBody).build();
		Response response;
		try {
			response = HttpUtil.getHttpClient().newCall(request).execute();
			if (response.isSuccessful()) {
				String resp = response.body().string();
				System.out.println(resp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
