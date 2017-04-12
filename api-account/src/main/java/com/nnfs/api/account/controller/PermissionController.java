package com.nnfs.api.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnfs.api.account.dto.MenuDto;
import com.nnfs.api.account.model.MenuModel;

@Controller
@RequestMapping(value = "permission")
public class PermissionController extends BaseController<MenuModel, MenuDto>{

	@Override
	public MenuDto convertToDto(MenuModel d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuModel convertToModel(MenuDto t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
