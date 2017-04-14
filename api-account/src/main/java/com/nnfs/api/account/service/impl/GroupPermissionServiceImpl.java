package com.nnfs.api.account.service.impl;

import com.nnfs.api.account.domain.GroupPermission;
import com.nnfs.api.account.dto.GroupPermissionDto;
import com.nnfs.api.account.service.GroupPermissionService;

public class GroupPermissionServiceImpl extends GenericServiceImpl<GroupPermissionDto, GroupPermission>
		implements GroupPermissionService {

	@Override
	public GroupPermission convertToDomain(GroupPermissionDto t) {
		GroupPermission domain = new GroupPermission();
		domain.setGroupId(t.getGroupId());
		domain.setId(t.getId());
		domain.setPermissionId(t.getPermissionId());
		return domain;
	}

	@Override
	public GroupPermissionDto convertToDto(GroupPermission d) {
		GroupPermissionDto dto = new GroupPermissionDto();
		dto.setGroupId(d.getGroupId());
		dto.setId(d.getId());
		dto.setPermissionId(d.getPermissionId());
		return dto;
	}

}
