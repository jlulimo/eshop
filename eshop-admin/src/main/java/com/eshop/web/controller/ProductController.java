package com.eshop.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson.JSON;
import com.eshop.web.constants.PromptMsg;
import com.eshop.web.model.CategoryNode;
import com.eshop.web.model.ResultModel;

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
	public ResultModel browseTree(@RequestParam(value = "nodeId") String nodeId) {
		ResultModel result = new ResultModel();
		if (StringUtils.isEmpty(nodeId)) {
			result.setCode(PromptMsg.QUERY_FAILED.getCode());
			result.setMsg("节点id为空");
			return result;
		}
		String testJson = "[{\"id\":1,\"text\":\"Root node\",\"children\":[{\"id\":2,\"text\":\"Child node 1\"},{\"id\":3,\"text\":\"Child node 2\"}]}]";
		CategoryNode categoryNode = JSON.parseObject(testJson, CategoryNode.class);
		result.setCode(PromptMsg.SUCCESS.getCode());
		result.setData(categoryNode);
		return result;
	}

	@RequestMapping(value = "layout", method = RequestMethod.GET)
	public String layout() {
		return "/layouts/layout";
	}
}
