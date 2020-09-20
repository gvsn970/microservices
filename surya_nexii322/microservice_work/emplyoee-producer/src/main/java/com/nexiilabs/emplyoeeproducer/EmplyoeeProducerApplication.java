package com.nexiilabs.emplyoeeproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmplyoeeProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmplyoeeProducerApplication.class, args);
	}
}
