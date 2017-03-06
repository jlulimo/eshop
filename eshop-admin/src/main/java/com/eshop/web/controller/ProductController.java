package com.eshop.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
import com.eshop.web.model.CategoryNode;
import com.eshop.web.model.ResultModel;
import com.eshop.web.util.HttpUtil;

@Controller
@RequestMapping(value = "product")
public class ProductController {

	@RequestMapping(value = "layout", method = RequestMethod.GET)
	public String layout() {
		return "/layouts/layout";
	}

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {
		return "/product/test";
	}

	@RequestMapping(value = "category", method = RequestMethod.GET)
	public String category() {
		return "/product/category";
	}

	@RequestMapping(value = "categoryTree", method = RequestMethod.GET)
	public String categoryTree() {
		return "/product/categoryTree";
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
			CategoryNode root = new CategoryNode();
			root.setChildren(true);
			root.setId("root");
			root.setParent("#");
			root.setLevel(-1);
			root.setText("产品类别管理");
			root.setType("root");
			result.setCode(PromptMsg.SUCCESS.getCode());
			result.setData(root);
		} else {
			Map<String, String> parm = new HashMap<>();
			parm.put("pid", nodeId);
			ResultModel apiResult = HttpUtil.exectGet(ResultModel.class, "/category/browse", parm);
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
	@RequestMapping(value = "addCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel addCategory(@RequestBody CategoryNode categoryNode) {
		ResultModel resultModel = new ResultModel();
		if (null == categoryNode) {
			resultModel.setCode(PromptMsg.ADD_FAILED.getCode());
			resultModel.setMsg(PromptMsg.ADD_FAILED.getMsg());
		} else if (StringUtils.isEmpty(categoryNode.getText())) {
			resultModel.setCode(PromptMsg.ADD_FAILED.getCode());
			resultModel.setMsg("节点名字为空");
		} else if (StringUtils.isEmpty(categoryNode.getParent())) {
			resultModel.setCode(PromptMsg.ADD_FAILED.getCode());
			resultModel.setMsg("父节点ID为空");
		} else {
			categoryNode.setId(UUID.randomUUID().toString());
			// categoryNode.setType("category");
			resultModel = HttpUtil.exectPost(resultModel.getClass(), categoryNode, "/category/add");
		}
		return resultModel;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel renameCategory(@RequestBody CategoryNode categoryNode) {
		ResultModel resultModel = new ResultModel();
		if (null == categoryNode) {
			resultModel.setCode(PromptMsg.EDIT_FAILED.getCode());
			resultModel.setMsg(PromptMsg.EDIT_FAILED.getMsg());
		} else if (StringUtils.isEmpty(categoryNode.getText())) {
			resultModel.setCode(PromptMsg.EDIT_FAILED.getCode());
			resultModel.setMsg("节点名字为空");
		} else if (StringUtils.isEmpty(categoryNode.getId())) {
			resultModel.setCode(PromptMsg.EDIT_FAILED.getCode());
			resultModel.setMsg("节点ID为空");
		} else if (StringUtils.isEmpty(categoryNode.getParent())) {
			resultModel.setCode(PromptMsg.EDIT_FAILED.getCode());
			resultModel.setMsg("父节点ID为空");
		} else {
			resultModel = HttpUtil.exectPost(resultModel.getClass(), categoryNode, "/category/edit");
		}
		return resultModel;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel delCategoryNode(@RequestBody CategoryNode categoryNode) {
		ResultModel resultModel = new ResultModel();
		if (null == categoryNode) {
			resultModel.setCode(PromptMsg.DEL_FAILED.getCode());
			resultModel.setMsg(PromptMsg.DEL_FAILED.getMsg());
		} else if (StringUtils.isEmpty(categoryNode.getId())) {
			resultModel.setCode(PromptMsg.DEL_FAILED.getCode());
			resultModel.setMsg("节点ID为空");
		} else {
			resultModel = HttpUtil.exectPost(resultModel.getClass(), categoryNode, "/category/delete");
		}
		return resultModel;
	}

}
