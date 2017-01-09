package com.nnfs.api.account.service;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.springframework.stereotype.Service;

@Service("authenService")
public class AuthenService implements Realm {

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		String username = (String) arg0.getPrincipal();
		String password = new String((char[]) arg0.getCredentials());
		if (!"zhang".equals(username)) {
			throw new UnknownAccountException(); // 如果用户名错误
		}
		if (!"123".equals(password)) {
			throw new IncorrectCredentialsException(); // 如果密码错误
		}
		return new SimpleAuthenticationInfo(username, password, this.getName());
	}

	@Override
	public String getName() {
		return "authenService";
	}

	@Override
	public boolean supports(AuthenticationToken arg0) {
		return arg0 instanceof UsernamePasswordToken;
	}

}
