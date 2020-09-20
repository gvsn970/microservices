package com.nexiilabs.stp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexiilabs.stp.user.UserResponseDTO;
import com.nexiilabs.stp.user.UsersListResponseDTO;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public UserResponseDTO createResource(EmployeeModel empModel) {
		UserResponseDTO userResponseDTO=employeeRepository.createResource(empModel);
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO extendEmployeeJoinDate(EmployeeModel empModel) {
		UserResponseDTO userResponseDTO=employeeRepository.extendEmployeeJoinDate(empModel);
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO updateResource(EmployeeModel empModel) {
		UserResponseDTO userResponseDTO=employeeRepository.updateResource(empModel);
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO deleteResource(EmployeeModel empModel) {
		UserResponseDTO userResponseDTO=employeeRepository.deleteResource(empModel);
		return userResponseDTO;
	}

	@Override
	public List<EmployeeListResponseDTO> getActiveResourceList(int userId) {
		return  employeeRepository.getActiveResourceList(userId);
	}

	@Override
	public UserResponseDTO activateEmployee(EmployeeModel empModel) {
		UserResponseDTO userResponseDTO=employeeRepository.activateEmployee(empModel);
		return userResponseDTO;
	}

	@Override
	public List<EmployeeListResponseDTO> getInActiveResourceList(int userId) {
		return  employeeRepository.getInActiveResourceList(userId);
	}

	@Override
	public EmployeeResponseDTO getEmployeeDetails(Integer empId) {
		return  employeeRepository.getEmployeeDetails(empId);

	}

	@Override
	public EmployeeResponseDTO getProjectDetails(Integer empId) {
		return  employeeRepository.getProjectDetails(empId);
	}

	@Override
	public EmployeeResponseDTO getPoDetails(Integer empId) {
		return  employeeRepository.getPoDetails(empId);
	}

	@Override
	public List<FilesListResponseDTO> getSowDetails(Integer empId) {
		return  employeeRepository.getSowDetails(empId);
	}

	@Override
	public List<FilesListResponseDTO> getPoUploadDetails(Integer empId) {
		return  employeeRepository.getPoUploadDetails(empId);
	}

	@Override
	public UserResponseDTO reassignResource(Integer employeeId, String email, Integer newcreatedBy) {
		UserResponseDTO userResponseDTO=employeeRepository.reassignResource(employeeId,email,newcreatedBy);
		return userResponseDTO;
	}

	@Override
	public List<UsersListResponseDTO> getUserListForReassign() {
		return  employeeRepository.getUserListForReassign();
	}

	@Override
	public UserResponseDTO checkDeleteResource(Integer employeeId) {
		UserResponseDTO userResponseDTO=employeeRepository.checkDeleteResource(employeeId);
		return userResponseDTO;
	}

}
