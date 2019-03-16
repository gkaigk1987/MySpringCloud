package com.gk.cloud.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class AdminService {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "helloError")	//添加hystrix熔断
	public String helloCloud(String msg) {
		return restTemplate.getForObject("http://CLOUD-SERVICE/helloCloud/" + msg, String.class);
	}
	
	/**
	 * 熔断后返回的内容调用方法
	 * @param msg
	 * @return
	 */
	public String helloError(String msg) {
		return String.format("Your message is: %s, but request error!", msg);
	}
}
