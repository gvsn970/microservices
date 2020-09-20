package com.nexiilab.springbootexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringRestController {

	@GetMapping("/")
	public String hello() {
		return "Hello World";
	}

}
