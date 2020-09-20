package com.nexiilabs.stp.bankaccount;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.nexiilabs.stp.user.UserResponseDTO;
@Transactional
@Repository
public class AddressRepositoryImpl implements AddressRepository
{
	private static final Logger log = LogManager.getLogger(AddressRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public UserResponseDTO addAddress(AddressModel addressModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
				entityManager.persist(addressModel);
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Address added Succesfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage("Address addition Failed");
		}
		return userResponseDTO;
	}

}
