package com.nexiilabs.stp.prospect;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexiilabs.stp.user.UserResponseDTO;
@Service
public class ProspectServiceImpl implements ProspectService{

	@Autowired
	ProspectRepository prospectRepository;
	@Override
	public UserResponseDTO addContact(ProspectModel prospectModel) {
		UserResponseDTO userResponseDTO=prospectRepository.addContact(prospectModel);
		return userResponseDTO;
	}
	@Override
	public UserResponseDTO updateContact(ProspectUpdateModel prospectUpdateModel,ProspectModel prospectModel) {
		UserResponseDTO userResponseDTO=prospectRepository.updateContact(prospectUpdateModel,prospectModel);
		return userResponseDTO;
	}
	@Override
	public List<ProspectListResposeDTO> getProspectList(int userId,int userTypeId) {
		return prospectRepository.getProspectList(userId,userTypeId);
	}
	@Override
	public List<FollowupProspectListResponseDTO> getAllFollowupList(int userId,int userTypeId) {
		return prospectRepository.getAllFollowupList(userId,userTypeId);
	}
	@Override
	public List<FollowupProspectListResponseDTO> getTodayFollowupList(int userId,int userTypeId) {
		return prospectRepository.getTodayFollowupList(userId, userTypeId);
	}
	@Override
	public List<MeetingType> getMeetingTypesList() {
		return prospectRepository.getMeetingTypesList();
	}
	@Override
	public List<StageModel> getStageList() {
		return prospectRepository.getStageList();
	}
	@Override
	public UserResponseDTO deleteProspect(ProspectModel prospectModel) {
		UserResponseDTO userResponseDTO=prospectRepository.deleteProspect(prospectModel);
		return userResponseDTO;
	}
	@Override
	public FollowupProspectListResponseDTO getProspectDetails(Integer prospectId) {
		return prospectRepository.getProspectDetails(prospectId);
	}
	@Override
	public UserResponseDTO addCompany(ProspectCompanyModel prospectCompanyModel) {
		return prospectRepository.addCompany(prospectCompanyModel);
	}
	@Override
	public List<ProspectCompanyModel> getCompaniesList() {
		return prospectRepository.getCompaniesList();
	}
	@Override
	public List<ProspectListResposeDTO> getClosedProspectList(int userId,int userTypeId) {
		return prospectRepository.getClosedProspectList(userId,userTypeId);
	}
	@Override
	public List<FollowupProspectListResponseDTO> getTrackDetails(Integer prospectId) {
		return prospectRepository.getTrackDetails(prospectId);
	}
	@Override
	public List  getAllProspectList(int userId, int userTypeId) {
		return prospectRepository.getAllProspectList(userId,userTypeId);
	}

}
