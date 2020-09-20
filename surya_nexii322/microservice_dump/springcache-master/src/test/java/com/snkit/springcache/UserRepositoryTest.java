package com.snkit.springcache;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class	)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	UserRepository userRepository;
	
	List<UserEntity> userEntityList ;
	
	@Before
	public void setup() {
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
		
		userEntityList = new ArrayList<UserEntity>();
		
		userEntityList.add(uEntity);
		userEntityList.add(uEntity1);
		
		
	}
	
	
	
	@Test
	public void testFindByName() {
		
		userRepository.save(userEntityList);
		
		UserEntity userEntity = userRepository.findByName("Tim");
		
		assertEquals(userEntity.getName(),"Tim");
		assertEquals(userEntity.getDesg(),"CEO");
	}

}
