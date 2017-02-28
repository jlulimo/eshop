package com.eshop.api.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.eshop.api.product.dao.CategoryDao;
import com.eshop.api.product.domain.Category;
import com.eshop.api.product.dto.CategoryDto;
import com.eshop.api.product.service.CategoryService;

public class CategoryServiceImpl extends GenericServiceImpl<CategoryDto, Category> implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Category convertToDomain(CategoryDto t) {
		if (null == t) {
			return null;
		}
		Category domain = new Category();
		domain.setCid(t.getCid());
		domain.setCno(t.getcNo());
		domain.setName(t.getName());
		domain.setParentId(t.getParentId());
		return domain;
	}

	@Override
	public CategoryDto convertToDto(Category d) {
		if (null == d) {
			return null;
		}
		CategoryDto dto = new CategoryDto();
		dto.setCid(d.getCid());
		dto.setcNo(d.getCno());
		dto.setId(d.getId());
		dto.setName(d.getName());
		dto.setParentId(d.getParentId());
		return dto;
	}

	@Override
	public CategoryDto getCategoryById(String categoryId) {
		return this.convertToDto(categoryDao.getByCategoryId(categoryId));
	}

}
