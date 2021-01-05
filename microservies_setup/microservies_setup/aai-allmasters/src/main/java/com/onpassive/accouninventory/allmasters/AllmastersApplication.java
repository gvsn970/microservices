package com.onpassive.accouninventory.allmasters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableDiscoveryClient
public class AllmastersApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllmastersApplication.class, args);
	}

}
