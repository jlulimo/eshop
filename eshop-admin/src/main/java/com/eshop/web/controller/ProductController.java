package com.eshop.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.eshop.web.constants.PromptMsg;
import com.eshop.web.model.CategoryNode;
import com.eshop.web.model.ResultModel;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;

import groovyjarjarasm.asm.tree.MultiANewArrayInsnNode;

@Controller
@RequestMapping(value = "product")
public class ProductController {
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
	public ResultModel addCategory(@RequestParam("text") String text, @RequestParam("parentId") String parentId) {
		CategoryNode addNode = new CategoryNode();
		addNode.setId(UUID.randomUUID().toString());
		addNode.setParentId(parentId);
		addNode.setText(text);
		addNode.setType("category");
		// add note
		ResultModel resultModel = new ResultModel();
		resultModel.setCode(PromptMsg.SUCCESS.getCode());
		resultModel.setData(addNode.getId());
		resultModel.setMsg("添加成功");
		return resultModel;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "renameCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel renameCategory(@RequestParam("nodeId") String nodeId, @RequestParam("text") String text) {
		System.out.println(nodeId + " , " + text);
		ResultModel resultModel = new ResultModel();
		resultModel.setCode(PromptMsg.SUCCESS.getCode());
		resultModel.setMsg("修改成功");
		return resultModel;
	}

	@RequestMapping(value = "layout", method = RequestMethod.GET)
	public String layout() {
		return "/layouts/layout";
	}
}
