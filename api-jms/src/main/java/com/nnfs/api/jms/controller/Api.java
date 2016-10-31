package com.nnfs.api.jms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Api {
	
	private static final Logger logger = LoggerFactory.getLogger(Api.class);
	
	@ResponseBody
	@RequestMapping("/msg")
	public void sendMsg(){
		logger.debug("[debug] log level..");
		logger.info("[info] log level...");
		logger.warn("[warn] log level...");
	}
}
