package com.nexiilabs.stp.authentication;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexiilabs.stp.user.CreateUserModel;
import com.nexiilabs.stp.util.MailUtils;
import com.nexiilabs.stp.util.PasswordGenerator;

@RestController
// @RequestMapping(path="/utilities")
public class UtilitiesController {

	@Autowired
	private UtilitiesService utilitiesService;

	/*
	 * @Value("${serverPort}") private String serverPort;
	 */

	/*
	 * @RequestMapping(path = "/") public ModelAndView sample() { return new
	 * ModelAndView("login"); }
	 */

	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		CreateUserModel userModel = utilitiesService.userExistancyCheck(email);
		if (userModel != null) {
			if (password.equals(userModel.getUserPassword())) {
				HttpSession userSession = request.getSession(true);
				userSession.setAttribute("userModel", userModel);
				System.err.println(userModel.getEmail() + "...............mail");
				// response.sendRedirect("welcome");
				List<String> menuList = utilitiesService.getMenuPermissions(userModel.getRoleId());
				userSession.setAttribute("menuPermissions", menuList);
				String homePage = "users";
				switch (menuList.get(0)) {
				case "User Management":
					homePage = "users";
					break;
				case "Accounts":
					homePage = "accounts";
					break;
				case "Resources":
					homePage = "add_resources";
					break;
				case "Invoices":
					homePage = "request_invoices";
					break;
				case "Bench":
					homePage = "bench";
					break;
				case "My Profile":
					homePage = "my_profile";
					break;
				case "Bank Accounts":
					homePage = "company_bankdetails";
					break;
				case "Tax Setup":
					homePage = "company_taxdetails";
					break;
				case "Prospects":
					homePage = "prospects-contacts";
					break;
				case "Reports":
					homePage = "reports";
					break;
				default:
					break;
				}
				return homePage;
			} else {
				// response.sendRedirect("login");
				return "login fail";
			}
		} else {
			// response.sendRedirect("login");
			return "User not exist";
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String doLogoutUser(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);

		try {
			if (session != null) {
				
				session.removeAttribute("userModel");
				session.removeAttribute("menuPermissions");
				session.invalidate();
				//request.getSession().invalidate();
				response.sendRedirect(request.getContextPath() + "/login");
				//System.out.println(session.toString());
				session=null;
			} else {
				response.sendRedirect("login");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "logout";
	}

	@RequestMapping(value = "/logoutcheck", method = RequestMethod.GET)
	public String doLogoutCheck(HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		String returnValue = null;
		if (userSession == null) {
			returnValue = "sessionexpired";
		} else {
			returnValue = "sessionnotexpired";
		}
		return returnValue;
	}

	@RequestMapping(path = "/changepassword")
	public String changePassword(@RequestParam("currentpassword") String currentPassword,
			@RequestParam("newpassword") String newPassword, @RequestParam("confirmpassword") String confirmPassword,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		CreateUserModel userModel1 = (CreateUserModel) userSession.getAttribute("userModel");
		// int id=(Integer) httpSession.getAttribute("userId");
		if (userModel1 != null) {
			int userId = userModel1.getUserId();
			boolean flag = false;
			currentPassword = currentPassword.trim();
			newPassword = newPassword.trim();
			confirmPassword = confirmPassword.trim();

			if (currentPassword == "") {
				return "Currentpassword sholud not be empty";
			}
			if (newPassword == "") {
				return "Newpassword sholud not be empty";
			}
			if (confirmPassword == "") {
				return "Confirmpassword sholud not be empty";
			}
			if (!(newPassword.equals(confirmPassword))) {
				return "Confirm password and new password should be same";
			}
			if (newPassword.equals(currentPassword)) {
				return "Please enter new password";
			}

			CreateUserModel userModel = new CreateUserModel();
			userModel.setUserId(userId);
			userModel.setUserPassword(newPassword);
			flag = utilitiesService.updatePassword(userModel);
			if (flag) {
				return "Password changed successfully";
			}
			return "Changing password fail";
		} else {
			return "Session expired";
		}
	}

	@RequestMapping(path = "/forgotpassword")
	public String forgotPassword(@RequestParam("email") String email) {
		email = email.trim();

		if (email == "") {
			return "Email sholud not be empty";
		}
		CreateUserModel userModel = new CreateUserModel();
		System.out.println(email + "............email");
		userModel = utilitiesService.userExistancyCheck(email);
		if (userModel != null) {

			String password = PasswordGenerator.generateRandomPassword();
			userModel.setUserId(userModel.getUserId());
			userModel.setUserPassword(password);
			boolean flag = utilitiesService.updatePassword(userModel);
			userModel.setEmail(email);
			FlasMailConfig flasMailConfig = utilitiesService.getflasMailConfig();
			if (flag && flasMailConfig != null) {
				MailUtils.sendForgotPasswordMail(userModel, flasMailConfig);
				return "New password sent to your mail";
			} else {
				return "Password Retrival failed";
			}
		} else {
			return "Email does not exist";
		}

	}

	@RequestMapping(value = "/menuNavigation", method = RequestMethod.GET)
	public String getMenuNavigation(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// get role id from users session, if null give error message else
		// get menu permissions based on the role id then construct menu
		// navigation and return
		HttpSession userSession = request.getSession(false);
		String menuContent = null;
		if (userSession != null) {
			CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
			List<String> menuList = utilitiesService.getMenuPermissions(userModel.getRoleId());
			menuContent = "<ul>";
			String userManagement = "<li class=\"has_sub\"> <a href=\"users\" class=\"waves-effect\">"
					+ "<i class=\"md md-group-work\"></i><span>User Management </span> <span class=\"menu-arrow\"></span></a>"
					+ "<ul class=\"list-unstyled\"><li><a href=\"users\">Users</a></li>"
					+ "<li><a href=\"roles\">Roles</a></li></ul></li>";
			String accounts = "<li class=\"has_sub\"> <a href=\"accounts\" class=\"waves-effect\">"
					+ "<i class=\"md md-account-child\"></i><span>Accounts </span> <span class=\"menu-arrow\"></span></a></li>";
			String resources = "<li class=\"has_sub\"> <a href=\"add_resources\" class=\"waves-effect\">"
					+ "<i class=\"md md-account-circle\"></i> <span> Resources</span> <span class=\"menu-arrow\"></span></a>"
					+ "<ul class=\"list-unstyled\">" + "<li><a href=\"add_resources\">Add Resources</a></li>"
					+ "<li><a href=\"active_resources\">Active Resources</a></li>"
					+ "<li><a href=\"inactive_resources\">Inactive Resources</a></li></ul></li>";
			String invoices = "<li class=\"has_sub\"> <a href=\"request_invoices\" class=\"waves-effect\">"
					+ "<i class=\"md md-receipt\"></i> "
					+ "<span> Invoices </span> <span class=\"menu-arrow\"></span></a>"
					+ "<ul class=\"list-unstyled\"><li><a href=\"request_invoices\">Request Invoices</a></li>"
					+ "<li><a href=\"my_invoices\">My Invoices</a></li>" + "</ul></li>";
			String bench = "<li class=\"has_sub\"> <a href=\"bench\" class=\"waves-effect\"><i class=\"md md-contacts\"></i><span>Bench </span> "
					+ "<span class=\"menu-arrow\"></span></a></li>";
			String myProfile = "<li class=\"has_sub\"> <a href=\"my_profile\" class=\"waves-effect\"><i class=\"typcn typcn-contacts\"></i>"
					+ "<span>My Profile </span> <span class=\"menu-arrow\"></span></a></li>";
			String bankAccounts = "<li class=\"has_sub\"> <a href=\"company_bankdetails\" class=\"waves-effect\"><i class=\"fa fa-bank\"></i>"
					+ "<span> Bank Details </span> <span class=\"menu-arrow\"></span></a></li>";
			String taxSetup = "<li class=\"has_sub\"> <a href=\"company_taxdetails\" class=\"waves-effect\"><i class=\"icon-credit-card\"></i>"
					+ "<span> Tax Details </span> <span class=\"menu-arrow\"></span></a></li>";

			for (String menu : menuList) {
				// System.out.println("Menu Item Name : "+menu);
				switch (menu) {
				case "User Management":
					menuContent = menuContent + userManagement;
					break;
				case "Accounts":
					menuContent = menuContent + accounts;
					break;
				case "Resources":
					menuContent = menuContent + resources;
					break;
				case "Invoices":
					menuContent = menuContent + invoices;
					break;
				case "Bench":
					menuContent = menuContent + bench;
					break;
				case "My Profile":
					menuContent = menuContent + myProfile;
					break;
				case "Bank Accounts":
					menuContent = menuContent + bankAccounts;
					break;
				case "Tax Setup":
					menuContent = menuContent + taxSetup;
					break;
				default:
					break;
				}
			}
			menuContent = menuContent + "</ul>";
		}
		return menuContent;
	}

	public String getMenuNavigationForActivePage(String activePage, List<String> menuList) {
		String menuContent = null;
		String userManagement = null;
		String accounts = null;
		String invoices = null;
		String resources = null;
		String bench = null;
		String myProfile = null;
		String bankAccounts = null;
		String taxSetup = null;
		String prospects = null;
		String reports=null;
		menuContent = "<ul>";
		if (activePage.equals("users")) {
			userManagement = " <li class=\"has_sub\"> <a href=\"users\" class=\"waves-effect\"><i class=\"md md-group-work\"></i><span>User Management </span> <span class=\"menu-arrow\"></span></a>\n"
					+ "<ul class=\"list-unstyled\"><li><a href=\"users\">Users</a></li>\n"
					+ "<li><a href=\"roles\">Roles</a></li></ul></li> ";
		} else if (activePage.equals("roles")) {
			userManagement = "<li class=\"has_sub\"> <a href=\"users\" class=\"waves-effect\"><i class=\"md md-group-work\"></i><span>User Management </span> <span class=\"menu-arrow\"></span></a>\n"
					+ " <ul class=\"list-unstyled\">\n" + " <li><a href=\"users\">Users</a></li>\n"
					+ "<li><a href=\"roles\">Roles</a></li>\n" + " </ul></li>";
		} else {
			userManagement = " <li class=\"has_sub\"> <a href=\"users\" class=\"waves-effect\"><i class=\"md md-group-work\"></i><span>User Management </span> <span class=\"menu-arrow\"></span></a>\n"
					+ " <ul class=\"list-unstyled\"> <li><a href=\"users\">Users</a></li>\n"
					+ "<li><a href=\"roles\">Roles</a></li> </ul></li>";
		}
		if (activePage.equals("accounts")) {
			accounts = " <li class=\"has_sub active\"> <a href=\"accounts\" class=\"waves-effect\"><i class=\"md md-account-child\"></i><span>Accounts </span> <span class=\"menu-arrow\"></span></a></li>";
		} else {
			accounts = " <li class=\"has_sub\"> <a href=\"accounts\" class=\"waves-effect\"><i class=\"md md-account-child\"></i><span>Accounts </span> <span class=\"menu-arrow\"></span></a></li>";
		}
		if (activePage.equals("request_invoices")) {
			invoices = "<li class=\"has_sub\"> <a href=\"request_invoices\" class=\"waves-effect\"><i class=\"md md-receipt\"></i> <span> Invoices </span> <span class=\"menu-arrow\"></span></a>\n"
					+ " <ul class=\"list-unstyled\"><li><a href=\"request_invoices\">Request Invoices</a></li>\n"
					+ " <li><a href=\"my_invoices\">My Invoices</a></li></ul></li>";
		} else if (activePage.equals("my_invoices")) {
			invoices = " <li class=\"has_sub\"> <a href=\"request_invoices\" class=\"waves-effect\"><i class=\"md md-receipt\"></i> <span> Invoices </span> <span class=\"menu-arrow\"></span></a>\n"
					+ " <ul class=\"list-unstyled\"><li><a href=\"request_invoices\">Request Invoices</a></li>\n"
					+ " <li><a href=\"my_invoices\">My Invoices</a></li> </ul></li>\n" + "                          </li>";
		} else {
			invoices = "<li class=\"has_sub\"> <a href=\"request_invoices\" class=\"waves-effect\"><i class=\"md md-receipt\"></i> <span> Invoices </span> <span class=\"menu-arrow\"></span></a>\n"
					+ "<ul class=\"list-unstyled\"><li><a href=\"request_invoices\">Request Invoices</a></li>\n"
					+ "<li><a href=\"my_invoices\">My Invoices</a></li></ul></li>";
		}
		if (activePage.equals("add_resources")) {
			resources = "<li class=\"has_sub\"> <a href=\"add_resources\" class=\"waves-effect\"><i class=\"md md-account-circle\"></i> <span> Resources</span> <span class=\"menu-arrow\"></span></a>\n"
					+ "<ul class=\"list-unstyled\"> <li><a href=\"add_resources\">Add Resources</a></li>\n"
					+ "<li><a href=\"active_resources\">Active Resources</a></li><li><a href=\"inactive_resources\">Inactive Resources</a></li>\n"
					+ "</ul></li>";
		} else if (activePage.equals("active_resources")) {
			resources = "<li class=\"has_sub\"> <a href=\"add_resources\" class=\"waves-effect\"><i class=\"md md-account-circle\"></i> <span> Resources</span> <span class=\"menu-arrow\"></span></a>\n"
					+ " <ul class=\"list-unstyled\"> <li><a href=\"add_resources\">Add Resources</a></li>\n"
					+ " <li><a href=\"active_resources\">Active Resources</a></li>\n"
					+ " <li><a href=\"inactive_resources\">Inactive Resources</a></li></ul></li>";
		} else if (activePage.equals("inactive_resources")) {
			resources = "<li class=\"has_sub\"> <a href=\"add_resources\" class=\"waves-effect\"><i class=\"md md-account-circle\"></i> <span> Resources</span> <span class=\"menu-arrow\"></span></a>\n"
					+ "<ul class=\"list-unstyled\"><li><a href=\"add_resources\">Add Resources</a></li>\n"
					+ "<li><a href=\"active_resources\">Active Resources</a></li><li><a href=\"inactive_resources\">Inactive Resources</a></li></ul></li>";
		} else {
			resources = "<li class=\"has_sub\"> <a href=\"add_resources\" class=\"waves-effect\"><i class=\"md md-account-circle\"></i> <span> Resources</span> <span class=\"menu-arrow\"></span></a>\n"
					+ "<ul class=\"list-unstyled\"><li><a href=\"add_resources\">Add Resources</a></li>\n"
					+ "<li><a href=\"active_resources\">Active Resources</a></li><li><a href=\"inactive_resources\">Inactive Resources</a></li></ul></li>";
		}
		if (activePage.equals("bench")) {
			bench = "<li class=\"has_sub active\"> <a href=\"bench\" class=\"waves-effect\"><i class=\"md md-contacts\"></i><span>Bench </span> <span class=\"menu-arrow\"></span></a></li>";
		} else {
			bench = "<li class=\"has_sub\"> <a href=\"bench\" class=\"waves-effect\"><i class=\"md md-contacts\"></i><span>Bench </span> <span class=\"menu-arrow\"></span></a></li>";
		}
		if (activePage.equals("my_profile")) {
			myProfile = " <li class=\"has_sub active\"> <a href=\"my_profile\" class=\"waves-effect\"><i class=\"typcn typcn-contacts\"></i><span>My Profile </span> <span class=\"menu-arrow\"></span></a></li>";
		} else {
			myProfile = "<li class=\"has_sub\"> <a href=\"my_profile\" class=\"waves-effect\"><i class=\"typcn typcn-contacts\"></i><span>My Profile </span> <span class=\"menu-arrow\"></span></a></li> ";
		}
		if (activePage.equals("company_bankdetails")) {
			bankAccounts = "<li class=\"has_sub active\"> <a href=\"company_bankdetails\" class=\"waves-effect\"><i class=\"fa fa-bank\"></i> <span> Bank Details </span> <span class=\"menu-arrow\"></span></a></li>";
		} else {
			bankAccounts = "<li class=\"has_sub\"> <a href=\"company_bankdetails\" class=\"waves-effect\"><i class=\"fa fa-bank\"></i> <span> Bank Details </span> <span class=\"menu-arrow\"></span></a></li>";
		}
		if (activePage.equals("company_taxdetails")) {
			taxSetup = " <li class=\"has_sub active\"> <a href=\"company_taxdetails\" class=\"waves-effect\"><i class=\"icon-credit-card\"></i> <span> Tax Details </span> <span class=\"menu-arrow\"></span></a></li>";
		} else {
			taxSetup = "  <li class=\"has_sub\"> <a href=\"company_taxdetails\" class=\"waves-effect\"><i class=\"icon-credit-card\"></i> <span> Tax Details </span> <span class=\"menu-arrow\"></span></a></li>";
		}
		if (activePage.equals("prospects-contacts")){
			prospects="<li class=\"has_sub active\"> <a href=\"prospects-contacts\" class=\"waves-effect\"><i class=\"icon-user-following\"></i> <span> Prospects </span> "
					+ "<span class=\"menu-arrow\"></span></a><ul class=\"list-unstyled\"> <li><a href=\"prospects-contacts\">Contacts</a></li>"
					+ "<li><a href=\"prospects-myfunnel\">My Funnel</a></li> <li><a href=\"prospects-followups\">Followups</a></li> </ul></li>";
		
		}else if(activePage.equals("prospects-myfunnel")){
			prospects="<li class=\"has_sub active\"> <a href=\"prospects-contacts\" class=\"waves-effect\"><i class=\"icon-user-following\"></i> <span> Prospects </span>"
					+ " <span class=\"menu-arrow\"></span></a><ul class=\"list-unstyled\"> <li><a href=\"prospects-contacts\">Contacts</a></li>"
					+ "<li><a href=\"prospects-myfunnel\">My Funnel</a></li> <li><a href=\"prospects-followups\">Followups</a></li> </ul></li>";
			
		}else if(activePage.equals("prospects-followups")){
			prospects="<li class=\"has_sub active\"> <a href=\"prospects-contacts\" class=\"waves-effect\"><i class=\"icon-user-following\"></i> <span> Prospects </span> "
					+ "<span class=\"menu-arrow\"></span></a><ul class=\"list-unstyled\"> <li><a href=\"prospects-contacts\">Contacts</a></li><li><a href=\"prospects-myfunnel\">My Funnel</a></li> "
					+ "<li><a href=\"prospects-followups\">Followups</a></li> </ul></li>";
		
		}else {
			prospects="<li class=\"has_sub active\"> <a href=\"prospects-contacts\" class=\"waves-effect\"><i class=\"icon-user-following\"></i> "
					+ "<span> Prospects </span> <span class=\"menu-arrow\"></span></a><ul class=\"list-unstyled\"> <li>"
					+ "<a href=\"prospects-contacts\">Contacts</a></li><li><a href=\"prospects-myfunnel\">My Funnel</a></li>"
					+ " <li><a href=\"prospects-followups\">Followups</a></li> </ul></li>";
		}
		if(activePage.equals("reports")){
			reports=" <li class=\"has_sub active\"> <a href=\"reports\" class=\"waves-effect\"><i class=\"fa fa-bar-chart-o\"></i><span>Reports </span> <span class=\"menu-arrow\"></span></a></li>";
		}else{
			reports="<li class=\"has_sub\"> <a href=\"reports\" class=\"waves-effect\"><i class=\"fa fa-bar-chart-o\"></i><span>Reports </span> <span class=\"menu-arrow\"></span></a></li>";
		}
		for (String menu : menuList) {
			//System.out.println("Menu Item Name : " + menu);
			switch (menu) {
			case "User Management":
				menuContent = menuContent + userManagement;
				break;
			case "Accounts":
				menuContent = menuContent + accounts;
				break;
			case "Resources":
				menuContent = menuContent + resources;
				break;
			case "Invoices":
				menuContent = menuContent + invoices;
				break;
			case "Bench":
				menuContent = menuContent + bench;
				break;
			case "My Profile":
				menuContent = menuContent + myProfile;
				break;
			case "Bank Accounts":
				menuContent = menuContent + bankAccounts;
				break;
			case "Tax Setup":
				menuContent = menuContent + taxSetup;
				break;
			case "Prospects":
				menuContent = menuContent + prospects;
				break;
			case "Reports":
				menuContent = menuContent + reports;
				break;
			default:
				break;
			}
		}
		//System.err.println("menuContent::" + menuContent);
		menuContent = menuContent + "</ul>";
		return menuContent;
	}
	public boolean authorizationsCheck(String menuName,List<String> menuList) throws IOException, ServletException {
		//System.err.println("menuName...="+menuName);
		boolean status=false;
		try{
			System.out.println(menuList.toString()+".........menuList");
			status=	menuList.contains(menuName);
			//System.out.println(status+".....status....."+menuName+"............menuName1");
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;

}

}
