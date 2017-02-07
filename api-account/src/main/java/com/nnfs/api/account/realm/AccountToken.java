package com.nnfs.api.account.realm;

import org.apache.shiro.authc.AuthenticationToken;

@SuppressWarnings("serial")
public class AccountToken implements AuthenticationToken {

	private String username;

	private String password;

	public AccountToken() {
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Object getPrincipal() {
		return this.getUsername();
	}

	@Override
	public Object getCredentials() {
		return this.getPassword();
	}

	
}
