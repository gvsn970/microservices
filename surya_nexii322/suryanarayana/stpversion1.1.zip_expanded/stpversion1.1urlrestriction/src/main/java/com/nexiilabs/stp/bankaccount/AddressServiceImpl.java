package com.nexiilabs.stp.bankaccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexiilabs.stp.user.UserResponseDTO;

@Service
public class AddressServiceImpl implements AddressService
{
	@Autowired
	AddressRepository addressRepository;
	@Override
	public UserResponseDTO addAddress(AddressModel addressModel) {
		UserResponseDTO userResponseDTO=addressRepository.addAddress(addressModel);
		return userResponseDTO;
	}

}
