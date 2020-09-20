package com.snkit.springbootdatajpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootdatajpaApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdatajpaApplication.class, args);
	}

	

	@Bean
	public AlwaysSampler defaultSampler() {
	  return new AlwaysSampler();
	}
	
	@Override
	public void run(String... arg0) throws Exception {
	
		
		UserEntity uEntity = new UserEntity();

		uEntity.setName("Tim");
		uEntity.setDesg("CEO");
		uEntity.setCompName("ABC");
		
		
		AddressEntity add = new AddressEntity();
		
		add.setCity("Hyd");
		add.setState("Telangana");		
		add.setUserEntity(uEntity);
		
		AddressEntity add1 = new AddressEntity();
		
		add1.setCity("Chennai");
		add1.setState("Tamilnadu");		
		add1.setUserEntity(uEntity);
		
		
		uEntity.getAddressEntity().add(add);
		uEntity.getAddressEntity().add(add1);
		
		
		
		
		UserEntity uEntity1 = new UserEntity();

		uEntity1.setName("Ravi");
		uEntity1.setDesg("CEO");
		uEntity1.setCompName("XYZ");
		
		
		AddressEntity uEntityadd = new AddressEntity();
		
		uEntityadd.setCity("Dubai");
		uEntityadd.setState("UAE");		
		uEntityadd.setUserEntity(uEntity1);
		
		AddressEntity uEntityadd1 = new AddressEntity();
		
		uEntityadd1.setCity("AlAlin");
		uEntityadd1.setState("UAE");		
		uEntityadd1.setUserEntity(uEntity1);
		
		
		uEntity1.getAddressEntity().add(uEntityadd);
		uEntity1.getAddressEntity().add(uEntityadd1);
		
		List<UserEntity> userEntityList = new ArrayList<UserEntity>();
		
		userEntityList.add(uEntity);
		userEntityList.add(uEntity1);
		
		userRepository.save(userEntityList);
		
	}
}
