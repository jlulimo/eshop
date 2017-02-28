package com.eshop.api.product.controller;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.eshop.api.product.constants.PromptMsg;
import com.eshop.api.product.dto.CategoryDto;
import com.eshop.api.product.model.CategoryNode;
import com.eshop.api.product.model.ResultModel;
import com.eshop.api.product.service.CategoryService;

public class ProductController extends BaseController<CategoryNode,CategoryDto> {
	
	@Autowired
	private CategoryService categoryService;
	
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
//			categoryService.add(categoryNode);
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
		}
		return resultModel;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "deleteCategory", method = RequestMethod.POST)
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
			// resultModel = HttpUtil.exectRequest(resultModel.getClass(),
			// categoryNode, "/delCategory");
		}
		return resultModel;
	}

	@Override
	public CategoryDto convertToDto(CategoryNode d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryNode convertToModel(CategoryDto t) {
		// TODO Auto-generated method stub
		return null;
	}

}
