package com.nexii.springbootNew.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController implements ErrorController {

	@RequestMapping("/")
	public String welcome() {
		return "welcome to  spring...!!";
	}

	private static final String PATH = "/error";

	@Override
	public String getErrorPath() {

		return PATH;
	}

	@RequestMapping(PATH)
	public String error() {
		return "no mapping available..!";
	}
}
