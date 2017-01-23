package com.nnfs.api.account.realm;

import java.util.Map;

import org.apache.shiro.authc.AuthenticationToken;

@SuppressWarnings("serial")
public class AccountToken implements AuthenticationToken {

	private String username;
	private Map<String, ?> params;
	private String clientDigest;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Map<String, ?> getParams() {
		return params;
	}

	public void setParams(Map<String, ?> params) {
		this.params = params;
	}

	public String getClientDigest() {
		return clientDigest;
	}

	public void setClientDigest(String clientDigest) {
		this.clientDigest = clientDigest;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

}
