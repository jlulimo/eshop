package com.nnfs.api.account.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nnfs.api.account.dao.GroupDao;
import com.nnfs.api.account.domain.Group;
import com.nnfs.api.account.mapper.GroupMapper;

public class GroupDaoImpl extends GenericDaoImpl<Group> implements GroupDao {
	@Autowired
	private GroupMapper groupMapper;

	@Autowired
	public void setBaseMapper() {
		super.setMapper(this.groupMapper);
	}

	public void setMenuMapper(GroupMapper groupMapper) {
		this.groupMapper = groupMapper;
	}
	
	
}
