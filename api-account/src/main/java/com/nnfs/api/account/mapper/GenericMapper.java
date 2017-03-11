package com.nnfs.api.account.mapper;

import java.util.List;

public interface GenericMapper<T> {

	public void create(T t);

	public T get(String id);

	public void update(T t);

	public void delete(String id);

	public List<T> getAll();
}
