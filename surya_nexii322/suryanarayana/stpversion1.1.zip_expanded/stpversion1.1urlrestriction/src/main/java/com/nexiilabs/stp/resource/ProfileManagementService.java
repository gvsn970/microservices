package com.nexiilabs.stp.resource;

import org.springframework.stereotype.Service;

import com.nexiilabs.stp.resource.EmployeeModel;
import com.nexiilabs.stp.user.CreateUserModel;

@Service
public interface ProfileManagementService {

	UserProfileResponseModel getUserprofile(CreateUserModel userModel);

	int updateUserprofile(EmployeeModel employeeModel);

}
