package com.nexiilabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
@EnableHystrixDashboard
public class EmplyoeeTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmplyoeeTurbineApplication.class, args);
	}
}
