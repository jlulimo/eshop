package com.eshop.api.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.eshop.api.product.constants.PromptMsg;
import com.eshop.api.product.dto.CategoryDto;
import com.eshop.api.product.model.CategoryNode;
import com.eshop.api.product.model.ResultModel;
import com.eshop.api.product.service.CategoryService;

@Controller
@RequestMapping(value = "category")
public class CategoryController extends BaseController<CategoryNode, CategoryDto> {
	@Autowired
	private CategoryService categoryService;

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "browse", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel browse(@RequestParam(value = "pid") String pid) {
		List<CategoryNode> models = new ArrayList<>();
		List<CategoryDto> children = categoryService.getChildrenByParentId(pid);
		children.forEach(dto -> models.add(this.convertToModel(dto)));
		ResultModel resultModel = new ResultModel();
		resultModel.setCode(PromptMsg.SUCCESS.getCode());
		resultModel.setData(models);
		return resultModel;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "add", method = RequestMethod.POST)
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
			String categoryId = categoryService.addCategory(this.convertToDto(categoryNode));
			resultModel.setCode(PromptMsg.SUCCESS.getCode());
			resultModel.setData(categoryId);
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
		} else {
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
			// resultModel = HttpUtil.exectRequest(resultModel.getClass(),
			// categoryNode, "/delCategory");
		}
		return resultModel;
	}

	@Override
	public CategoryDto convertToDto(CategoryNode d) {
		if (null == d) {
			return null;
		}
		CategoryDto dto = new CategoryDto();
		dto.setName(d.getText());
		dto.setParentId(d.getParent());
		dto.setType(d.getType());
		dto.setLevel(d.getLevel());
		return dto;
	}

	@Override
	public CategoryNode convertToModel(CategoryDto t) {
		CategoryNode model = new CategoryNode();
		model.setId(t.getCid());
		model.setType(t.getType());
		model.setText(t.getName());
		model.setParent(t.getParentId());
		model.setType(t.getType());
		model.setLevel(t.getLevel());
		model.setChildren(true);
		return model;
	}

}
