package com.nexii.Demoscheduler.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Demoscheduler {
	
	@Scheduled(fixedRate=3000)
	public void task() {
		System.out.println("***********Schedule Task**************");
	}

}
