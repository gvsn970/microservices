package com.nexiilabs.stp.prospect;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nexiilabs.stp.user.UsersListResponseDTO;

@Repository
public interface FollowupReportsRepository {

	List<FollowupProspectListResponseDTO> todayNotUpdatedFollowupReportsList(int userId,String toDay,int updateStatus,int userTypeId);
	List<FollowupProspectListResponseDTO> followupNotUpdatedReportsList(int userId, String startDate, String endDate,
			int updatedType,int userTypeId);
	List<FollowupProspectListResponseDTO> todayFollowupUpdatedReportsList(int userId, String toDay, int userTypeId);
	List<FollowupProspectListResponseDTO> followupUpdatedReportsList(int userId, String startDate, String endDate,
			int userTypeId);
	int todayFollowupNotUpdatedCount(int userId, String today,int updatedType,int usertypeId);
	int getFollowupNotUpdatedCountInDateRange(int userId, String startDate, String endDate, int updatedType,int usertypeId);
	int todayFollowupUpdatedCount(int userId, String today,int usertypeId);
	int getFollowupUpdatedCountInDateRange(int userId, String startDate, String endDate,int usertypeId);
	List<UsersListResponseDTO> getUsersListForProspets(int userId);
	

}
