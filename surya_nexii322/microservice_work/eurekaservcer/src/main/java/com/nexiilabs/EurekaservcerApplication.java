package com.nexiilabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaservcerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaservcerApplication.class, args);
	}
}
