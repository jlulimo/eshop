package com.eshop.api.product.service;

import com.eshop.api.product.dto.CategoryDto;

public interface CategoryService extends GenericService<CategoryDto> {
	public CategoryDto getCategoryById(String categoryId);
}
