package com.nexii.SpringBootFirstExample;

import org.springframework.stereotype.Service;

@Service("springBootService")

public class SpringBootService {

	public String sayHello(String name) {

		return "welcome To Spring Boot"+name;

	}

}
