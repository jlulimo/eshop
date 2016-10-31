package com.nnfs.api.jms.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReponseQueueListener {
	private static final Logger logger = LoggerFactory.getLogger(ReponseQueueListener.class);

	public <T extends Serializable> void receive(T object) {
		logger.debug(String.format("receive response: %s", object));
		//todo. add some logic after confirm msg . 
		
	}
}
