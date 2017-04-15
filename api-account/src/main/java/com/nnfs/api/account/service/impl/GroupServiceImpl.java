package com.nnfs.api.account.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nnfs.api.account.dao.GroupDao;
import com.nnfs.api.account.dao.GroupPermissionDao;
import com.nnfs.api.account.domain.Group;
import com.nnfs.api.account.domain.GroupPermission;
import com.nnfs.api.account.dto.GroupDto;
import com.nnfs.api.account.service.GroupService;

@Service
public class GroupServiceImpl extends GenericServiceImpl<GroupDto, Group> implements GroupService {

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private GroupPermissionDao groupPermissionDao;

	@Override
	public Group convertToDomain(GroupDto t) {
		if (null == t) {
			return null;
		}
		Group domain = new Group();
		if (StringUtils.isEmpty(t.getGroupId())) {
			t.setGroupId(UUID.randomUUID().toString());
		}
		domain.setId(t.getId());
		domain.setGroupId(t.getGroupId());
		domain.setName(t.getName());
		domain.setDescription(t.getDescription());
		domain.setType(t.getType());
		return domain;
	}

	@Override
	public GroupDto convertToDto(Group d) {
		GroupDto dto = new GroupDto();
		dto.setGroupId(d.getGroupId());
		dto.setId(d.getId());
		dto.setName(d.getName());
		dto.setType(d.getType());
		dto.setDescription(d.getDescription());
		return dto;
	}

	public List<GroupPermission> convertToGPDomain(GroupDto t) {
		if (null == t) {
			return null;
		}
		List<GroupPermission> groupPermissions = new ArrayList<>();
		if (!CollectionUtils.isEmpty(t.getPermissions())) {
			for (String permissionId : t.getPermissions()) {
				GroupPermission domain = new GroupPermission();
				domain.setGroupId(t.getGroupId());
				domain.setPermissionId(permissionId);
				groupPermissions.add(domain);
			}
		}
		return groupPermissions;
	}

	@Override
	public void add(GroupDto t) {
		this.groupDao.create(this.convertToDomain(t));
		this.groupPermissionDao.batchAdd(this.convertToGPDomain(t));
	}
}
