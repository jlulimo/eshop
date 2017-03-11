package com.eshop.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.eshop.web.constants.PromptMsg;
import com.eshop.web.model.MenuNode;
import com.eshop.web.model.ResultModel;
import com.eshop.web.util.HttpUtil;

@Controller
@RequestMapping(value = "menu")
public class MenuController {
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "/menu/index";
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "browse", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel browseTree(@RequestParam(value = "nodeId") String nodeId) {
		ResultModel result = new ResultModel();
		if (StringUtils.isEmpty(nodeId)) {
			result.setCode(PromptMsg.QUERY_FAILED.getCode());
			result.setMsg("节点id为空");
			return result;
		} else if ("#".equalsIgnoreCase(nodeId)) {
			MenuNode root = new MenuNode();
			root.setChildren(true);
			root.setId("root");
			root.setParent("#");
			root.setText("菜单类别管理");
			result.setCode(PromptMsg.SUCCESS.getCode());
			result.setData(root);
		} else {
			Map<String, String> parm = new HashMap<>();
			parm.put("pid", nodeId);
			ResultModel apiResult = HttpUtil.exectGet(ResultModel.class, HttpUtil.ACCOUNT_BASE_URL + "/menu/browse",
					parm);
			if (apiResult.getCode() == PromptMsg.SUCCESS.getCode()) {
				result.setCode(PromptMsg.SUCCESS.getCode());
				result.setData(apiResult.getData());
			} else {
				result.setCode(PromptMsg.QUERY_FAILED.getCode());
				result.setMsg(PromptMsg.QUERY_FAILED.getMsg());
			}
		}
		return result;
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel add(@RequestBody MenuNode menuNode) {
		ResultModel resultModel = new ResultModel();
		if (null == menuNode) {
			resultModel.setCode(PromptMsg.ADD_FAILED.getCode());
			resultModel.setMsg(PromptMsg.ADD_FAILED.getMsg());
		} else if (StringUtils.isEmpty(menuNode.getText())) {
			resultModel.setCode(PromptMsg.ADD_FAILED.getCode());
			resultModel.setMsg("节点名字为空");
		} else if (StringUtils.isEmpty(menuNode.getParent())) {
			resultModel.setCode(PromptMsg.ADD_FAILED.getCode());
			resultModel.setMsg("父节点ID为空");
		} else {
			resultModel = HttpUtil.exectPost(resultModel.getClass(),  menuNode, HttpUtil.ACCOUNT_BASE_URL + "/menu/add");
		}
		return resultModel;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel edit(@RequestBody MenuNode menuNode) {
		ResultModel resultModel = new ResultModel();
		if (null == menuNode) {
			resultModel.setCode(PromptMsg.EDIT_FAILED.getCode());
			resultModel.setMsg(PromptMsg.EDIT_FAILED.getMsg());
		} else if (StringUtils.isEmpty(menuNode.getText())) {
			resultModel.setCode(PromptMsg.EDIT_FAILED.getCode());
			resultModel.setMsg("节点名字为空");
		} else if (StringUtils.isEmpty(menuNode.getId())) {
			resultModel.setCode(PromptMsg.EDIT_FAILED.getCode());
			resultModel.setMsg("节点ID为空");
		} else if (StringUtils.isEmpty(menuNode.getParent())) {
			resultModel.setCode(PromptMsg.EDIT_FAILED.getCode());
			resultModel.setMsg("父节点ID为空");
		} else {
			resultModel = HttpUtil.exectPost(resultModel.getClass(), menuNode, HttpUtil.ACCOUNT_BASE_URL + "/menu/edit");
		}
		return resultModel;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel delete(@RequestBody MenuNode menuNode) {
		ResultModel resultModel = new ResultModel();
		if (null == menuNode) {
			resultModel.setCode(PromptMsg.DEL_FAILED.getCode());
			resultModel.setMsg(PromptMsg.DEL_FAILED.getMsg());
		} else if (StringUtils.isEmpty(menuNode.getId())) {
			resultModel.setCode(PromptMsg.DEL_FAILED.getCode());
			resultModel.setMsg("节点ID为空");
		} else {
			resultModel = HttpUtil.exectPost(resultModel.getClass(), menuNode, HttpUtil.ACCOUNT_BASE_URL + "/menu/delete");
		}
		return resultModel;
	}
}
