package com.gk.cloud.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gk.cloud.ribbon.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping(value = "/helloCloud/{msg}")
	public String helloCloud(@PathVariable String msg) {
		return adminService.helloCloud(msg);
	}

}
