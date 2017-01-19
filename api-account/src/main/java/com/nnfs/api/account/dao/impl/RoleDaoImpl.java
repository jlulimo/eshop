package com.nnfs.api.account.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nnfs.api.account.dao.RoleDao;
import com.nnfs.api.account.domain.Role;
import com.nnfs.api.account.mapper.RoleMapper;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Role getRoleByName(String roleName) {
		return this.roleMapper.getRoleByName(roleName);
	}

	@Override
	public int count() {
		return this.roleMapper.count();
	}

}
