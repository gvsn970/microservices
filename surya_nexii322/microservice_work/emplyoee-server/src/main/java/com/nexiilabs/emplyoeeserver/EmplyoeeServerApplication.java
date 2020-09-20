package com.nexiilabs.emplyoeeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EmplyoeeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmplyoeeServerApplication.class, args);
	}
}
