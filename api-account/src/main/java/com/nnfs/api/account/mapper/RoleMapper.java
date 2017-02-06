package com.nnfs.api.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nnfs.api.account.domain.Role;

@Mapper
public interface RoleMapper extends GenericMapper<Role> {
	public Role getRoleByName(String roleName);
	public int count();
}
