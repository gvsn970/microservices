package com.nexiilabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class Gatewaycontroller {
	@Autowired
	GateWayService gateWayService;
	@RequestMapping(value="/getUser", method=RequestMethod.GET)
	public String getUser() {
		return gateWayService.getUser();
	}

}
