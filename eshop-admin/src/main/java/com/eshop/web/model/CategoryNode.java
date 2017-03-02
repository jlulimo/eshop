package com.eshop.web.model;

import java.util.List;

public class CategoryNode {
	private String id;
	private String text;
	private List<CategoryNode> children;
	private String type;
	private int level;
	private String parentId;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
