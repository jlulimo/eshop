package com.nnfs.api.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nnfs.api.account.constant.MenuType;
import com.nnfs.api.account.dto.MenuDto;
import com.nnfs.api.account.model.ApiResult;
import com.nnfs.api.account.model.GroupModel;
import com.nnfs.api.account.model.MenuModel;
import com.nnfs.api.account.service.GroupService;

@Controller
@RequestMapping(value = "permission")
public class PermissionController extends BaseController<MenuModel, MenuDto>{

	@Autowired
	private GroupService groupService;
	
	@Override
	public MenuDto convertToDto(MenuModel d) {
		MenuDto menuDto = new MenuDto();
		menuDto.setMenuId(d.getId());
		menuDto.setName(d.getText());
		menuDto.setParentId(d.getParent());
		menuDto.setStatus(d.getStatus());
		switch (d.getType()) {
		case 0:
			menuDto.setType(MenuType.MODEL);
			break;
		case 1:
			menuDto.setType(MenuType.MENU);
			break;
		case 2:
			menuDto.setType(MenuType.FUNCTION);
			break;
		default:
			break;
		}
		menuDto.setUrl(d.getUrl());
		return menuDto;
	}

	@Override
	public MenuModel convertToModel(MenuDto t) {
		MenuModel menuModel = new MenuModel();
		menuModel.setId(t.getMenuId());
		menuModel.setParent(t.getParentId());
		menuModel.setStatus(t.getStatus());
		menuModel.setText(t.getName());
		menuModel.setType(t.getType().ordinal());
		menuModel.setUrl(t.getUrl());
		menuModel.setChildren(true);
		return menuModel;
	}
	
	
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult apply(@RequestBody GroupModel groupModel){
		
		return null;
	}
	

}
