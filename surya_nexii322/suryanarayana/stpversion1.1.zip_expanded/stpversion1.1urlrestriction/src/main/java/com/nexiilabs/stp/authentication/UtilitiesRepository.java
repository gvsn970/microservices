package com.nexiilabs.stp.authentication;

import java.util.List;

import com.nexiilabs.stp.user.CreateUserModel;

public interface UtilitiesRepository {

	boolean updatePassword(CreateUserModel userModel);

	CreateUserModel userExistancyCheck(String email);

	FlasMailConfig getflasMailConfig();
	
	List<String> getMenuPermissions(int roleId);
}
