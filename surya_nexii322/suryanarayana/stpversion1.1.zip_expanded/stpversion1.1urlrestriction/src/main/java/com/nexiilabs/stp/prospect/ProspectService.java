package com.nexiilabs.stp.prospect;

import java.util.List;

import com.nexiilabs.stp.user.UserResponseDTO;

public interface ProspectService {

	UserResponseDTO addContact(ProspectModel prospectModel);

	UserResponseDTO updateContact(ProspectUpdateModel prospectUpdateModel, ProspectModel prospectModel);

	List<ProspectListResposeDTO> getProspectList(int userId,int userTypeId);

	List<FollowupProspectListResponseDTO> getAllFollowupList(int userId,int userTypeId);

	List<FollowupProspectListResponseDTO> getTodayFollowupList(int userId,int userTypeId);

	List<MeetingType> getMeetingTypesList();

	List<StageModel> getStageList();

	UserResponseDTO deleteProspect(ProspectModel prospectModel);

	FollowupProspectListResponseDTO getProspectDetails(Integer prospectId);

	UserResponseDTO addCompany(ProspectCompanyModel prospectCompanyModel);
	List<ProspectCompanyModel> getCompaniesList();

	List<ProspectListResposeDTO> getClosedProspectList(int userId,int userTypeId);

	List<FollowupProspectListResponseDTO> getTrackDetails(Integer prospectId);

	List<ProspectListResposeDTO> getAllProspectList(int userId, int userTypeId);

}
