package com.eshop.api.product.dao;

import java.util.List;

import com.eshop.api.product.domain.Category;

public interface CategoryDao extends GenericDao<Category> {
	public int count();

	public Category getCategoryByName(String cName);

	public Category getByCategoryId(String cid);

	public List<Category> getChildrenByParentId(String pid);
}
