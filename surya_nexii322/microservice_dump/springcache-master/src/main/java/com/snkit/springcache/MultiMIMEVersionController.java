package com.snkit.springcache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiMIMEVersionController {
	
	
	
	
	
	@Autowired
	UserService userService;
	
	
	

	

	@RequestMapping( value= "/getUsers",method=RequestMethod.GET)
	public List<User> getUsers() {
		
		System.out.println("    From getUsers  ");
		return userService.getAllUser();
	}
	
	
	
	
	
	
	@RequestMapping( value= "/cacheableUser",	method=RequestMethod.POST,
			consumes= {"application/json","application/xml"},
			produces= {"application/json","application/xml"}
			)
	public User cacheableUser(@RequestBody User user) {
		return userService.getByUser(user);
	}
	
	
	@RequestMapping( value= "/cachePutUser",	method=RequestMethod.POST,
			consumes= {"application/json","application/xml"},
			produces= {"application/json","application/xml"}
			)
	public User cachePutUser(@RequestBody User user) {
		return userService.updateCache(user);
	}
	
	
	@RequestMapping( value= "/deleteCache",method=RequestMethod.POST,
			consumes= {"application/json","application/xml"},
			produces= {"application/json","application/xml"})
	public void deleteCache(@RequestBody User user) {
		 userService.deleteCache(user);
	}
	
	
	

	@RequestMapping( value= "/findUserByName",	method=RequestMethod.POST,
			consumes= {"application/json","application/xml"},
			produces= {"application/json","application/xml"}
			)
	public User findUserByName(@RequestBody User user) {
		return userService.getByUser(user);
	}
	@RequestMapping( value= "/findUserByName",	method=RequestMethod.POST,
			consumes= {"application/vnd.snkit-v1+json","application/vnd.snkit-v1+xml"} ,
			produces = { "application/vnd.snkit-v1+json","application/vnd.snkit-v1+xml" }
			)
	public User findUserAddByName(@RequestBody User user) {
		return userService.findUserAddByName(user);
	}
	
	
	@RequestMapping( value= "/findUserByName",	method=RequestMethod.POST,
			consumes= {"application/vnd.snkit-v2+json","application/vnd.snkit-v2+xml"} ,
			produces = { "application/vnd.snkit-v2+json","application/vnd.snkit-v2+xml" }
			)
	public UserInfo findUserAddByName1(@RequestBody User user) {
		return userService.findUserAddByName1(user);
	}
	
	
	@RequestMapping( value= "/findByUserCity",	method=RequestMethod.POST,
			consumes= {"application/json","application/xml"},
			produces= {"application/json","application/xml"}
			)
	public List<User> findByUserCity(@RequestBody UserReq user) {
		
		System.out.println("  findByUserCity   from controller  ");
		
		return userService.findByUserCity(user);
	}
	
}
