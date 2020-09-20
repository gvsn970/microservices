package com.nexiilabs.stp.bankaccount;

import com.nexiilabs.stp.user.UserResponseDTO;

public interface AddressRepository {

	UserResponseDTO addAddress(AddressModel addressModel);

}
