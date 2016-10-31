package com.nnfs.api.jms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nnfs.api.jms.App;
import com.nnfs.api.jms.model.OrderMsgModel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class ProducerServiceImplTest {

	@Autowired
	private ProducerServiceImpl producerServiceImpl;

	@Test
	public void sendMsgTest() {
		OrderMsgModel orderMsgModel = new OrderMsgModel();
		orderMsgModel.setId(001);
		orderMsgModel.setOrderNo(123);
		orderMsgModel.setStatus(1);
		this.producerServiceImpl.send(orderMsgModel);
	}

}
