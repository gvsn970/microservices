package com.nexiilabs.stp.prospect;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexiilabs.stp.user.UsersListResponseDTO;

@Service
public class FollowupReportsServiceImpl implements FollowupReportsService{

	@Autowired
	FollowupReportsRepository followupReportsRepository;

	@Override
	public FollowupReportsCountModel followupReportsCounts(int userId,int usertypeId) {
		FollowupReportsCountModel followupReportsCountModel=new FollowupReportsCountModel();
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
		String startDate=monday+"";
		String endDate=sunday+"";
				
		//today updated count
		int updatedType=0;
		int todayUpdatedCount=followupReportsRepository.todayFollowupUpdatedCount(userId,LocalDate.now().toString(),usertypeId);
		followupReportsCountModel.setTodayUpdateCount(todayUpdatedCount);
		//today not updated count
		int todayNotUpdatedCount=followupReportsRepository.todayFollowupNotUpdatedCount(userId,LocalDate.now().toString(),updatedType,usertypeId);
		followupReportsCountModel.setTodayNotUpdateCount(todayNotUpdatedCount);
		
		//weekly updatedcount
		int weeklyUpdatedCount=followupReportsRepository.getFollowupUpdatedCountInDateRange(userId,startDate,endDate,usertypeId);
		followupReportsCountModel.setWeeklyUpdateCount(weeklyUpdatedCount);
		//weekly not updated count
		int weeklyNotUpdatedCount=followupReportsRepository.getFollowupNotUpdatedCountInDateRange(userId,startDate,endDate,updatedType,usertypeId);
		followupReportsCountModel.setWeeklyNotUpdateCount(weeklyNotUpdatedCount);
		
		//current month updated count
		startDate=today.withDayOfMonth(1).toString();
		endDate=today.withDayOfMonth(today.lengthOfMonth()).toString();
        int monthlyUpdatedCount=followupReportsRepository.getFollowupUpdatedCountInDateRange(userId,startDate,endDate,usertypeId);
        followupReportsCountModel.setMonthlyUpdateCount(monthlyUpdatedCount);
        //current month not updated count 
        updatedType=0;
        int monthlyNotUpdatedCount=followupReportsRepository.getFollowupNotUpdatedCountInDateRange(userId,startDate,endDate,updatedType,usertypeId);
        followupReportsCountModel.setMonthlyNotUpdateCount(monthlyNotUpdatedCount);
        
		// 2 months updated count
		startDate = today.minusMonths(1).withDayOfMonth(1).toString();
		endDate = today.withDayOfMonth(today.lengthOfMonth()).toString();
		int dateRangeUpdatedCount = followupReportsRepository.getFollowupUpdatedCountInDateRange(userId, startDate, endDate,usertypeId);
		followupReportsCountModel.setDateRangeUpdateCount(dateRangeUpdatedCount);;
		// 2 months not updated count
		int dateRangeNotUpdatedCount = followupReportsRepository.getFollowupNotUpdatedCountInDateRange(userId, startDate, endDate,updatedType,usertypeId);
		followupReportsCountModel.setDateRangeNotUpdateCount(dateRangeNotUpdatedCount);
		followupReportsCountModel.setStartDate(startDate);
		followupReportsCountModel.setEndDate(endDate);

		return followupReportsCountModel;
	}
	@Override
	public FollowupReportsCountModel dateRangefollowupReportsCounts(int userId, String startDate, String endDate,int userTypeId) {
		// 2 months updated count
		FollowupReportsCountModel followupReportsCountModel=new FollowupReportsCountModel();
		int updatedType = 0;
		int dateRangeUpdatedCount = followupReportsRepository.getFollowupUpdatedCountInDateRange(userId, startDate, endDate,userTypeId);
		followupReportsCountModel.setDateRangeUpdateCount(dateRangeUpdatedCount);;
		// 2 months not updated count
		int dateRangeNotUpdatedCount = followupReportsRepository.getFollowupNotUpdatedCountInDateRange(userId, startDate, endDate,updatedType,userTypeId);
		followupReportsCountModel.setDateRangeNotUpdateCount(dateRangeNotUpdatedCount);
		followupReportsCountModel.setStartDate(startDate);
		followupReportsCountModel.setEndDate(endDate);

		return followupReportsCountModel;
	}

	
	@Override
	public List<FollowupProspectListResponseDTO> todayUpdatedFollowupReportsList(int userId, int userTypeId) {
		List<FollowupProspectListResponseDTO> list=new ArrayList<FollowupProspectListResponseDTO>();
		list=followupReportsRepository.todayFollowupUpdatedReportsList(userId,LocalDate.now().toString(),userTypeId);
		
		return list;
	}

