package com.nnfs.api.account.dao;

import java.util.List;

import com.nnfs.api.account.domain.Menu;

public interface MenuDao extends GenericDao<Menu> {
	Menu getMenuById(String id);
	List<Menu> getChildrenByParentId(String pid);
}
