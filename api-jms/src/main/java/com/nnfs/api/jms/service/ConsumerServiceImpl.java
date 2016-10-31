package com.nnfs.api.jms.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsumerServiceImpl implements IConsumerService {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);

	// @JmsListener(destination = "m_queue")
	@Override
	public <T extends Serializable> T receiveFromQueue(T object) {
		logger.debug(String.format("receive msg [%s]", object));
		return object;
	}

	// @JmsListener(destination = "m_topic")
	@Override
	public <T extends Serializable> T receiveFromTopic(T object) {
		logger.debug(String.format("receive msg [%s]", object));
		return object;
	}

}
