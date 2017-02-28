package com.eshop.api.product.controller;

import org.springframework.stereotype.Controller;

@Controller
public abstract class BaseController<M, T> {
	public abstract T convertToDto(M d);

	public abstract M convertToModel(T t);
}
