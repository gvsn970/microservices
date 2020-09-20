package com.snkit.springconfigservercloudbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringconfigservercloudbusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringconfigservercloudbusApplication.class, args);
	}
}
