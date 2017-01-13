package com.nnfs.api.account.domain;

import java.util.List;

public class Role {
	private long id;
	private String name;
	private int type;
	private List<Permission> permissions;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
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
