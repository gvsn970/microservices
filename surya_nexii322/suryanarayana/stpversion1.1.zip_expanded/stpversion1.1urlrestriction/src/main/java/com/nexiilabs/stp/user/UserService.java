package com.nexiilabs.stp.user;

import java.util.List;

public interface UserService {

	UserResponseDTO createUser(CreateUserModel createUserModel);

	String getReportIds(String reportTo);

	UserResponseDTO updateUser(CreateUserModel createUserModel);

	UserResponseDTO deleteUser(CreateUserModel createUserModel);

	List<UsersListResponseDTO> getUsersList(int userId);

	List<UsersListResponseDTO> getUsersListForReporting();

	
}
