package com.eshop.api.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.api.product.dao.CategoryDao;
import com.eshop.api.product.domain.Category;
import com.eshop.api.product.dto.CategoryDto;
import com.eshop.api.product.service.CategoryService;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<CategoryDto, Category> implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Category convertToDomain(CategoryDto t) {
		if (null == t) {
			return null;
		}
		Category domain = new Category();
		if (StringUtils.isEmpty(t.getCid())) {
			domain.setCid(UUID.randomUUID().toString());
		}
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
		dto.setType(d.getType());
		dto.setLevel(d.getLevel());
		return dto;
	}

	@Override
	public CategoryDto getCategoryById(String categoryId) {
		return this.convertToDto(categoryDao.getByCategoryId(categoryId));
	}

	@Override
	public List<CategoryDto> getChildrenByParentId(String pid) {
		List<CategoryDto> result = new ArrayList<>();
		List<Category> domains = categoryDao.getChildrenByParentId(pid);
		domains.forEach(domain -> result.add(this.convertToDto(domain)));
		return result;
	}

}
