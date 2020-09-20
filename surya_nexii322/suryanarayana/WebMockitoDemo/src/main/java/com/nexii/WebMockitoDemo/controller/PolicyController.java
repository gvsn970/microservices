package com.nexii.WebMockitoDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexii.WebMockitoDemo.service.PolicyService;

@RestController
public class PolicyController {

	@Autowired
	private PolicyService policyService;
	
	public static final String URl="/policy/{policyNumber}";
	
	@RequestMapping(URl)
	public String getPolicy(@PathVariable final int policyNumber) {
		return policyService.getPolicy(policyNumber);
	}
}
