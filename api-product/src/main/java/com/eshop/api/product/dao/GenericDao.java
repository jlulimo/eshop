package com.eshop.api.product.dao;

import java.util.List;

public interface GenericDao<T> {
	public void create(T t);

	public T get(long id);

	public void update(T t);

	public void delete(long id);

	public List<T> getAll();

}
