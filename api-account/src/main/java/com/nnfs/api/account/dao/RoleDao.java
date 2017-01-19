package com.nnfs.api.account.dao;

import com.nnfs.api.account.domain.Role;

public interface RoleDao extends GenericDao<Role> {
	public Role getRoleByName(String roleName);
	public int count();
}
