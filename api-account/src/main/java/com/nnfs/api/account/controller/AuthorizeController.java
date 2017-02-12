package com.nnfs.api.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnfs.api.account.constant.PromptMsg;
import com.nnfs.api.account.dto.ClientDto;
import com.nnfs.api.account.dto.Result;
import com.nnfs.api.account.service.ClientService;
import com.nnfs.api.account.service.OAuthService;

@Controller
public class AuthorizeController {
	@Autowired
	private OAuthService oAuthService;
	@Autowired
	private ClientService clientService;

	@RequestMapping("/authorize")
	public Result authorize(HttpServletRequest request) throws OAuthSystemException {
//		try {
//			// 构建OAuth 授权请求
//			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
//			// 检查传入的客户端id是否正确
//			if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
//				OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
//						.setError(OAuthError.TokenResponse.INVALID_CLIENT)
//						.setErrorDescription(PromptMsg.INVALID_CLIENT.getMsg()).buildJSONMessage();
//				Result result = new Result();
//				result.setCode(response.getResponseStatus());
//				result.setMsg(PromptMsg.INVALID_CLIENT.getMsg());
//				result.setData(response.getBody());
//				return result;
//			}
//			Subject subject = SecurityUtils.getSubject();
//			// 如果用户没有登录，跳转到登陆页面
//			if (!subject.isAuthenticated()) {
//				// 登录失败时跳转到登陆页面
//				if (!login(subject, request)) {
//					Result result = new Result();
//					result.setCode(PromptMsg.login_error.getCode());
//					result.setMsg(PromptMsg.login_error.getMsg());
//					ClientDto clientDto = clientService.findByClientId(oauthRequest.getClientId());
//					result.setData(clientDto);
//					return result;
//				}
//			}
//			String username = (String) subject.getPrincipal();
//			// 生成授权码
//			String authorizationCode = null;
//			// responseType目前仅支持CODE，另外还有TOKEN
//			String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
//			if (responseType.equals(ResponseType.CODE.toString())) {
//				OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
//				authorizationCode = oauthIssuerImpl.authorizationCode();
//				oAuthService.addAuthCode(authorizationCode, username);
//			}
//			// 进行OAuth响应构建
//			OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request,
//					HttpServletResponse.SC_FOUND);
//			// 设置授权码
//			builder.setCode(authorizationCode);
//			// 得到到客户端重定向地址
//			String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
//			// 构建响应
//			final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
//			Result result = new Result();
//			result.setCode(response.getResponseStatus());
//			result.setMsg(HttpStatus.valueOf(response.getResponseStatus()).getReasonPhrase());
//			result.setData(response.getLocationUri());
//			return result;
//		} catch (OAuthProblemException e) {
//			e.printStackTrace();
//			String redirectUri = e.getRedirectUri();
//			if (OAuthUtils.isEmpty(redirectUri)) {
//				// 告诉客户端没有传入redirectUri直接报错
//				Result result = new Result();
//				result.setCode(PromptMsg.OAuth_callback_url_needs_to_be_provided_by_client.getCode());
//				result.setMsg(PromptMsg.OAuth_callback_url_needs_to_be_provided_by_client.getMsg());
//				return result;
//			}
//
//			// 返回错误消息（如?error=）
//			final OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND).error(e)
//					.location(redirectUri).buildQueryMessage();
//			Result result = new Result();
//			result.setCode(response.getResponseStatus());
//			result.setMsg(HttpStatus.valueOf(response.getResponseStatus()).getReasonPhrase());
//			result.setData(response.getLocationUri());
//			return result;
//		}
	}

	private boolean login(Subject subject, HttpServletRequest request) {
		if ("get".equalsIgnoreCase(request.getMethod())) {
			return false;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return false;
		}
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
