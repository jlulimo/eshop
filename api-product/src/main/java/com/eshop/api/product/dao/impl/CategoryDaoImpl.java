package com.eshop.api.product.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eshop.api.product.dao.CategoryDao;
import com.eshop.api.product.domain.Category;
import com.eshop.api.product.mapper.CategoryMapper;

@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	public void setBaseMapper() {
		super.setMapper(this.categoryMapper);
	}

	public void setCategoryMapper(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}

	public int count() {
		return categoryMapper.count();
	}

	@Override
	public Category getCategoryByName(String categoryName) {
		return categoryMapper.getCategoryByName(categoryName);
	}

	@Override
	public Category getByCategoryId(String categoryId) {
		return categoryMapper.getByCategoryId(categoryId);
	}

	@Override
	public List<Category> getChildrenByParentId(String pid) {
		return categoryMapper.getChildrenByParentId(pid);
	}

	@Override
	public void deleteByCategoryId(String cId) {
		 categoryMapper.deleteByCategoryId(cId);		
	}

}
