package com.snkit.springbootdatajpa;

import java.util.ArrayList;
import java.util.List;

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

	public List<User> getAllUser() {

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

	public User getByUser(User user1) {

		UserEntity user = userRepository.findByName(user1.getName());
		
		
		User u = new User(user.getName(), user.getDesg());
		u.setCompName(user.getCompName());

		return u;
	}
	
	public UserInfo findUserAddByName(User user1) {

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

		return userList;

	}
}
