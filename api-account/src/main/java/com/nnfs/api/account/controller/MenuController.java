package com.nnfs.api.account.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nnfs.api.account.constant.PromptMsg;
import com.nnfs.api.account.dto.MenuDto;
import com.nnfs.api.account.model.ApiResult;
import com.nnfs.api.account.model.MenuModel;
import com.nnfs.api.account.service.MenuService;

@Controller
@RequestMapping(value = "menu")
public class MenuController extends BaseController<MenuModel, MenuDto> {

	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "browse")
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public ApiResult browse(String parentId) {
		ApiResult result = new ApiResult();
		List<MenuModel> data = new ArrayList<>();
		try {
			List<MenuDto> menuDtos = menuService.getChildrenByParentId(parentId);
			menuDtos.forEach(dto -> data.add(this.convertToModel(dto)));
			result.setCode(PromptMsg.SUCCESS.getCode());
			result.setData(data);
		} catch (Exception e) {
			result.setCode(PromptMsg.QUERY_FAILED.getCode());
			result.setMsg("查询菜单失败");
			logger.error("菜单查询失败", e);
		}
		return result;
	}

	@Override
	public MenuDto convertToDto(MenuModel d) {
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
	public MenuModel convertToModel(MenuDto t) {
		if (null == t) {
			return null;
		}
		MenuModel model = new MenuModel();
		model.setMenuId(t.getMenuId());
		model.setName(t.getName());
		model.setParentId(t.getParentId());
		return model;
	}
}
