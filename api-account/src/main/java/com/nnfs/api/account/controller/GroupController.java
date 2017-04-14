package com.nnfs.api.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nnfs.api.account.constant.PromptMsg;
import com.nnfs.api.account.dto.GroupDto;
import com.nnfs.api.account.model.ApiResult;
import com.nnfs.api.account.model.GroupModel;
import com.nnfs.api.account.service.GroupService;

public class GroupController extends BaseController<GroupModel, GroupDto> {
	@Autowired
	private GroupService groupService;

	@Override
	public GroupDto convertToDto(GroupModel d) {
		GroupDto dto = new GroupDto();
		dto.setAccounts(d.getAccounts());
		dto.setDescription(d.getDescription());
		dto.setGroupId(d.getGroupId());
		dto.setId(d.getId());
		dto.setName(d.getName());
		dto.setPermissions(d.getPermissions());
		dto.setType(d.getType());
		return dto;
	}

	@Override
	public GroupModel convertToModel(GroupDto t) {
		GroupModel model = new GroupModel();
		model.setAccounts(t.getAccounts());
		model.setDescription(t.getDescription());
		model.setGroupId(t.getGroupId());
		model.setId(t.getId());
		model.setName(t.getName());
		model.setPermissions(t.getPermissions());
		model.setType(t.getType());
		return model;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult create(GroupModel groupModel) {
		ApiResult apiResult = new ApiResult();
		try {
			GroupDto dto = this.convertToDto(groupModel);
			groupService.add(dto);
			apiResult.setCode(PromptMsg.SUCCESS.ordinal());
			apiResult.setMsg(PromptMsg.SUCCESS.getMsg());
			apiResult.setData(dto.getGroupId());
		} catch (Exception e) {
			apiResult.setCode(PromptMsg.ADD_FAILED.ordinal());
		}
		return apiResult;
	}

}
