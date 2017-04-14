package com.eshop.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
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
@RequestMapping(value = "permission")
public class PermissionController {
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "browse", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel browse(@RequestParam(value = "nodeId") String nodeId){
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
			root.setText("权限分配");
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
	@RequestMapping(value = "apply", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel apply(@RequestBody String[] menuIds){
		ResultModel result = new ResultModel();
		if (!ArrayUtils.isEmpty(menuIds)) {
			Map<String, String[]> parm = new HashMap<>();
			parm.put("menuIds", menuIds);
			ResultModel apiResult = HttpUtil.exectPost(ResultModel.class, parm, HttpUtil.ACCOUNT_BASE_URL + "/permission/apply");
			if (apiResult.getCode() == PromptMsg.SUCCESS.getCode()) {
				result.setCode(PromptMsg.SUCCESS.getCode());
				result.setData(apiResult.getData());
			} else {
				result.setCode(PromptMsg.EDIT_FAILED.getCode());
				result.setMsg(PromptMsg.EDIT_FAILED.getMsg());
			}
		}
		
		return result;
	}
	
}
