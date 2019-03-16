package com.gk.cloud.ribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
	
	@Bean
	@LoadBalanced	//设置负载均衡
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
 