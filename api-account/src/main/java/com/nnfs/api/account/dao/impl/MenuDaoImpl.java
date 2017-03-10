package com.nnfs.api.account.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nnfs.api.account.dao.MenuDao;
import com.nnfs.api.account.domain.Menu;
import com.nnfs.api.account.mapper.MenuMapper;
@Repository
public class MenuDaoImpl extends GenericDaoImpl<Menu> implements MenuDao{

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setMapper(this.menuMapper);
	}
	
	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}

	@Override
	public Menu getMenuById(String id) {
		return menuMapper.getMenuById(id);
	}

	@Override
	public List<Menu> getChildrenByParentId(String pid) {
		return this.menuMapper.getChildrenByParentId(pid);
	}

}
