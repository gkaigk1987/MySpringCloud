package com.gk.cloud.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gk.cloud.feign.hystrix.AdminServiceHystrix;

@FeignClient(value = "cloud-service",fallback = AdminServiceHystrix.class)
public interface AdminService {

	@GetMapping("/helloCloud/{msg}")
	public String helloCloud(@PathVariable(value="msg") String msg);
	
}
