package com.eshop.web.model;

import java.util.List;

public class CategoryNode {
	private String id;
	private String text;
	private List<CategoryNode> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<CategoryNode> getChildren() {
		return children;
	}
	public void setChildren(List<CategoryNode> children) {
		this.children = children;
	}
	
	
}
