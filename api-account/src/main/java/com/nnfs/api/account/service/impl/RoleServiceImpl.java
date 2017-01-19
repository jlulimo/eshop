package com.nnfs.api.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnfs.api.account.dao.RoleDao;
import com.nnfs.api.account.domain.Role;
import com.nnfs.api.account.dto.RoleDto;
import com.nnfs.api.account.service.RoleService;

@Service
public class RoleServiceImpl extends GenericServiceImpl<RoleDto, Role> implements RoleService {

	@Autowired
	private RoleDao roleDaoImpl;

	@Override
	public RoleDto getRoleByName(String roleName) {
		return this.convertToDto(roleDaoImpl.getRoleByName(roleName));
	}

	@Override
	public int count() {
		return this.roleDaoImpl.count();
	}

	@Override
	public Role convertToDomain(RoleDto t) {
		Role role = new Role();
		role.setId(t.getId());
		role.setName(t.getName());
		role.setPermissions(t.getPermissions());
		role.setType(t.getType());
		return role;
	}

	@Override
	public RoleDto convertToDto(Role d) {
		RoleDto dto = new RoleDto();
		dto.setId(d.getId());
		dto.setName(d.getName());
		dto.setPermissions(d.getPermissions());
		dto.setType(d.getType());
		return dto;
	}

}
