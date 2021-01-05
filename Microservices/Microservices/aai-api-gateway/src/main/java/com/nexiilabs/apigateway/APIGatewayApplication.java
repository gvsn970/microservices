package com.nexiilabs.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;;

@SpringBootApplication
@EnableZuulProxy
@EnableHystrixDashboard
@EnableCircuitBreaker
@RestController
public class APIGatewayApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(APIGatewayApplication.class, args);
	}
	
	@Bean
	public AlwaysSampler deafaultSampler() {
		return new AlwaysSampler();
	} 
	
	@LoadBalanced
	@Bean 
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	

	@Bean(name="hystrixRegistrationBean")
	public ServletRegistrationBean hystrixDashboardServletRegistration(){
		ServletRegistrationBean servletRegistrationBean =
				new ServletRegistrationBean(new HystrixMetricsStreamServlet(), "/hystrix.stream");
		servletRegistrationBean.setName("hystrixServlet");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}
	
	
	
	
}	
	
