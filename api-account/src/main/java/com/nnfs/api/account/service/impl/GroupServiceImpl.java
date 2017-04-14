package com.nnfs.api.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnfs.api.account.dao.GroupDao;
import com.nnfs.api.account.dao.GroupPermissionDao;
import com.nnfs.api.account.domain.Group;
import com.nnfs.api.account.dto.GroupDto;
import com.nnfs.api.account.service.GroupService;

@Service
public class GroupServiceImpl extends GenericServiceImpl<GroupDto, Group> implements GroupService {

	@Autowired
	private GroupDao groupDao;

	@Override
	public Group convertToDomain(GroupDto t) {
		Group domain = new Group();
		domain.setGroupId(t.getGroupId());
		domain.setId(t.getId());
		domain.setName(t.getName());
		domain.setType(t.getType());
		domain.setAccounts(t.getAccounts());
		return domain;
	}

	@Override
	public GroupDto convertToDto(Group d) {
		GroupDto dto = new GroupDto();
		dto.setAccounts(d.getAccounts());
		dto.setGroupId(d.getGroupId());
		dto.setId(d.getId());
		dto.setName(d.getName());
		dto.setType(d.getType());
		return dto;
	}

	@Override
	public void add(GroupDto t) {
		groupDao.create(this.convertToDomain(t));
//		groupPermissionDao.create(t);
	}
}
