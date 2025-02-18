package com.nexiilabs.emplyoeeproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ConsumerService {
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getUserCallback")
	public String getUser() {
		ResponseEntity<String> resp = restTemplate.exchange("http://emplyoee-producer/listEmp", HttpMethod.GET, null,
				String.class);
		return resp.getBody();
	}

	public String getUserCallback(Throwable t) {
		String str = "from getUserCallback ";
		return str;
	}
}
