package com.nexiilabs.emplyoeeproducer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfiguration {
	
	@Bean
	public EmployeeService employeeservice() {
		return new EmployeeService();
	}

}
