package com.nexiilabs.stp.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexiilabs.stp.resource.EmployeeModel;
import com.nexiilabs.stp.user.CreateUserModel;

@Service
public class ProfileManagementServiceImpl implements ProfileManagementService{
	
	@Autowired
	ProfileManagementDao profileManagementDao;

	@Override
	public UserProfileResponseModel getUserprofile(CreateUserModel userModel) {
		return profileManagementDao.getUserprofile(userModel);
	}

	@Override
	public int updateUserprofile(EmployeeModel employeeModel) {
		return profileManagementDao.updateUserprofile(employeeModel);
	}

}
