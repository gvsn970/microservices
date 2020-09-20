package com.nexiilabs.stp.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexiilabs.stp.user.CreateUserModel;
import com.nexiilabs.stp.user.UserResponseDTO;

@RestController
@RequestMapping(path = "/requirements")
public class RequirementsController {

	@Autowired
	private RequirementService requirementService;

	@RequestMapping(path = "/saverequirements")
	public UserResponseDTO saveRequirements(@RequestParam("projectName") String projectName,
			@RequestParam("projectDescription") String projectDescription, @RequestParam("hmName") String hmName,
			@RequestParam("hmEmail") String hmEmail, @RequestParam("hmContact") String hmContact,
			@RequestParam("pmName") String pmName, @RequestParam("pmEmail") String pmEmail,
			@RequestParam("pmContact") String pmContact, @RequestParam("location") String location,
			@RequestParam("rateCard") double rateCard, @RequestParam("fkCustomerId") int fkCustomerId,
			@RequestParam("fkEmployeeId") int fkEmployeeId, HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		RequirementsModel requirementsModel = new RequirementsModel();
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					requirementsModel.setProjectName(projectName);
					requirementsModel.setProjectDescription(projectDescription);
					requirementsModel.setHmName(hmName);
					requirementsModel.setHmEmail(hmEmail);
					requirementsModel.setHmContact(hmContact);
					requirementsModel.setPmName(pmName);
					requirementsModel.setPmEmail(pmEmail);
					requirementsModel.setPmContact(pmContact);
					requirementsModel.setLocation(location);
					requirementsModel.setRateCard(rateCard);
					requirementsModel.setCreatedBy(userModel.getUserId());
					requirementsModel.setFkCustomerId(fkCustomerId);
					requirementsModel.setFkEmployeeId(fkEmployeeId);
					requirementsModel.setDeleteStatus(0);
					boolean flag = requirementService.requirementsExistanceCheck(requirementsModel);
					if (flag) {
						flag = requirementService.saveRequirements(requirementsModel);
						if (flag) {
							userResponseDTO.setStatusCode(1);
							userResponseDTO.setCustomerId(fkCustomerId);
							userResponseDTO.setMessage("Requirement saved successfully");
						} else {
							userResponseDTO.setStatusCode(0);
							userResponseDTO.setMessage("Requirement not saved");
						}
					} else {
						userResponseDTO.setStatusCode(2);
						userResponseDTO.setMessage("Requirement already exist");
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
			userResponseDTO.setStatusCode(0);
		}
		return userResponseDTO;
	}

	@RequestMapping(path = "/updaterequirements")
	public UserResponseDTO updateRequirements(@RequestParam("projectName") String projectName,
			@RequestParam("projectDescription") String projectDescription, @RequestParam("hmName") String hmName,
			@RequestParam("hmEmail") String hmEmail, @RequestParam("hmContact") String hmContact,
			@RequestParam("pmName") String pmName, @RequestParam("pmEmail") String pmEmail,
			@RequestParam("pmContact") String pmContact, @RequestParam("location") String location,
			@RequestParam("rateCard") int rateCard, @RequestParam("fkCustomerId") int fkCustomerId,
			@RequestParam("fkEmployeeId") int fkEmployeeId, HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		RequirementsModel requirementsModel = new RequirementsModel();
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel1 = (CreateUserModel) userSession.getAttribute("userModel");
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					requirementsModel.setProjectName(projectName);
					requirementsModel.setProjectDescription(projectDescription);
					requirementsModel.setHmName(hmName);
					requirementsModel.setHmEmail(hmEmail);
					requirementsModel.setHmContact(hmContact);
					requirementsModel.setPmName(pmName);
					requirementsModel.setPmEmail(pmEmail);
					requirementsModel.setPmContact(pmContact);
					requirementsModel.setLocation(location);
					requirementsModel.setRateCard(rateCard);
					requirementsModel.setUpdatedBy(userModel1.getUserId());
					requirementsModel.setFkCustomerId(fkCustomerId);
					requirementsModel.setFkEmployeeId(fkEmployeeId);
					requirementsModel.setDeleteStatus(0);
					boolean flag = requirementService.updateRequirements(requirementsModel);
					if (flag) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setCustomerId(fkCustomerId);
						userResponseDTO.setMessage("Requirement updated successfully");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Requirement updation failed");
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
			userResponseDTO.setStatusCode(0);
		}
		return userResponseDTO;

	}

	@RequestMapping(path = "/deleterequirements")
	public UserResponseDTO deleteRequirements(@RequestParam("requirementId") int requirementId,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		RequirementsModel requirementsModel = new RequirementsModel();
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel1 = (CreateUserModel) userSession.getAttribute("userModel");
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					requirementsModel.setRequirementId(requirementId);
					requirementsModel.setDeleteStatus(0);
					requirementsModel = requirementService.getRequirementById(requirementsModel);
					if (requirementsModel != null) {
						requirementsModel.setDeleteStatus(1);
						requirementsModel.setDeleteBy(userModel1.getUserId());
						boolean flag = requirementService.deleteRequirements(requirementsModel);
						if (flag) {
							userResponseDTO.setStatusCode(1);
							userResponseDTO.setMessage("Requirement deleted successfully");
						} else {
							userResponseDTO.setStatusCode(0);
							userResponseDTO.setMessage("Requirement deletion failed");
						}
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Requirement not exist");
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
			userResponseDTO.setStatusCode(0);
		}
		return userResponseDTO;

	}

	/* not using this service */
	@RequestMapping(path = "/getrequirements")
	public List<RequirementsModel> getRequirementsList(HttpServletRequest request, HttpServletResponse response) {

		HttpSession userSession = request.getSession(false);
		List<RequirementsModel> list = new ArrayList<RequirementsModel>();
		if (userSession != null) {
			try {
				list = requirementService.getRequirementsList();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("empty list");
			return list;
		}
		return list;
	}
}
