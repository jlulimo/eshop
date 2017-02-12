package com.nnfs.api.account.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

import com.nnfs.api.account.constant.PromptMsg;
import com.nnfs.api.account.constant.ProtocolConstant;
import com.nnfs.api.account.realm.AccountToken;

public class AccountFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object object)
			throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// 1、客户端生成的消息摘要
//		String clientDigest = request.getParameter(ProtocolConstant.digest.getName());
		// 2、客户端传入的用户身份
		String username = request.getParameter(ProtocolConstant.accountName.getName());
		String password = request.getParameter("password");
		// 3、客户端请求的参数列表
		Map<String, String[]> params = new HashMap<String, String[]>(request.getParameterMap());
		params.remove(ProtocolConstant.digest.getName());

		// 4、生成无状态Token
		AccountToken token = new AccountToken();
		token.setUsername(username);
		token.setPassword(password);
//		token.setParams(params);
//		getSubject(request, response)

		try {
			// 5、委托给Realm进行登录
			getSubject(request, response).login(token);
		} catch (Exception e) {
			e.printStackTrace();
			// 6、登录失败
			onLoginFail(response);
			return false;
		}
		return true;
	}

	private void onLoginFail(ServletResponse response) throws IOException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		httpResponse.getWriter().write(PromptMsg.LOGIN_ERROR.getMsg());
	}

}
