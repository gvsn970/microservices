package com.snkit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ServiceController {
	
	@Autowired
	private UserService UserService;
	
	@GetMapping("/getUser")
	public String getUser() {
		
		System.out.println("  From hellowrold ");
		
	
		
		return UserService.getUser();
	}

}
