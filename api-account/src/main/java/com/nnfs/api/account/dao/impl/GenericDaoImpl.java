package com.nnfs.api.account.dao.impl;

import java.util.List;

import com.nnfs.api.account.dao.GenericDao;
import com.nnfs.api.account.mapper.GenericMapper;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
	private GenericMapper<T> mapper;

	public GenericMapper<T> getMapper() {
		return mapper;
	}

	public void setMapper(GenericMapper<T> mapper) {
		this.mapper = mapper;
	}

	@Override
	public void create(T t) {
		mapper.create(t);
	}

	@Override
	public T get(long id) {
		return mapper.get(id);
	}

	@Override
	public T get(String id) {
		return mapper.get(id);
	}

	@Override
	public void update(T t) {
		mapper.update(t);
	}

	@Override
	public void delete(long id) {
		mapper.delete(id);
	}

	@Override
	public void delete(String id) {
		mapper.delete(id);
	}

	@Override
	public List<T> getAll() {
		return mapper.getAll();
	}

}
