package com.nexiilabs.stp.resource;

import java.util.List;

import com.nexiilabs.stp.user.UserResponseDTO;
import com.nexiilabs.stp.user.UsersListResponseDTO;

public interface EmployeeService {

	UserResponseDTO createResource(EmployeeModel empModel);

	UserResponseDTO extendEmployeeJoinDate(EmployeeModel empModel);

	UserResponseDTO updateResource(EmployeeModel empModel);

	UserResponseDTO deleteResource(EmployeeModel empModel);

	List<EmployeeListResponseDTO> getActiveResourceList(int userId);

	UserResponseDTO activateEmployee(EmployeeModel empModel);

	List<EmployeeListResponseDTO> getInActiveResourceList(int userId);

	EmployeeResponseDTO getEmployeeDetails(Integer empId);

	EmployeeResponseDTO getProjectDetails(Integer empId);

	EmployeeResponseDTO getPoDetails(Integer empId);

	List<FilesListResponseDTO> getSowDetails(Integer empId);

	List<FilesListResponseDTO> getPoUploadDetails(Integer empId);

	UserResponseDTO reassignResource(Integer employeeId, String email, Integer newcreatedBy);

	List<UsersListResponseDTO> getUserListForReassign();

	UserResponseDTO checkDeleteResource(Integer employeeId);


}
