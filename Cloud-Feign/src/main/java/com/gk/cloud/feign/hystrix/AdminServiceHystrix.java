package com.gk.cloud.feign.hystrix;

import org.springframework.stereotype.Component;

import com.gk.cloud.feign.service.AdminService;

/**
 * 为AdminService实现熔断方法
 * @author gaokai
 *
 */
@Component
public class AdminServiceHystrix implements AdminService {

	@Override
	public String helloCloud(String msg) {
		return "your message is " + msg + ", but request error.";
	}

	@Override
	public String jsonReceive(String params) {
		return null;
	}

}
