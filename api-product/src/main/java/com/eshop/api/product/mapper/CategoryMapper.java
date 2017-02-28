package com.eshop.api.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.eshop.api.product.domain.Category;

@Mapper
public interface CategoryMapper extends GenericMapper<Category> {
	public int count();

	public Category getCategoryByName(String cName);

	public Category getByCategoryId(String cId);
}
