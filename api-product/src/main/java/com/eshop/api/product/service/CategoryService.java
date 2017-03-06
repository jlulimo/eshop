package com.eshop.api.product.service;

import java.util.List;

import com.eshop.api.product.dto.CategoryDto;

public interface CategoryService extends GenericService<CategoryDto> {
	CategoryDto getCategoryById(String categoryId);

	List<CategoryDto> getChildrenByParentId(String pid);

	CategoryDto getCategoryByName(String name);

	String addCategory(CategoryDto categoryDto);
	
	String editCategory(CategoryDto categoryDto);
	
	void deleteCategoryById(String cId);
}
