package com.nexiilabs.stp.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexiilabs.stp.user.CreateUserModel;
import com.nexiilabs.stp.user.UserResponseDTO;
import com.nexiilabs.stp.user.UsersListResponseDTO;

@RestController
@RequestMapping("/resource")
public class EmployeeController {
	private static final Logger log = LogManager.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/createResource", method = RequestMethod.POST)
	public UserResponseDTO createResource(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("empEmailID") String email,
			@RequestParam("empContact") String contact, @RequestParam("empJoiningDate") String joinDate,
			@RequestParam("skillSet") String skillSet, @RequestParam("experienceLevel") String experienceLevel,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		EmployeeModel empModel = null;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		int userId = 0;
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				userId = userModel.getUserId();
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					if (firstName != null && lastName != null && email != null && contact != null
							&& experienceLevel != null && joinDate != null && skillSet != null) {
						empModel = new EmployeeModel();
						empModel.setFirstName(firstName);
						empModel.setLastName(lastName);
						empModel.setEmpName(firstName + " " + lastName);
						empModel.setEmpEmailID(email);
						empModel.setEmpContact(contact);
						empModel.setEmpJoiningDate(joinDate);
						empModel.setExperienceLevel(experienceLevel);
						empModel.setSkillSet(skillSet);
						empModel.setCreatedBy(userId);
						if (empModel != null) {
							userResponseDTO = employeeService.createResource(empModel);
						}
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("All input fields are Mandatory");
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
			userResponseDTO.setMessage(e.getMessage());
			userResponseDTO.setStatusCode(0);
		}
		return userResponseDTO;
	}

	@RequestMapping(value = "/updateResource", method = RequestMethod.POST)
	public UserResponseDTO updateResource(@RequestParam("employeeId") Integer employeeId,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email, @RequestParam("contact") String contact,
			@RequestParam("joinDate") String joinDate, @RequestParam("skillSet") String skillSet,
			@RequestParam("experienceLevel") String experienceLevel, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		EmployeeModel empModel = null;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		int userId = 0;
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				userId = userModel.getUserId();
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					if (employeeId != 0 && firstName != null && lastName != null && email != null && contact != null
							&& experienceLevel != null && skillSet != null) {
						empModel = new EmployeeModel();
						empModel.setEmployeeId(employeeId);
						empModel.setFirstName(firstName);
						empModel.setLastName(lastName);
						empModel.setEmpName(firstName + " " + lastName);
						empModel.setEmpEmailID(email);
						empModel.setEmpContact(contact);
						empModel.setEmpJoiningDate(joinDate);
						empModel.setExperienceLevel(experienceLevel);
						empModel.setSkillSet(skillSet);
						empModel.setUpdatedBy(userId);
						if (empModel != null) {
							userResponseDTO = employeeService.updateResource(empModel);
						}
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("All input fields are Mandatory");
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
			userResponseDTO.setMessage(e.getMessage());
			userResponseDTO.setStatusCode(0);
		}
		return userResponseDTO;
	}
	@RequestMapping(value = "/checkDeleteResource/{employeeId}", method = RequestMethod.POST)
	public UserResponseDTO deleteResource(@PathVariable("employeeId") Integer employeeId)
	{
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO=employeeService.checkDeleteResource(employeeId);
		return userResponseDTO;
	}
	@RequestMapping(value = "/deleteResource/{email}/{employeeId}", method = RequestMethod.POST)
	public UserResponseDTO deleteResource(@PathVariable("email") String email,
			@PathVariable("employeeId") Integer employeeId, HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		EmployeeModel empModel = null;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		int userId = 0;
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				userId = userModel.getUserId();
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					if (email != null && employeeId != 0) {
						System.err.println("email:::::::::" + email);
						empModel = new EmployeeModel();
						empModel.setEmpEmailID(email);
						empModel.setDeletedBy(userId);
						empModel.setEmployeeId(employeeId);
						if (empModel != null) {
							userResponseDTO = employeeService.deleteResource(empModel);
						}
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("All input fields are Mandatory");
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
			userResponseDTO.setMessage(e.getMessage());
			userResponseDTO.setStatusCode(0);
		}
		return userResponseDTO;
	}

	@RequestMapping(value = "/activeResourceList", method = RequestMethod.GET)
	public List<EmployeeListResponseDTO> getActiveResourceList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<EmployeeListResponseDTO> employeeListResponseDTO = null;
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					employeeListResponseDTO = employeeService.getActiveResourceList(userModel.getUserId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeListResponseDTO;
	}

	@RequestMapping(value = "/inactiveResourceList", method = RequestMethod.GET)
	public List<EmployeeListResponseDTO> getInActiveResourceList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<EmployeeListResponseDTO> employeeListResponseDTO = null;
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					employeeListResponseDTO = employeeService.getInActiveResourceList(userModel.getUserId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeListResponseDTO;
	}

