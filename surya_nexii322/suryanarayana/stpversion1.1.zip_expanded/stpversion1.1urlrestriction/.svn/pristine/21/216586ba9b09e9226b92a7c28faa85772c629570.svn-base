package com.nexiilabs.stp.bench;

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

@RestController
@RequestMapping("/bench")
public class BenchController {
	private static final Logger log = LogManager.getLogger(BenchController.class);
	@Autowired
	BenchService benchService;

	@RequestMapping("/welcome")
	public String getMsg() {
		log.info("in Bench List Controller --- welcome");
		return "BenchListRESTService";
	}
	@RequestMapping(value = "/getBenchResourcesList/{expireIn}", method = RequestMethod.GET)
	public List<BenchListResponseDTO> getBenchResourcesList(@PathVariable("expireIn") int expireIn,HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<BenchListResponseDTO> benchListResponseDTOs = null;
		try {
			if(userSession!=null){
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("Bench")){
						benchListResponseDTOs = benchService.getBenchResourcesList(expireIn,userModel.getUserId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return benchListResponseDTOs;
	}
	@RequestMapping(value = "/expiredResourceReassign", method = RequestMethod.POST)
	public UserResponseDTO expiredResourceReassign(@RequestParam("resourceId") int resourceId,HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		UserResponseDTO userResponseDTO = null;
		try {
			userResponseDTO=new UserResponseDTO();
			String status="";
			if(userSession!=null){
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("Bench")){
					
					userResponseDTO=benchService.expiredResourceReassign(resourceId,userModel.getUserId());
					
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




















