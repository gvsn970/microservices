package com.nexiilabs.stp.prospect;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ContactReportsService {

	List<ProspectListResposeDTO> getTodayContactList(int userId, int userTypeId);

	ContactCountResponseDTO getContactCount(int userId, int userTypeId);

	int getFilterdDateContactCount(String startDate, String endDate, int userId, int userTypeId);

	List<ProspectListResposeDTO> getWeeklyContactList(int userId, int userTypeId);

	List<ProspectListResposeDTO> getMonthlyContactList(int userId, int userTypeId);

	List<ProspectListResposeDTO> getFilteredDateContactList(int userId, String startDate, String endDate,
			int userTypeId);

}
