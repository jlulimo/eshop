package com.eshop.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

import com.alibaba.fastjson.JSON;
import com.eshop.web.constants.PromptMsg;
import com.eshop.web.model.CategoryNode;
import com.eshop.web.model.ResultModel;
import com.eshop.web.util.HttpUtil;

import okhttp3.Request;
import okhttp3.Response;

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
		}
		if ("root".equalsIgnoreCase(nodeId)) {
			CategoryNode tree = this.assembleTree();
			result.setCode(PromptMsg.SUCCESS.getCode());
			result.setData(tree);
			return result;
		}
		return result;
	}

	private CategoryNode assembleTree() {
		CategoryNode rootNode = new CategoryNode();
		rootNode.setId("root");
		rootNode.setText("商品类别管理");
		rootNode.setType("root");
		// todo getchildren..
		CategoryNode c1 = new CategoryNode();
		c1.setId("c1");
		c1.setText("类别1");
		c1.setType("category");
		CategoryNode c2 = new CategoryNode();
		c2.setId("c2");
		c2.setText("类别2");
		c2.setType("category");
		List<CategoryNode> children = new ArrayList<>();
		children.add(c1);
		children.add(c2);
		rootNode.setChildren(children);
		return rootNode;
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
		} else if (StringUtils.isEmpty(categoryNode.getParentId())) {
			resultModel.setCode(PromptMsg.ADD_FAILED.getCode());
			resultModel.setMsg("父节点ID为空");
		} else {
			categoryNode.setId(UUID.randomUUID().toString());
			categoryNode.setType("category");
			resultModel = HttpUtil.exectRequest(resultModel.getClass(), categoryNode, "/addCategory");
		}
		return resultModel;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "renameCategory", method = RequestMethod.POST)
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
		} else {
			 resultModel = HttpUtil.exectRequest(resultModel.getClass(), categoryNode, "/renameCategory");
		}
		return resultModel;
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "deleteCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel delCategoryNode(@RequestBody CategoryNode categoryNode){
		ResultModel resultModel = new ResultModel();
		if (null == categoryNode) {
			resultModel.setCode(PromptMsg.DEL_FAILED.getCode());
			resultModel.setMsg(PromptMsg.DEL_FAILED.getMsg());
		} else if (StringUtils.isEmpty(categoryNode.getId())) {
			resultModel.setCode(PromptMsg.DEL_FAILED.getCode());
			resultModel.setMsg("节点ID为空");
		} else {
			resultModel = HttpUtil.exectRequest(resultModel.getClass(), categoryNode, "/delCategory");
		}
		return resultModel;
	}
	
}
