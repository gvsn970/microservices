package com.snkit.springcache;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class CacheService {
	
	
	private ConcurrentHashMap cMap = new ConcurrentHashMap();
	
	
	
	@PostConstruct
	public void getCities() {
		cMap.put("HYd", "Hyderabad");
		
		cMap.put("Bang", "Bangalore");
		
		System.out.println("   getCities  ");
		

		
	}

	
	public ConcurrentHashMap getCityDetails() {
		
		System.out.println("   getCityDetails  ");
		return this.cMap;
	}
	
	
	
}
