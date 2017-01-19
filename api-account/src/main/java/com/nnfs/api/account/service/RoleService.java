package com.nnfs.api.account.service;

import com.nnfs.api.account.dto.RoleDto;

public interface RoleService extends GenericService<RoleDto>{
	public RoleDto getRoleByName(String roleName);
	public int count();
}