	@RequestMapping(value = "/getResourceDetails/{empId}", method = RequestMethod.GET)
	public EmployeeResponseDTO getResourceDetails(@PathVariable("empId") Integer empId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		EmployeeResponseDTO employeeResponseDTO = null;
		List<String> menus = null;
		try {
			if (userSession != null) {
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					employeeResponseDTO = employeeService.getEmployeeDetails(empId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeResponseDTO;
	}

	@RequestMapping(value = "/getProjectDetails/{empId}", method = RequestMethod.GET)
	public EmployeeResponseDTO getProjectDetails(@PathVariable("empId") Integer empId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		EmployeeResponseDTO employeeResponseDTO = null;
		List<String> menus = null;
		try {
			if (userSession != null) {
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					employeeResponseDTO = employeeService.getProjectDetails(empId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeResponseDTO;
	}

	@RequestMapping(value = "/getPoDetails/{empId}", method = RequestMethod.GET)
	public EmployeeResponseDTO getPoDetails(@PathVariable("empId") Integer empId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		EmployeeResponseDTO employeeResponseDTO = null;
		List<String> menus = null;

		try {
			if (userSession != null) {
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					employeeResponseDTO = employeeService.getPoDetails(empId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeResponseDTO;
	}

	@RequestMapping(value = "/getPoUploadDetails/{empId}", method = RequestMethod.GET)
	public List<FilesListResponseDTO> getPoUploadDetails(@PathVariable("empId") Integer empId,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<FilesListResponseDTO> filesListResponseDTO = null;
		List<String> menus = null;
		try {
			if (userSession != null) {
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					filesListResponseDTO = employeeService.getPoUploadDetails(empId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filesListResponseDTO;
	}

	@RequestMapping(value = "/getSowDetails/{empId}", method = RequestMethod.GET)
	public List<FilesListResponseDTO> getSowDetails(@PathVariable("empId") Integer empId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<FilesListResponseDTO> filesListResponseDTO = null;
		List<String> menus = null;
		try {
			if (userSession != null) {
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					filesListResponseDTO = employeeService.getSowDetails(empId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filesListResponseDTO;
	}

	@RequestMapping(value = "/reassignResource", method = RequestMethod.POST)
	public UserResponseDTO reassignResource(@RequestParam("employeeId") Integer employeeId,
			@RequestParam("email") String email, @RequestParam("newcreatedBy") Integer newcreatedBy,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		List<String> menus = null;
		try {
			if (userSession != null) {
				//CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					userResponseDTO = employeeService.reassignResource(employeeId,email,newcreatedBy);
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
			userResponseDTO.setMessage(e.getMessage());
			userResponseDTO.setStatusCode(0);
		}
		return userResponseDTO;

	}
	@RequestMapping(value = "/getUserListForReassign", method = RequestMethod.GET)
	public List<UsersListResponseDTO> getUserListForReassign(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<UsersListResponseDTO> usersListResponseDTO = null;
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Resources")) {
					usersListResponseDTO = employeeService.getUserListForReassign();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersListResponseDTO;
	}

	/* Services which are not using */
	@RequestMapping(value = "/extendJoiningDate/{empId}/{joinDate}", method = RequestMethod.POST)
	public UserResponseDTO extendEmployeeJoinDate(@PathVariable("empId") Integer empId,
			@PathVariable("joinDate") String joinDate, HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		EmployeeModel empModel = null;
		UserResponseDTO userResponseDTO = null;
		try {
			empModel = new EmployeeModel();
			empModel.setEmployeeId(empId);
			empModel.setEmpJoiningDate(joinDate);
			if (empModel != null) {
				userResponseDTO = new UserResponseDTO();
				userResponseDTO = employeeService.extendEmployeeJoinDate(empModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setMessage(e.getMessage());
			userResponseDTO.setStatusCode(0);
		}
		return userResponseDTO;

	}

	@RequestMapping(value = "/activateResource/{empId}/{poId}", method = RequestMethod.POST)
	public UserResponseDTO activateEmployee(@PathVariable("empId") Integer empId, @PathVariable("poId") Integer poId,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		EmployeeModel empModel = null;
		UserResponseDTO userResponseDTO = null;
		try {
			empModel = new EmployeeModel();
			empModel.setEmployeeId(empId);
			empModel.setPoId(poId);
			if (empModel != null) {
				userResponseDTO = new UserResponseDTO();
				userResponseDTO = employeeService.activateEmployee(empModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setMessage(e.getMessage());
			userResponseDTO.setStatusCode(0);
		}
		return userResponseDTO;

	}

}
