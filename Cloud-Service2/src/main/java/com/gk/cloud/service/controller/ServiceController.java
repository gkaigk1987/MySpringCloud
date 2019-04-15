package com.gk.cloud.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * @author gk
 * 2019年3月15日 下午6:33:14
 */
@RestController
public class ServiceController {
	
	private static Logger logger = LoggerFactory.getLogger(ServiceController.class);
	
	@Value("${server.port}")
	private String serverPort;

	/**
	 * 
	 * @param msg
	 * @return
	 */
	@GetMapping("/helloCloud/{msg}")
	public String helloCloud(@PathVariable String msg) {
		logger.info("Request Message is {}",msg);
		//以下是测试重试的代码
//		try {
//			Thread.sleep(50000);
//		} catch (Exception e) {
//			logger.error("Time out error,{}",e);
//		}
		return String.format("Hello Spring Cloud, your message is %s from port: %s", msg, serverPort);
	}
	
}
