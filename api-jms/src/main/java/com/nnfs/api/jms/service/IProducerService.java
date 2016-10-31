package com.nnfs.api.jms.service;

import java.io.Serializable;

import javax.jms.Destination;

public interface IProducerService {
	public <T extends Serializable> void sendMsgToQueue(Destination destination, T object);

	public <T extends Serializable> void sendMsgToTopic(Destination destination, T object);
}
