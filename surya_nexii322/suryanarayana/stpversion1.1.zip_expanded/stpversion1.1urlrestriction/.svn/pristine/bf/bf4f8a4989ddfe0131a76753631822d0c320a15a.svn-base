package com.nexiilabs.stp.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	public UserResponseDTO createUser(CreateUserModel createUserModel) {
		UserResponseDTO userResponseDTO = userRepository.createUser(createUserModel);
		return userResponseDTO;
	}

	public String getReportIds(String reportTo) {
		String reportId=userRepository.getReportIds(reportTo);
		return reportId;
	}

	public UserResponseDTO updateUser(CreateUserModel createUserModel) {
		UserResponseDTO userResponseDTO = userRepository.updateUser(createUserModel);
		return userResponseDTO;
	}

	public UserResponseDTO deleteUser(CreateUserModel createUserModel) {
		UserResponseDTO userResponseDTO = userRepository.deleteUser(createUserModel);
		return userResponseDTO;
	}

	public List<UsersListResponseDTO> getUsersList(int userId) {
		return  userRepository.getUsersList(userId);
	}

	@Override
	public List<UsersListResponseDTO> getUsersListForReporting() {
		return  userRepository.getUsersListForReporting();
	}

	
	
}
