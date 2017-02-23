package com.eshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="product")
public class ProductController {
	@RequestMapping(value = "category", method = RequestMethod.GET)
	public String category() {
		return "/product/category";
	}
	
	@RequestMapping(value = "categoryTree", method = RequestMethod.GET)
	public String categoryTree() {
		return "/product/categoryTree";
	}
	
	@RequestMapping(value = "layout", method = RequestMethod.GET)
	public String layout() {
		return "/layouts/layout";
	}
}
