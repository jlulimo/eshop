package com.nnfs.api.account.mapper;

import com.nnfs.api.account.domain.Role;

public interface RoleMapper extends GenericMapper<Role> {
	public Role getRoleByName(String roleName);
	public int count();
}
