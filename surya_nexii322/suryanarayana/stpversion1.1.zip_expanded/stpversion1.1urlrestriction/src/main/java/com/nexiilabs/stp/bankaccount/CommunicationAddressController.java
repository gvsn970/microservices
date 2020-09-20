package com.nexiilabs.stp.bankaccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nexiilabs.stp.user.UserResponseDTO;

@RestController
@RequestMapping("/address")
public class CommunicationAddressController {
	private static final Logger log = LogManager.getLogger(BankAccountController.class);

	@Autowired
	AddressService addressService;
	@RequestMapping(value = "/addAddress/{address}/{addressName}", method = RequestMethod.POST)
	public UserResponseDTO addAddress(@PathVariable("address") String address,
			@PathVariable("addressName") String addressName,HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		AddressModel addressModel = null;
		UserResponseDTO userResponseDTO = null;
		try {
			addressModel = new AddressModel();
			/*
			 * CreateUserModel userModel1 = (CreateUserModel)
			 * userSession.getAttribute("userModel"); int userId=0;
			 * if(userModel1!=null) { userId=userModel1.getUserId();
			 * System.err.println("USERID::::"+userId); }
			 */
			addressModel.setAddress(address);
			addressModel.setAddressName(addressName);
			if (addressModel != null) {
				userResponseDTO = new UserResponseDTO();
				userResponseDTO = addressService.addAddress(addressModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setMessage(e.getMessage());
			userResponseDTO.setStatusCode(0);
		}
		return userResponseDTO;
	}
}
