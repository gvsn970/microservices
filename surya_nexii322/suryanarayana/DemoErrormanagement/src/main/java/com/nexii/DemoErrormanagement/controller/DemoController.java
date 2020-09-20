package com.nexii.DemoErrormanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	@RequestMapping("/test")
	public void  demoTest() {
		throw new RuntimeException("demo test Expetions");
	}
}
