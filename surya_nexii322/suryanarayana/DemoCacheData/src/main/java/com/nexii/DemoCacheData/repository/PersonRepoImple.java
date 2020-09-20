package com.nexii.DemoCacheData.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.nexii.DemoCacheData.bean.Person;


@Component
public class PersonRepoImple implements DemoRepository {

	@Override
	@Cacheable("person")
	public Person getDetailes(String name) {
		delay();
		return new Person(name, "suryanarayana");
	}

	public void delay() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
