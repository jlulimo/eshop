package com.nnfs.api.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nnfs.api.account.domain.GroupPermission;

@Mapper
public interface GroupPermissionMapper extends GenericMapper<GroupPermission>{
	void batchAdd(List<GroupPermission> groupPermissions);
}
