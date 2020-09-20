package com.nexiilabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@SpringBootApplication
@EnableZuulProxy
@EnableHystrixDashboard
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableEurekaClient
@RestController
public class EmplyoeeGatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmplyoeeGatewayserverApplication.class, args);
	}
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	@Bean(name="hystrixRegistrationBean")
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean registration=new ServletRegistrationBean(
		new HystrixMetricsStreamServlet() ,"/hystrix.stream");
		registration.setName("hystrix servlet");
		registration.setLoadOnStartup(1);
		return registration;
		
	}
}
