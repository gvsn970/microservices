package com.nexii.restwebService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nexii.restwebService.service.DemoService;

@RestController
public class DemoRestController {

	@Autowired
	private DemoService demoService;
	@RequestMapping(value="/getdata",method=RequestMethod.GET)
	public DemoService getData() {
		demoService.setId(1);
		demoService.setName("surya");
		demoService.setAge(26);
		demoService.setHeight(168);
		return demoService;
	}
	
}
