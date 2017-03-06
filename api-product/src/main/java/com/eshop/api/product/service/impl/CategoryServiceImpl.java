package com.eshop.api.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.api.product.dao.CategoryDao;
import com.eshop.api.product.domain.Category;
import com.eshop.api.product.dto.CategoryDto;
import com.eshop.api.product.service.CategoryService;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<CategoryDto, Category> implements CategoryService {

	private static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Category convertToDomain(CategoryDto t) {
		if (null == t) {
			return null;
		}
		Category domain = new Category();
		if (StringUtils.isEmpty(t.getId())) {
			domain.setCid(UUID.randomUUID().toString());
		}else{
			domain.setCid(t.getId());
		}
		domain.setType(t.getType());
		domain.setNo(t.getNo());
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
		dto.setId(d.getCid());
		dto.setNo(d.getNo());
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

	@Override
	public CategoryDto getCategoryByName(String name) {
		return this.convertToDto(categoryDao.getCategoryByName(name));
	}

	@Override
	public String addCategory(CategoryDto categoryDto) {
		CategoryDto existed = this.getCategoryByName(categoryDto.getName());
		if (null != existed) {
			logger.warn("category: {%s} existed", categoryDto.getName());
			return null;
		}
		try {
			this.add(categoryDto);
			return categoryDto.getId();
		} catch (Exception e) {
			logger.warn("add category failed", e);
			return null;
		}
	}

	@Override
	public String editCategory(CategoryDto categoryDto) {
		CategoryDto existed = this.getCategoryByName(categoryDto.getName());
		if (null != existed) {
			logger.warn("category: {%s} existed", categoryDto.getName());
			return null;
		}
		try {
			this.update(categoryDto);
			return categoryDto.getId();
		} catch (Exception e) {
			logger.warn("edit category failed", e);
			return null;
		}
	}

}