	@Override
	public List<FollowupProspectListResponseDTO> todayNotUpdatedFollowupReportsList(int userId, int userTypeId) {
		List<FollowupProspectListResponseDTO> list=new ArrayList<FollowupProspectListResponseDTO>();
		int updatedType=0; 
		list=followupReportsRepository.todayNotUpdatedFollowupReportsList(userId,LocalDate.now().toString(),updatedType,userTypeId);
		
		return list;
	}

	@Override
	public List<FollowupProspectListResponseDTO> weeklyUpdatedFollowupReportsList(int userId,int userTypeId) {
		List<FollowupProspectListResponseDTO> list=new ArrayList<FollowupProspectListResponseDTO>();
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
		String startDate=monday+"";
		String endDate=sunday+"";
		int updatedType=1; 
		list=followupReportsRepository.followupUpdatedReportsList(userId,startDate,endDate,userTypeId);
		
		return list;
	}

	@Override
	public List<FollowupProspectListResponseDTO> weeklyNotUpdatedFollowupReportsList(int userId,int userTypeId) {
		List<FollowupProspectListResponseDTO> list=new ArrayList<FollowupProspectListResponseDTO>();
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
		String startDate=monday+"";
		String endDate=sunday+"";
		int updatedType=0; 
		list=followupReportsRepository.followupNotUpdatedReportsList(userId,startDate,endDate,updatedType,userTypeId);
		
		return list;
	}

	@Override
	public List<FollowupProspectListResponseDTO> monthlyUpdatedFollowupReportsList(int userId,int userTypeId) {
		List<FollowupProspectListResponseDTO> list=new ArrayList<FollowupProspectListResponseDTO>();
		LocalDate today = LocalDate.now();
		String startDate=today.withDayOfMonth(1).toString();
		String endDate=today.withDayOfMonth(today.lengthOfMonth()).toString();
        list=followupReportsRepository.followupUpdatedReportsList(userId,startDate,endDate, userTypeId);
		return list;
	}

	@Override
	public List<FollowupProspectListResponseDTO> monthlyNotUpdatedFollowupReportsList(int userId,int userTypeId) {
		List<FollowupProspectListResponseDTO> list=new ArrayList<FollowupProspectListResponseDTO>();
		LocalDate today = LocalDate.now();
		String startDate=today.withDayOfMonth(1).toString();
		String endDate=today.withDayOfMonth(today.lengthOfMonth()).toString();
        int updatedType=0;
        list=followupReportsRepository.followupNotUpdatedReportsList(userId,startDate,endDate,updatedType, userTypeId);
		return list;
	}

	@Override
	public List<FollowupProspectListResponseDTO> dateRangeUpdatedFollowupReportsList(int userId,String startDate, String endDate,int userTypeId) {
		List<FollowupProspectListResponseDTO> list=new ArrayList<FollowupProspectListResponseDTO>();
		//LocalDate today = LocalDate.now();
		//String startDate = today.minusMonths(1).withDayOfMonth(1).toString();
		//String endDate = today.withDayOfMonth(today.lengthOfMonth()).toString();
        list=followupReportsRepository.followupUpdatedReportsList(userId,startDate,endDate, userTypeId);
		return list;
	}

	@Override
	public List<FollowupProspectListResponseDTO> dateRangeNotUpdatedFollowupReportsList(int userId,String startDate, String endDate,int userTypeId) {
		List<FollowupProspectListResponseDTO> list=new ArrayList<FollowupProspectListResponseDTO>();
		//LocalDate today = LocalDate.now();
		//String startDate = today.minusMonths(1).withDayOfMonth(1).toString();
		//String endDate = today.withDayOfMonth(today.lengthOfMonth()).toString();
		int updatedType = 0;
        list=followupReportsRepository.followupNotUpdatedReportsList(userId,startDate,endDate,updatedType,userTypeId);
		return list;
	}
	@Override
	public List<UsersListResponseDTO> getUsersListForProspets(int userId) {
		return followupReportsRepository.getUsersListForProspets(userId);
	}

}
