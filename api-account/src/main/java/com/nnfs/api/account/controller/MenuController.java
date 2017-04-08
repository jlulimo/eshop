package com.nnfs.api.account.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "browse", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult browse(@RequestParam(value = "pid") String parentId) {
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

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "{menuId}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult query(@PathVariable(value = "menuId") String menuId) {
		ApiResult resultModel = new ApiResult();
		if (StringUtils.isEmpty(menuId)) {
			resultModel.setCode(PromptMsg.QUERY_FAILED.getCode());
		} else {
			try {
				MenuDto menuDto = this.menuService.getById(menuId);
				resultModel.setCode(PromptMsg.SUCCESS.getCode());
				resultModel.setData(this.convertToModel(menuDto));
			} catch (Exception e) {
			}
		}
		return resultModel;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult add(@RequestBody MenuModel menuModel) {
		ApiResult resultModel = new ApiResult();
		if (null == menuModel) {
			resultModel.setCode(PromptMsg.ADD_FAILED.getCode());
			resultModel.setMsg(PromptMsg.ADD_FAILED.getMsg());
		} else if (StringUtils.isEmpty(menuModel.getText())) {
			resultModel.setCode(PromptMsg.ADD_FAILED.getCode());
			resultModel.setMsg("节点名字为空");
		} else if (StringUtils.isEmpty(menuModel.getParent())) {
			resultModel.setCode(PromptMsg.ADD_FAILED.getCode());
			resultModel.setMsg("父节点ID为空");
		} else {
			try {
				this.menuService.add(this.convertToDto(menuModel));
				resultModel.setCode(PromptMsg.SUCCESS.getCode());
				resultModel.setData(menuModel.getParent());
			} catch (Exception e) {
				resultModel.setCode(PromptMsg.ADD_FAILED.getCode());
				resultModel.setMsg("menu name existed.");
			}
		}
		return resultModel;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult edit(@RequestBody MenuModel menuModel) {
		ApiResult resultModel = new ApiResult();
		if (null == menuModel) {
			resultModel.setCode(PromptMsg.EDIT_FAILED.getCode());
			resultModel.setMsg(PromptMsg.EDIT_FAILED.getMsg());
		} else if (StringUtils.isEmpty(menuModel.getText())) {
			resultModel.setCode(PromptMsg.EDIT_FAILED.getCode());
			resultModel.setMsg("节点名字为空");
		} else if (StringUtils.isEmpty(menuModel.getParent())) {
			resultModel.setCode(PromptMsg.EDIT_FAILED.getCode());
			resultModel.setMsg("节点ID为空");
		} else {
			try {
				this.menuService.update(this.convertToDto(menuModel));
				resultModel.setCode(PromptMsg.SUCCESS.getCode());
				resultModel.setData(menuModel.getParent());
			} catch (Exception e) {
				resultModel.setCode(PromptMsg.EDIT_FAILED.getCode());
				resultModel.setMsg("menu name existed.");
			}
		}
		return resultModel;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult delete(@RequestBody MenuModel menuModel) {
		ApiResult resultModel = new ApiResult();
		if (null == menuModel) {
			resultModel.setCode(PromptMsg.DEL_FAILED.getCode());
			resultModel.setMsg(PromptMsg.DEL_FAILED.getMsg());
		} else if (StringUtils.isEmpty(menuModel.getId())) {
			resultModel.setCode(PromptMsg.DEL_FAILED.getCode());
			resultModel.setMsg("节点ID为空");
		} else {
			try {
				this.menuService.deleteById(menuModel.getId());
				resultModel.setCode(PromptMsg.SUCCESS.getCode());
			} catch (Exception e) {
				resultModel.setCode(PromptMsg.DEL_FAILED.getCode());
				resultModel.setMsg(PromptMsg.DEL_FAILED.getMsg());
			}
		}
		return resultModel;
	}

	@Override
	public MenuDto convertToDto(MenuModel d) {
		if (null == d) {
			return null;
		}
		MenuDto dto = new MenuDto();
		dto.setMenuId(d.getId());
		dto.setName(d.getText());
		dto.setParentId(d.getParent());
		dto.setStatus(d.getStatus());
		dto.setUrl(d.getUrl());
		return dto;
	}

	@Override
	public MenuModel convertToModel(MenuDto t) {
		if (null == t) {
			return null;
		}
		MenuModel model = new MenuModel();
		model.setId(t.getMenuId());
		model.setText(t.getName());
		model.setParent(t.getParentId());
		model.setChildren(true);
		model.setStatus(t.getStatus());
		model.setUrl(t.getUrl());
		return model;
	}
	
}
