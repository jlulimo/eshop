package com.eshop.api.product.dao;

import java.util.List;

import com.eshop.api.product.domain.Category;

public interface CategoryDao extends GenericDao<Category> {
	 int count();

	 Category getCategoryByName(String cName);

	 Category getByCategoryId(String cid);

	 List<Category> getChildrenByParentId(String pid);
	 
	 void deleteByCategoryId(String cId);
}
