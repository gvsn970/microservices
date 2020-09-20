package com.snkit.springcache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service(value = "userService")
public class UserService {
	


	
	public UserService(UserRepository userRepository,ObjectMapper objectMapper) {
		this.userRepository = userRepository;		
		this.objectMapper = objectMapper;
	}
	
	private Integer  userId = new Integer(0);
	
	private UserRepository userRepository;
	
	private ObjectMapper objectMapper;
	
	

	public void addUser(User user) {

		UserEntity uEntity = new UserEntity();

		uEntity.setName(user.getName());
		uEntity.setDesg(user.getDesg());
		uEntity.setCompName(user.getCompName());
		
		
		AddressEntity add = new AddressEntity();
		
		add.setUserEntity(uEntity);
		
	//	add.setCity(user.getCity());
		//add.setState(user.getState());
		
		uEntity.getAddressEntity().add(add);
		userRepository.save(uEntity);

	}

	
	@Cacheable(value="getUsers")
	public List<User> getAllUser() {
		
		System.out.println("  From GetAllUser    DB call");

		List<User> userList = new ArrayList<>();

		List<UserEntity> userEntity = userRepository.findAll();

		userEntity.forEach(user -> {
			User u = new User(user.getName(), user.getDesg());
			u.setCompName(user.getCompName());
			
			u.setId(userId++);
			
			user.getAddressEntity().forEach(add -> {
				Address addVo = new Address();
				
				addVo.setCity(add.getCity());
				addVo.setState(add.getState());
				u.getAddList().add(addVo);
				
			});

			userList.add(u);
		});

		return userList;

	}
	@Cacheable(value="userAddCache",key="#user1.getName()")
	public User getByUser(User user1) {

		System.out.println("   From  @Cacheable getByUser    ::::::::::::::::::::::::::::::::::::::::::::::::   ");
		
		UserEntity user = userRepository.findByName(user1.getName());
		User u = new User(user.getName(), user.getDesg());
		u.setCompName(user.getCompName());
	
		
		throw new CacheUserNotFoundException("CE001","You can not cache the this user data");

		//return u;
	}
	
	@CachePut(value="userAddCache" ,key="#user1.getName()")
	public User updateCache(User user1) {
		
		
		System.out.println("   From  @CachePut updateCache    ::::::::::::::::::::::::::::::::::::::::::::::::   ");
		

		UserEntity user = userRepository.findByName(user1.getName());
		user.setDesg(user1.getDesg());
		
		UserEntity finalUser = userRepository.save(user);
		
		User u = new User(finalUser.getName(), finalUser.getDesg());
		u.setCompName(finalUser.getCompName());		

		return u;
	}
	
	
	@CacheEvict(value="userAddCache",key="#user.getName()")
	public void deleteCache(User user) {

		System.out.println("    Removing from Cache data from EhCache ");
	}
	
	public User findUserAddByName(User user1) {

		UserEntity user = userRepository.getUserEntity(user1.getName());
		
		
		User u = new User(user.getName(), user.getDesg());
		
		u.setCompName(user.getCompName());
		
		user.getAddressEntity().forEach(add -> {
			Address addVo = new Address();
			
			addVo.setCity(add.getCity());
			addVo.setState(add.getState());
			u.getAddList().add(addVo);
			
		});

		return u;
	}
	
	public UserInfo findUserAddByName1(User user1) {

		UserEntity user = userRepository.getUserEntity(user1.getName());
		
		
		UserInfo u = new UserInfo(user.getName(), user.getDesg());
		u.setShareValue("Test");
		u.setCompName(user.getCompName());
		
		user.getAddressEntity().forEach(add -> {
			Address addVo = new Address();
			
			addVo.setCity(add.getCity());
			addVo.setState(add.getState());
			u.getAddList().add(addVo);
			
		});

		return u;
	}
	public List<User> findByUserCity(UserReq userReq) {

		List<User> userList = new ArrayList<>();
		
		UserSpecification spec =new UserSpecification(userReq);

		
	
		
		List<UserEntity> userEntity = userRepository.findAll(spec);
		
		

		userEntity.forEach(user -> {
			User u = new User(user.getName(), user.getDesg());
			u.setCompName(user.getCompName());
			
			user.getAddressEntity().forEach(add -> {
				Address addVo = new Address();
				
				addVo.setCity(add.getCity());
				addVo.setState(add.getState());
				u.getAddList().add(addVo);
				
			});

			userList.add(u);
		});

	//	return userList;
		
		throw new UserNotFoundException("UN001","User not found in repository");

	}
}
