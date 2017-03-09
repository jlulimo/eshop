package com.nnfs.api.account.controller;

public abstract class BaseController<M, T> {
	public abstract T convertToDto(M d);

	public abstract M convertToModel(T t);
}
