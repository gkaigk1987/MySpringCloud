package com.gk.cloud.service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {
	
	@Value("${server.port}")
	private String serverPort;

	/**
	 * 
	 * @param msg
	 * @return
	 */
	@GetMapping("/helloCloud/{msg}")
	public String helloCloud(@PathVariable String msg) {
		return String.format("Hello Spring Cloud, your message is %s from port: %s", msg, serverPort);
	}
	
}
