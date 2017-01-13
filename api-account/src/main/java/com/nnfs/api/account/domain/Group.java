package com.nnfs.api.account.domain;

import java.util.List;

public class Group {
	private long id;
	private String name;
	private int type;
	private List<Role> roles;
	private List<Account> accounts;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
