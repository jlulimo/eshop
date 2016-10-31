package com.nnfs.api.jms.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {
	private static final Logger logger = LoggerFactory.getLogger(ConsumerListener.class);

	public <T extends Serializable> T receive(T object) {
		logger.debug(String.format("receive msg: %s", object));
		return object;
	}
}
