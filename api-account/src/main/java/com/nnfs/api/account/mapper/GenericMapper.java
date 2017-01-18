package com.nnfs.api.account.mapper;

import java.util.List;

public interface GenericMapper<T> {

	public void create(T t);

	public T get(long id);

	public void update(T t);

	public void delete(long id);

	public List<T> getAll();
}
