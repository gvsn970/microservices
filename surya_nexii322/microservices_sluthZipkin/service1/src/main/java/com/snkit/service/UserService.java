package com.snkit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getUserCallback" )
	public String getUser() {

		ResponseEntity<String> resp = restTemplate.exchange("http://custaddress-service/getUsers", HttpMethod.GET, null,
				String.class);

		return resp.getBody();

	}

/*	public String getUserCallback(Throwable t) {

		System.out.println("  from @HystrixCommand   getUserCallback =============================");

		String str = " Requested upstream is down";

		return str;

	}*/
	
	public String getUserCallback() {

		System.out.println("  from @HystrixCommand   getUserCallback =============================");

		String str = " Requested upstream is down";

		return str;

	}

}
