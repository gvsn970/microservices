package com.snkit.springcache;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {
	
	
	@Autowired
	private CacheService cacheService;
	
	@GetMapping(value="cities")
	public ConcurrentHashMap getCities(){
		
		return cacheService.getCityDetails();
		
	}

}
