package com.nnfs.api.jms.util;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageConverter implements org.springframework.jms.support.converter.MessageConverter {

	private static final Logger logger = LoggerFactory.getLogger(OrderMessageConverter.class);

	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		logger.debug(String.format("convert object[%s] to message: ", object.getClass()));
		return session.createObjectMessage((java.io.Serializable) object);
	}

	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		ObjectMessage objectMessage = (ObjectMessage) message;
		Object object = objectMessage.getObject();
		logger.debug(String.format("convert message[%s] to object: ", object.getClass()));
		return object;
	}

}
