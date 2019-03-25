package com.gk.cloud.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gk.cloud.feign.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/helloCloud/{message}")
	public String helloCloud(@PathVariable(value="message") String message) {
		return adminService.helloCloud(message);
	}
	
	@PostMapping("/jsonReceive")
	public String jsonReceive(@RequestBody String params) {
		return adminService.jsonReceive(params);
	}
}
