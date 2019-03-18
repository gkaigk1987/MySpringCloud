package com.gk.cloud.admin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * admin client 测试
 * @author gk
 * 2019年3月18日 下午1:02:33
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AdminClientApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AdminClientApplication.class, args);
	}
}
