package com.nexii.SpringBootFirstExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootFirstExampleApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootFirstExampleApplication.class, args);

		SpringBootService sbs=context.getBean("springBootService",SpringBootService.class);
		
		System.out.println(sbs.sayHello("surya"));
		System.out.println(context.getBeanDefinitionCount());
		String[] names=context.getBeanDefinitionNames();
		for(String name:names) {
			System.out.println(name);
		}
	}
}
