package com.nnfs.api.account.dao;

import java.util.List;

import com.nnfs.api.account.domain.GroupPermission;

public interface GroupPermissionDao extends GenericDao<GroupPermission>{
	void batchAdd(List<GroupPermission> groupPermissions);
}
