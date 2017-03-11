package com.nnfs.api.account.service;

import java.util.List;

public interface GenericService<T> {
	public void add(T t);
	public void deleteById(String id);
	public void update(T t);
	public T getById(String id);
	public List<T> getAll();
}
