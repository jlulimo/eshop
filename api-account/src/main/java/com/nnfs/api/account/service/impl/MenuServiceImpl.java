package com.nnfs.api.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnfs.api.account.dao.MenuDao;
import com.nnfs.api.account.domain.Menu;
import com.nnfs.api.account.dto.MenuDto;
import com.nnfs.api.account.service.MenuService;

@Service
public class MenuServiceImpl extends GenericServiceImpl<MenuDto, Menu> implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public MenuDto getMenuById(String id) {
		return this.convertToDto(menuDao.getMenuById(id));
	}

	@Override
	public Menu convertToDomain(MenuDto t) {
		Menu domain = new Menu();
		if (null == t) {
			return null;
		}
		domain.setMenuId(t.getMenuId());
		domain.setName(t.getName());
		domain.setParentId(t.getParentId());
		return domain;
	}

	@Override
	public MenuDto convertToDto(Menu d) {
		if (null == d) {
			return null;
		}
		MenuDto dto = new MenuDto();
		dto.setMenuId(d.getMenuId());
		dto.setName(d.getName());
		dto.setParentId(d.getParentId());
		return dto;
	}

	@Override
	public List<MenuDto> getChildrenByParentId(String pid) {
		// TODO Auto-generated method stub
		return null;
	}

}
