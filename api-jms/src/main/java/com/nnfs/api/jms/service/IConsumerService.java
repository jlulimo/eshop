package com.nnfs.api.jms.service;

import java.io.Serializable;

public interface IConsumerService {
	public <T extends Serializable> T receiveFromQueue(T object);

	public <T extends Serializable> T receiveFromTopic(T object);
}
