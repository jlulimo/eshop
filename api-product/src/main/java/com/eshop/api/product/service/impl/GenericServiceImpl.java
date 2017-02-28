package com.eshop.api.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.eshop.api.product.dao.GenericDao;
import com.eshop.api.product.service.GenericService;

public abstract class  GenericServiceImpl<T,D> implements GenericService<T> {

	@Autowired
	private GenericDao<D> genericDao;
	
	@Override
	public void add(T t) {
		this.genericDao.create(this.convertToDomain(t));
	}

	@Override
	public void deleteById(long id) {
		this.genericDao.delete(id);
	}

	@Override
	public void update(T t) {
		this.genericDao.update(this.convertToDomain(t));
	}

	@Override
	public T getById(long id) {
		D domain = this.genericDao.get(id);
		return this.convertToDto(domain);
	}

	@Override
	public List<T> getAll() {
		List<D> domains = this.genericDao.getAll();
		List<T> result = new ArrayList<T>();
		domains.forEach(domain -> result.add(this.convertToDto(domain)));
		return result;
	}
	
	public abstract D convertToDomain(T t);
	
	public abstract T convertToDto(D d);

}
