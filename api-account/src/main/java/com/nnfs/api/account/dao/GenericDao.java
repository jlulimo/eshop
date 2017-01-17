package com.nnfs.api.account.dao;

import java.util.List;

public interface GenericDao<T> {
	public void create(T t);

	public T get(long id);

	public T get(String id);

	public void update(T t);

	public void delete(long id);

	public void delete(String id);

	public List<T> getAll();

}
