package com.nexiilabs.stp.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexiilabs.stp.user.CreateUserModel;


@Service
public class UtilitiesServiceImpl implements UtilitiesService{

	@Autowired
	private UtilitiesRepository utilitiesRepository;

	@Override
	public boolean updatePassword(CreateUserModel userModel) {
		return utilitiesRepository.updatePassword(userModel);
	}

	@Override
	public CreateUserModel userExistancyCheck(String email) {
		return utilitiesRepository.userExistancyCheck(email);
	}

	@Override
	public FlasMailConfig getflasMailConfig() {
		return utilitiesRepository.getflasMailConfig();
	}
	
	@Override
	public List<String> getMenuPermissions(int roleId) {
		return utilitiesRepository.getMenuPermissions(roleId);
	}
}
