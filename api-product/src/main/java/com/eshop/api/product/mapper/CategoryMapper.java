package com.eshop.api.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.eshop.api.product.domain.Category;

@Mapper
public interface CategoryMapper extends GenericMapper<Category> {
	int count();

	Category getCategoryByName(String cName);

	Category getByCategoryId(String cId);

	List<Category> getChildrenByParentId(String pid);

	void deleteByCategoryId(String cId);
}
