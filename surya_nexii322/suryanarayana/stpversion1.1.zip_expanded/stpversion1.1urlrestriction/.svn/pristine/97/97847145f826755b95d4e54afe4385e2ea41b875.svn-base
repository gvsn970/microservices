package com.nexiilabs.stp.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexiilabs.stp.resource.EmployeeModel;
import com.nexiilabs.stp.user.CreateUserModel;
import com.nexiilabs.stp.user.UserResponseDTO;

@RestController
@RequestMapping("/profile")
public class ProfileManagementController {

	@Autowired
	ProfileManagementService profileManagementService;

	@RequestMapping(value = "/getmyprofile", method = RequestMethod.GET)
	public UserProfileResponseModel getUserprofile(HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		CreateUserModel userModel = null;
		UserProfileResponseModel userProfileResponseModel = null;

		try {
			if (userSession != null) {
				userModel = (CreateUserModel) userSession.getAttribute("userModel");
				List<String> menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("My Profile")) {
					userProfileResponseModel = profileManagementService.getUserprofile(userModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userProfileResponseModel;
	}

	@RequestMapping(value = "/updatemyprofile", method = RequestMethod.POST)
	public UserResponseDTO updateUserprofile(@RequestParam("empId") int empId, @RequestParam("empName") String empName,
			@RequestParam("empMailId") String empMailId, @RequestParam("empContact") String empContact,
			@RequestParam("empSkill") String empSkill, @RequestParam("empExp") String empExp,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession userSession = request.getSession(false);
		CreateUserModel userModel = null;
		int update = 0;
		List<String> menus = null;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			if (userSession != null) {
				userModel = (CreateUserModel) userSession.getAttribute("userModel");
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("My Profile")) {
					EmployeeModel employeeModel = new EmployeeModel();
					employeeModel.setEmployeeId(empId);
					employeeModel.setEmpName(empName);
					employeeModel.setEmpEmailID(empMailId);
					employeeModel.setEmpContact(empContact);
					employeeModel.setSkillSet(empSkill);
					employeeModel.setExperienceLevel(empExp);
					update = profileManagementService.updateUserprofile(employeeModel);
					if (update != 0) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setMessage("Profile Updated Successfully");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Profile updation fail");
					}
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Not permitted to access the service");
				}
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Login Required to Access this service");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userResponseDTO;
	}

}
