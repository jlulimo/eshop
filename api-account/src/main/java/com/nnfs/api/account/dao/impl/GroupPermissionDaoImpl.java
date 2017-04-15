package com.nnfs.api.account.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nnfs.api.account.dao.GroupPermissionDao;
import com.nnfs.api.account.domain.GroupPermission;
import com.nnfs.api.account.mapper.GroupPermissionMapper;

@Repository
public class GroupPermissionDaoImpl extends GenericDaoImpl<GroupPermission> implements GroupPermissionDao {

	@Autowired
	private GroupPermissionMapper groupPermissionMapper;

	@Autowired
	public void setBaseMapper() {
		super.setMapper(this.groupPermissionMapper);
	}

	public void setMenuMapper(GroupPermissionMapper menuMapper) {
		this.groupPermissionMapper = menuMapper;
	}

	@Override
	public void batchAdd(List<GroupPermission> groupPermissions) {
		this.groupPermissionMapper.batchAdd(groupPermissions);
	}

}
