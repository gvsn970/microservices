package com.nexiilabs.stp.user;

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

import com.nexiilabs.stp.util.PasswordGenerator;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LogManager.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@RequestMapping("/Welcome")
	public String getMsg() {
		log.info("in user controller!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return "hello !";
	}

	// @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders =
	// "*")
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public UserResponseDTO createUser(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("roleId") Integer roleId, @RequestParam("empNum") String empNum,
			@RequestParam("location") String location, @RequestParam("reportTo") String reportTo,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		CreateUserModel createUserModel = null;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		int userId = 0;
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				userId = userModel.getUserId();
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				System.out.println("USERID::::" + userId);
				if (menus.contains("User Management")) {
					if (firstName != null && lastName != null && email != null && roleId != 0 && reportTo != null
							&& empNum != null && location != null) {
						createUserModel = new CreateUserModel();
						createUserModel.setFirstName(firstName);
						createUserModel.setLastName(lastName);
						createUserModel.setUserName(firstName + " " + lastName);
						createUserModel.setEmail(email);
						createUserModel.setRoleId(roleId);
						createUserModel.setReportingTo(reportTo);
						createUserModel.setEmpNum(empNum);
						createUserModel.setUserLocation(location);
						createUserModel.setCreatedBy(userId);
						String password = PasswordGenerator.generateRandomPassword();
						// System.out.println("Password" + password);
						createUserModel.setUserPassword(password);
						// if repoting to

						String reportingIds = userService.getReportIds(reportTo);
						if (reportingIds != null) {
							createUserModel.setReportHierarchy(reportingIds);
						} else {
							createUserModel.setReportHierarchy(reportTo);
						}
						if (createUserModel != null) {
							userResponseDTO = userService.createUser(createUserModel);
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

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public UserResponseDTO updateUser(@RequestParam("userId") Integer userId,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("roleId") Integer roleId, @RequestParam("reportTo") String reportTo,
			@RequestParam("location") String location, HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		CreateUserModel createUserModel = null;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		int loggedInUserId = 0;
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				loggedInUserId = userModel.getUserId();
				System.out.println("USERID::::" + userId);
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("User Management")) {
					if (firstName != null && lastName != null && roleId != 0 && reportTo != null && location != null) {
						createUserModel = new CreateUserModel();
						createUserModel.setUserId(userId);
						createUserModel.setFirstName(firstName);
						createUserModel.setLastName(lastName);
						createUserModel.setUserName(firstName + " " + lastName);
						createUserModel.setRoleId(roleId);
						createUserModel.setReportingTo(reportTo);
						createUserModel.setUserLocation(location);
						createUserModel.setUpdatedBy(loggedInUserId);
						String reportingIds = userService.getReportIds(reportTo);
						// System.err.println("before iff::: reporting
						// ids"+reportingIds);
						System.out.println("userID in controller::::::::::::::" + userId);
						if (reportTo.equals("" + userId) || reportTo.equals("0")) {
							createUserModel.setReportHierarchy("" + userId);
							createUserModel.setReportingTo("" + userId);
						} else {
							if (reportingIds != null) {
								createUserModel.setReportHierarchy(reportingIds + ',' + userId);
								// System.err.println("reportingIds::::"+createUserModel.getReportHierarchy().toString());
							} else {
								createUserModel.setReportHierarchy(reportTo);
							}
						}
						if (createUserModel != null) {
							userResponseDTO = userService.updateUser(createUserModel);
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

	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.POST)
	public UserResponseDTO updateUser(@PathVariable("userId") Integer userId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		CreateUserModel createUserModel = null;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		int loggedInUserId = 0;
		List<String> menus = null;
		try {
			if (userSession != null) {
				createUserModel = new CreateUserModel();
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				loggedInUserId = userModel.getUserId();
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("User Management")) {
					System.out.println("USERID::::" + userId);
					createUserModel.setUserId(userId);
					createUserModel.setDeletedBy(loggedInUserId);
					if (createUserModel != null) {
						userResponseDTO = userService.deleteUser(createUserModel);
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

	@RequestMapping(value = "/getUsersList", method = RequestMethod.GET)
	public List<UsersListResponseDTO> getUsersList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<UsersListResponseDTO> usersListResponseDTO = null;
		List<String> menus = null;
		int userId = 0;
		try {

			if (userSession != null) {
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("User Management")) {
					CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
					userId = userModel.getUserId();
					System.err.println("USERID::::" + userId);
					usersListResponseDTO = userService.getUsersList(userId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersListResponseDTO;
	}

	@RequestMapping(value = "/getUsersListForReporting", method = RequestMethod.GET)
	public List<UsersListResponseDTO> getUsersListForReporting(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<UsersListResponseDTO> usersListResponseDTO = null;
		List<String> menus = null;
		try {
			if (userSession != null) {
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("User Management")) {
					usersListResponseDTO = userService.getUsersListForReporting();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersListResponseDTO;
	}

}
