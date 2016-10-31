package com.nnfs.api.jms.service;

import java.io.Serializable;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ProducerServiceImpl implements IProducerService {

	private static final Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);
	@Autowired
	private JmsTemplate jmsQueueTemplate;

	@Autowired
	private JmsTemplate jmsTopicTemplate;

	@Autowired
	private Destination queueDestination;

	@Autowired
	private Destination topicDestination;

	// @Scheduled(fixedDelay = 30000)
	public <T extends Serializable> void send(T object) {
		this.sendMsgToTopic(this.topicDestination, object);
		this.sendMsgToQueue(this.queueDestination, object);
	}

	@Override
	public <T extends Serializable> void sendMsgToQueue(Destination destination, T object) {
		this.jmsQueueTemplate.convertAndSend(destination, object);
		logger.debug(String.format("send msg to queue %s", object));
	}

	@Override
	public <T extends Serializable> void sendMsgToTopic(Destination destination, T object) {
		this.jmsTopicTemplate.convertAndSend(destination, object);
		logger.debug(String.format("send msg to topic %s", object));
	}

}
