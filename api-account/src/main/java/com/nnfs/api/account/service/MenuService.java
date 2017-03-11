package com.nnfs.api.account.service;

import java.util.List;

import com.nnfs.api.account.dto.MenuDto;

public interface MenuService extends GenericService<MenuDto>{
	MenuDto getMenuById(String id);
	List<MenuDto> getChildrenByParentId(String pid);
	void deleteById(String id);
}
