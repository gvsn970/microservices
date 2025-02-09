package com.nexiilabs.stp.prospect;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexiilabs.stp.user.CreateUserModel;
import com.nexiilabs.stp.user.UsersListResponseDTO;

@RestController
@RequestMapping("/folloupreports")
public class FollowupReportsController {

	@Autowired
	FollowupReportsService followupReportsService;
	
	@RequestMapping(value = "/followupReportsCounts", method = RequestMethod.GET)
	public FollowupReportsCountModel followupReportsCounts(@RequestParam("userTypeId") int userTypeId,HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		FollowupReportsCountModel  followupReportsCountModel=new FollowupReportsCountModel();
		int userId=0;
		if (userSession != null) {
			CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
			userId= userModel.getUserId();
			List<String> menuList = (List<String>) userSession.getAttribute("menuPermissions");
			if (menuList.contains("Prospects")) {
				try {
					followupReportsCountModel=followupReportsService.followupReportsCounts(userId,userTypeId);
					
					return followupReportsCountModel;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return followupReportsCountModel;		
		
	}
	@RequestMapping(value = "/dateRangefollowupReportsCounts", method = RequestMethod.GET)
	public FollowupReportsCountModel dateRangefollowupReportsCounts(@RequestParam("userTypeId") int userTypeId,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate,HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		FollowupReportsCountModel  followupReportsCountModel=new FollowupReportsCountModel();
		int userId=0;
		if (userSession != null) {
			CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
			userId= userModel.getUserId();
			List<String> menuList = (List<String>) userSession.getAttribute("menuPermissions");
			if (menuList.contains("Prospects")) {
				try {
					followupReportsCountModel=followupReportsService.dateRangefollowupReportsCounts(userId,startDate,endDate,userTypeId);
					
					return followupReportsCountModel;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return followupReportsCountModel;		
		
	}
	
	@RequestMapping(value = "/todayUpdatedFollowupReportsList", method = RequestMethod.GET)
	public List<FollowupProspectListResponseDTO> todayUpdatedFollowupReportsList(@RequestParam("userTypeId") int userTypeId,HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		List<FollowupProspectListResponseDTO>  followupTodayList=new ArrayList<FollowupProspectListResponseDTO>();
		int userId=0;
		if (userSession != null) {
			CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
			userId = userModel.getUserId();
			List<String> menuList = (List<String>) userSession.getAttribute("menuPermissions");
			if (menuList.contains("Prospects")) {
				try {
					followupTodayList=followupReportsService.todayUpdatedFollowupReportsList(userId,userTypeId);
					
					return followupTodayList;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return followupTodayList;
	}
	@RequestMapping(value = "/todayNotUpdatedFollowupReportsList", method = RequestMethod.GET)
	public List<FollowupProspectListResponseDTO> todayNotUpdatedFollowupReportsList(@RequestParam("userTypeId") int userTypeId,HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		List<FollowupProspectListResponseDTO>  followupTodayList=new ArrayList<FollowupProspectListResponseDTO>();
		int userId=0;
		if (userSession != null) {
			CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
			userId = userModel.getUserId();
			List<String> menuList = (List<String>) userSession.getAttribute("menuPermissions");
			if (menuList.contains("Prospects")) {
				try {
					followupTodayList=followupReportsService.todayNotUpdatedFollowupReportsList(userId,userTypeId);
					
					return followupTodayList;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return followupTodayList;
	}
	
	@RequestMapping(value = "/weeklyUpdatedFollowupReportsList", method = RequestMethod.GET)
	public List<FollowupProspectListResponseDTO> weeklyUpdatedFollowupReportsList(@RequestParam("userTypeId") int userTypeId,HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		List<FollowupProspectListResponseDTO>  followupTodayList=new ArrayList<FollowupProspectListResponseDTO>();
		int userId=0;
		if (userSession != null) {
			CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
			userId = userModel.getUserId();
			List<String> menuList = (List<String>) userSession.getAttribute("menuPermissions");
			if (menuList.contains("Prospects")) {
				try {
					followupTodayList=followupReportsService.weeklyUpdatedFollowupReportsList(userId,userTypeId);
					
					return followupTodayList;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return followupTodayList;
	}
	@RequestMapping(value = "/weeklyNotUpdatedFollowupReportsList", method = RequestMethod.GET)
	public List<FollowupProspectListResponseDTO> weeklyNotUpdatedFollowupReportsList(@RequestParam("userTypeId") int userTypeId,HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		List<FollowupProspectListResponseDTO>  followupTodayList=new ArrayList<FollowupProspectListResponseDTO>();
		int userId=0;
		if (userSession != null) {
			CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
			userId = userModel.getUserId();
			List<String> menuList = (List<String>) userSession.getAttribute("menuPermissions");
			if (menuList.contains("Prospects")) {
				try {
					followupTodayList=followupReportsService.weeklyNotUpdatedFollowupReportsList(userId,userTypeId);
					
					return followupTodayList;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return followupTodayList;
	}
	@RequestMapping(value = "/monthlyUpdatedFollowupReportsList", method = RequestMethod.GET)
	public List<FollowupProspectListResponseDTO> monthlyUpdatedFollowupReportsList(@RequestParam("userTypeId") int userTypeId,HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		List<FollowupProspectListResponseDTO>  followupTodayList=new ArrayList<FollowupProspectListResponseDTO>();
		int userId=0;
		if (userSession != null) {
			CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
			userId = userModel.getUserId();
			List<String> menuList = (List<String>) userSession.getAttribute("menuPermissions");
			if (menuList.contains("Prospects")) {
				try {
					followupTodayList=followupReportsService.monthlyUpdatedFollowupReportsList(userId,userTypeId);
					
					return followupTodayList;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return followupTodayList;
	}
	@RequestMapping(value = "/monthlyNotUpdatedFollowupReportsList", method = RequestMethod.GET)
	public List<FollowupProspectListResponseDTO> monthlyNotUpdatedFollowupReportsList(@RequestParam("userTypeId") int userTypeId,HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		List<FollowupProspectListResponseDTO>  followupTodayList=new ArrayList<FollowupProspectListResponseDTO>();
		int userId=0;
		if (userSession != null) {
			CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
			userId = userModel.getUserId();
			List<String> menuList = (List<String>) userSession.getAttribute("menuPermissions");
			if (menuList.contains("Prospects")) {
				try {
					followupTodayList=followupReportsService.monthlyNotUpdatedFollowupReportsList(userId,userTypeId);
					
					return followupTodayList;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return followupTodayList;
	}
	@RequestMapping(value = "/dateRangeUpdatedFollowupReportsList", method = RequestMethod.GET)
	public List<FollowupProspectListResponseDTO> dateRangeUpdatedFollowupReportsList(@RequestParam("userTypeId") int userTypeId,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate,HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		List<FollowupProspectListResponseDTO>  followupTodayList=new ArrayList<FollowupProspectListResponseDTO>();
		int userId=0;
		if (userSession != null) {
			CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
			userId = userModel.getUserId();
			List<String> menuList = (List<String>) userSession.getAttribute("menuPermissions");
			if (menuList.contains("Prospects")) {
				try {
					followupTodayList=followupReportsService.dateRangeUpdatedFollowupReportsList(userId,startDate,endDate,userTypeId);
					
					return followupTodayList;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return followupTodayList;
	}
	@RequestMapping(value = "/dateRangeNotUpdatedFollowupReportsList", method = RequestMethod.GET)
	public List<FollowupProspectListResponseDTO> dateRangeNotUpdatedFollowupReportsList(@RequestParam("userTypeId") int userTypeId,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate,HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		List<FollowupProspectListResponseDTO>  followupTodayList=new ArrayList<FollowupProspectListResponseDTO>();
		int userId=0;
		if (userSession != null) {
			CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
			userId = userModel.getUserId();
			List<String> menuList = (List<String>) userSession.getAttribute("menuPermissions");
			if (menuList.contains("Prospects")) {
				try {
					followupTodayList=followupReportsService.dateRangeNotUpdatedFollowupReportsList(userId,startDate,endDate,userTypeId);
					
					return followupTodayList;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return followupTodayList;
	}
	
	
	@RequestMapping("/sample")
	public void sample(){
		LocalDate today = LocalDate.now();

	    // Go backward to get Monday
	    LocalDate monday = today;
	    while (monday.getDayOfWeek() != DayOfWeek.MONDAY)
	    {
	      monday = monday.minusDays(1);
	    }

	    // Go forward to get Sunday
	    LocalDate sunday = today;
	    while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY)
	    {
	      sunday = sunday.plusDays(1);
	    }

	    System.out.println("Today: " + today);
	    System.out.println("Monday of the Week: " + monday);
	    System.out.println("Sunday of the Week: " + sunday);
	}
	@RequestMapping(value = "/getUsersListForProspets", method = RequestMethod.GET)
	public List<UsersListResponseDTO> getUsersListForProspets(HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<UsersListResponseDTO> usersListResponseDTO = null;
		List<String> menus = null;
		int userId = 0;
		try {

			if (userSession != null) {
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Prospects")) {
					CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
					userId = userModel.getUserId();
					System.err.println("USERID::::" + userId);
					usersListResponseDTO = followupReportsService.getUsersListForProspets(userId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersListResponseDTO;
	}
}
