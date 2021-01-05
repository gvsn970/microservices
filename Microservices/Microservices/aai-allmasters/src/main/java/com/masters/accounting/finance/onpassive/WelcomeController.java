package com.masters.accounting.finance.onpassive;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeController {
	
	
	private HttpServletRequest request;
	
	
	@Autowired
	Environment environment;
	
	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String showWelcome(@RequestParam String uname) {
		
		logger.warn("From Welcome Controller");
		logger.info("From Welcome Controller");
		logger.debug("From Welcome Controller");
		logger.trace("From Welcome Controller");
		logger.error("From Welcome Controller");
		
		return environment.getProperty("welcome.message")
				+" >>> "+environment.getProperty("ymlwelcome.message")
				+" >>> "+environment.getProperty("bootmessage");
		
	
	
	}

}
