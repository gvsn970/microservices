package com.nexiilabs.stp.prospect;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.nexiilabs.stp.user.UserResponseDTO;

@Transactional
@Repository
public class ProspectRepositoryImpl implements ProspectRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	Environment environment;

	@Override
	public UserResponseDTO addContact(ProspectModel prospectModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			UserResponseDTO check = prospectExistencyCheck(prospectModel.getEmail());
			if (check.getMessage().equals("Prospect Already Exists") && check.getStatusCode() == 2) {
				return check;
			} else {
				entityManager.persist(prospectModel);
				if (prospectModel.getProspectId() != 0) {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("Contact Added successfully.");
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Contact Addition Failed.");
				}
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	private UserResponseDTO prospectExistencyCheck(String email) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql1 = "select * from stp_prospect sc where sc.email='" + email + "'"
					+ " and sc.delete_status=0 and sc.prospect_status!=2";
			List prospectlist = entityManager.createNativeQuery(hql1).getResultList();
			System.out.println("Before existency check::" + prospectlist.size());
			if (prospectlist.size() > 0) {
				userResponseDTO.setStatusCode(2);
				userResponseDTO.setMessage("Prospect Already Exists");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Prospect Not Exists");
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO updateContact(ProspectUpdateModel prospectUpdateModel, ProspectModel prospectModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			UserResponseDTO changesCheck = noChangesCheckForUpdate(prospectUpdateModel, prospectModel);
			if (changesCheck.getStatusCode() == 3) {
				return changesCheck;
			} else {
				String hql = "update stp_prospect_followup set followup_status=1 , updated_by="
						+ prospectUpdateModel.getCreatedBy() + ",updated_on=now() where followup_status=0 and fk_prospect_id="
						+ prospectUpdateModel.getFkProspectId();
				int updatedRows=entityManager.createNativeQuery(hql).executeUpdate();
				if(updatedRows>0){
					prospectUpdateModel.setInitialStatus(0);
				}else{
					prospectUpdateModel.setInitialStatus(1);
				}
				entityManager.persist(prospectUpdateModel);
				if (prospectUpdateModel.getFollowUpId() != 0) {
					Query hql1 = entityManager.createNativeQuery("update stp_prospect set prospect_name=:prospect_name ,first_name=:firstname,last_name=:lastname,phone_number=:phoneNumber,"
							+ "alternate_number=:alternateNumber,address1=:address1,address2=:address2,"
							+ "designation=:designation,additional_information=:additionalInformation ,email=:email, prospect_status=1,updated_by="
							+ prospectUpdateModel.getCreatedBy() + ",updated_on=now() where prospect_id="
							+ prospectUpdateModel.getFkProspectId());
					hql1.setParameter("prospect_name", prospectModel.getProspectName());
					hql1.setParameter("firstname", prospectModel.getFirstName());
					hql1.setParameter("lastname", prospectModel.getLastName());
					hql1.setParameter("phoneNumber", prospectModel.getPhoneNumber());
					hql1.setParameter("alternateNumber", prospectModel.getAlternateNumber());
					hql1.setParameter("address1", prospectModel.getAddress1());
					hql1.setParameter("address2", prospectModel.getAddress2());
					hql1.setParameter("designation", prospectModel.getDesignation());
					hql1.setParameter("additionalInformation", prospectModel.getAdditionalInformation());
					hql1.setParameter("email", prospectModel.getEmail());
					hql1.executeUpdate();
					if (prospectUpdateModel.getStageId() == 7) {
						String hql2 = "update stp_prospect set prospect_status=2,updated_by="
								+ prospectUpdateModel.getCreatedBy() + ",updated_on=now() where prospect_id="
								+ prospectUpdateModel.getFkProspectId();
						entityManager.createNativeQuery(hql2).executeUpdate();
						String hql3 = "update stp_prospect_followup set followup_status=1,updated_by="
								+ prospectUpdateModel.getCreatedBy() + " where fk_prospect_id="
								+ prospectUpdateModel.getFkProspectId() +" and followup_status=0";
						entityManager.createNativeQuery(hql3).executeUpdate();
					}
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("Prospect Updated successfully.");
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Prospect Updation Failed.");
				}
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public List<ProspectListResposeDTO> getProspectList(int userId,int userTypeId) {
		ProspectListResposeDTO prospectListResposeDTO = null;
		List<ProspectListResposeDTO> list = new ArrayList<ProspectListResposeDTO>();
		try {
			String hql =null;
			if(userTypeId==-2){
				hql= "select sp.prospect_id,sp.prospect_name,sc.company_name,sp.created_on,sp.email,sp.prospect_status,sp.fk_company_id,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information"
					+ " from stp_prospect sp,stp_prospect_company sc where sp.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%,"
					+ userId + ",%' or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%,"
					+ userId + "' or reporting_hierarchy like '" + userId + "' and delete_status=0) and "
					+ "sp.prospect_status!=2 and sp.fk_company_id=sc.company_id and sp.delete_status=0";
			}else if(userTypeId==-1){
				hql= "select sp.prospect_id,sp.prospect_name,sc.company_name,sp.created_on,sp.email,sp.prospect_status,sp.fk_company_id,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information"
						+ " from stp_prospect sp,stp_prospect_company sc where sp.created_by="+userId+" and "
						+ "sp.prospect_status!=2 and sp.fk_company_id=sc.company_id and sp.delete_status=0";
			}else{
				hql= "select sp.prospect_id,sp.prospect_name,sc.company_name,sp.created_on,sp.email,sp.prospect_status,sp.fk_company_id,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information"
						+ " from stp_prospect sp,stp_prospect_company sc where sp.created_by="+userTypeId+" and "
						+ "sp.prospect_status!=2 and sp.fk_company_id=sc.company_id and sp.delete_status=0";
			}
			List<Object> prospectlist = entityManager.createNativeQuery(hql).getResultList();
			if (prospectlist.size() > 0) {
				Iterator itr = prospectlist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					prospectListResposeDTO = new ProspectListResposeDTO();
					prospectListResposeDTO.setProspectId(Integer.parseInt(String.valueOf(obj[0])));
					prospectListResposeDTO.setProspectName(String.valueOf(obj[1]));
					prospectListResposeDTO.setCompanyName(String.valueOf(obj[2]));
					prospectListResposeDTO.setCreatedOn(String.valueOf(obj[3]));
					prospectListResposeDTO.setEmail(String.valueOf(obj[4]));
					prospectListResposeDTO.setProspectStatus(Integer.parseInt(String.valueOf(obj[5])));
					prospectListResposeDTO.setFkCompanyId(Integer.parseInt(String.valueOf(obj[6])));
					prospectListResposeDTO.setFirstName(String.valueOf(obj[7]));
					prospectListResposeDTO.setLastName(String.valueOf(obj[8]));
					prospectListResposeDTO.setAddress1(String.valueOf(obj[9]));
					prospectListResposeDTO.setAddress2(String.valueOf(obj[10]));
					prospectListResposeDTO.setPhoneNumber(String.valueOf(obj[11]));
					prospectListResposeDTO.setAlternateNumber(String.valueOf(obj[12]));
					prospectListResposeDTO.setDesignation(String.valueOf(obj[13]));
					prospectListResposeDTO.setAdditionalInfo(String.valueOf(obj[14]));
					list.add(prospectListResposeDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<FollowupProspectListResponseDTO> getAllFollowupList(int userId,int userTypeId) {
		FollowupProspectListResponseDTO followupListResponseDTO = null;
		List<FollowupProspectListResponseDTO> list = new ArrayList<FollowupProspectListResponseDTO>();
		try {
			String hql = null;
			if(userTypeId==-2){
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information \n"
					+ " from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
					+ " sp.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%," + userId
					+ ",%' or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%," + userId
					+ "' or reporting_hierarchy like '" + userId + "' and delete_status=0) and\n"
					+ " sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and sf.followup_status=0 and sf.delete_status=0 order by sf.created_on desc";
			}else if(userTypeId==-1){
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information \n"
						+ " from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
						+ " sp.created_by="+userId+" and"
						+ " sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and sf.followup_status=0 and sf.delete_status=0 order by sf.created_on desc";
				
			}else{
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information \n"
						+ " from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
						+ " sp.created_by="+userTypeId+" and"
						+ " sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and sf.followup_status=0 and sf.delete_status=0 order by sf.created_on desc";
				
				
			}
			List<Object> followuplist = entityManager.createNativeQuery(hql).getResultList();
			if (followuplist.size() > 0) {
				Iterator itr = followuplist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					followupListResponseDTO = new FollowupProspectListResponseDTO();
					followupListResponseDTO.setFollowupId(Integer.parseInt(String.valueOf(obj[0])));
					followupListResponseDTO.setMeetingType(String.valueOf(obj[1]));
					followupListResponseDTO.setStage(String.valueOf(obj[2]));
					followupListResponseDTO.setCompanyName(String.valueOf(obj[3]));
					followupListResponseDTO.setProspectName(String.valueOf(obj[4]));
					followupListResponseDTO.setContactedOn(String.valueOf(obj[5]));
					followupListResponseDTO.setComments(String.valueOf(obj[6]));
					followupListResponseDTO.setCreatedOn(String.valueOf(obj[7]));
					followupListResponseDTO.setEmail(String.valueOf(obj[8]));
					followupListResponseDTO.setFkProspectId(Integer.parseInt(String.valueOf(obj[9])));
					followupListResponseDTO.setFkmeetingId(Integer.parseInt(String.valueOf(obj[10])));
					followupListResponseDTO.setFkCompanyId(Integer.parseInt(String.valueOf(obj[11])));
					followupListResponseDTO.setFkStageId(Integer.parseInt(String.valueOf(obj[12])));
					followupListResponseDTO.setNextFollowup(String.valueOf(obj[13]));
					followupListResponseDTO.setFirstName(String.valueOf(obj[14]));
					followupListResponseDTO.setLastName(String.valueOf(obj[15]));
					followupListResponseDTO.setAddress1(String.valueOf(obj[16]));
					followupListResponseDTO.setAddress2(String.valueOf(obj[17]));
					followupListResponseDTO.setPhoneNumber(String.valueOf(obj[18]));
					followupListResponseDTO.setAlternateNumber(String.valueOf(obj[19]));
					followupListResponseDTO.setDesignation(String.valueOf(obj[20]));
					followupListResponseDTO.setAdditionalInfo(String.valueOf(obj[21]));
					list.add(followupListResponseDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<FollowupProspectListResponseDTO> getTodayFollowupList(int userId,int userTypeId) {
		FollowupProspectListResponseDTO followupListResponseDTO = null;
		List<FollowupProspectListResponseDTO> list = new ArrayList<FollowupProspectListResponseDTO>();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			String todayDate=dateFormat.format(date);
			String hql = null;
			if(userTypeId==-2){
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information \n"
					+ "from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
					+ " sp.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%," + userId
					+ ",%' or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%," + userId
					+ "' or reporting_hierarchy like '" + userId + "' and delete_status=0) and "
					+ " sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and sf.followup_status=0 and (sf.next_followup<=now() or sf.next_followup LIKE '" + todayDate + "%') and sf.delete_status=0";
			}else if(userTypeId==-1){
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information \n"
						+ "from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
						+ " sp.created_by="+userId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and sf.followup_status=0 and (sf.next_followup<=now() or sf.next_followup LIKE '" + todayDate + "%') and sf.delete_status=0";
				
			}else{
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information \n"
						+ "from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
						+ " sp.created_by="+userTypeId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and sf.followup_status=0 and (sf.next_followup<=now() or sf.next_followup LIKE '" + todayDate + "%') and sf.delete_status=0";
				
			}
			
			List<Object> followuplist = entityManager.createNativeQuery(hql).getResultList();
			if (followuplist.size() > 0) {
				Iterator itr = followuplist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					followupListResponseDTO = new FollowupProspectListResponseDTO();
					followupListResponseDTO.setFollowupId(Integer.parseInt(String.valueOf(obj[0])));
					followupListResponseDTO.setMeetingType(String.valueOf(obj[1]));
					followupListResponseDTO.setStage(String.valueOf(obj[2]));
					followupListResponseDTO.setCompanyName(String.valueOf(obj[3]));
					followupListResponseDTO.setProspectName(String.valueOf(obj[4]));
					followupListResponseDTO.setContactedOn(String.valueOf(obj[5]));
					followupListResponseDTO.setComments(String.valueOf(obj[6]));
					followupListResponseDTO.setCreatedOn(String.valueOf(obj[7]));
					followupListResponseDTO.setEmail(String.valueOf(obj[8]));
					followupListResponseDTO.setFkProspectId(Integer.parseInt(String.valueOf(obj[9])));
					followupListResponseDTO.setFkmeetingId(Integer.parseInt(String.valueOf(obj[10])));
					followupListResponseDTO.setFkCompanyId(Integer.parseInt(String.valueOf(obj[11])));
					followupListResponseDTO.setFkStageId(Integer.parseInt(String.valueOf(obj[12])));
					followupListResponseDTO.setNextFollowup(String.valueOf(obj[13]));
					followupListResponseDTO.setFirstName(String.valueOf(obj[14]));
					followupListResponseDTO.setLastName(String.valueOf(obj[15]));
					followupListResponseDTO.setAddress1(String.valueOf(obj[16]));
					followupListResponseDTO.setAddress2(String.valueOf(obj[17]));
					followupListResponseDTO.setPhoneNumber(String.valueOf(obj[18]));
					followupListResponseDTO.setAlternateNumber(String.valueOf(obj[19]));
					followupListResponseDTO.setDesignation(String.valueOf(obj[20]));
					followupListResponseDTO.setAdditionalInfo(String.valueOf(obj[21]));
					list.add(followupListResponseDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MeetingType> getMeetingTypesList() {
		MeetingType meetingType = null;
		List<MeetingType> list = new ArrayList<MeetingType>();
		try {
			String hql = "select * from stp_meeting_type";
			List<Object> meetingTypeList = entityManager.createNativeQuery(hql).getResultList();
			if (meetingTypeList.size() > 0) {
				Iterator itr = meetingTypeList.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					meetingType = new MeetingType();
					meetingType.setMeetingTypeId(Integer.parseInt(String.valueOf(obj[0])));
					meetingType.setMeetingType(String.valueOf(obj[1]));
					list.add(meetingType);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<StageModel> getStageList() {
		StageModel stageModel = null;
		List<StageModel> list = new ArrayList<StageModel>();
		try {
			String hql = "select * from stp_stage";
			List<Object> stageList = entityManager.createNativeQuery(hql).getResultList();
			if (stageList.size() > 0) {
				Iterator itr = stageList.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					stageModel = new StageModel();
					stageModel.setStageId(Integer.parseInt(String.valueOf(obj[0])));
					stageModel.setStageName(String.valueOf(obj[1]));
					list.add(stageModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public UserResponseDTO deleteProspect(ProspectModel prospectModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql1 = "update stp_prospect set delete_status=1,deleted_by=" + prospectModel.getDeletedBy()
					+ ",deleted_on=now() where prospect_id=" + prospectModel.getProspectId();
			int prospectDelete = entityManager.createNativeQuery(hql1).executeUpdate();
			if (prospectDelete > 0) {
				String hql2 = "update stp_prospect_followup set delete_status=1,deleted_by="
						+ prospectModel.getDeletedBy() + ",deleted_on=now() where fk_prospect_id="
						+ prospectModel.getProspectId();
				entityManager.createNativeQuery(hql2).executeUpdate();
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Prospect Deleted successfully");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Prospect Deletion Failed");
			}

		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public FollowupProspectListResponseDTO getProspectDetails(Integer prospectId) {
		FollowupProspectListResponseDTO followupListResponseDTO = null;
		List<Object> prospectDetails = null;
		try {
			String hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup"
					+ ",sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information"
					+ " from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id"
					+ " and sf.fk_prospect_id=" + prospectId + " and sp.fk_company_id=sc.company_id and "
					+ "sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and "
					+ "sf.followup_status=0 and sf.delete_status=0";
			prospectDetails = entityManager.createNativeQuery(hql).getResultList();
			if (prospectDetails.size() > 0) {
				Iterator itr = prospectDetails.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					followupListResponseDTO = new FollowupProspectListResponseDTO();
					followupListResponseDTO.setFollowupId(Integer.parseInt(String.valueOf(obj[0])));
					followupListResponseDTO.setMeetingType(String.valueOf(obj[1]));
					followupListResponseDTO.setStage(String.valueOf(obj[2]));
					followupListResponseDTO.setCompanyName(String.valueOf(obj[3]));
					followupListResponseDTO.setProspectName(String.valueOf(obj[4]));
					followupListResponseDTO.setContactedOn(String.valueOf(obj[5]));
					followupListResponseDTO.setComments(String.valueOf(obj[6]));
					followupListResponseDTO.setCreatedOn(String.valueOf(obj[7]));
					followupListResponseDTO.setEmail(String.valueOf(obj[8]));
					followupListResponseDTO.setFkProspectId(Integer.parseInt(String.valueOf(obj[9])));
					followupListResponseDTO.setFkmeetingId(Integer.parseInt(String.valueOf(obj[10])));
					followupListResponseDTO.setFkCompanyId(Integer.parseInt(String.valueOf(obj[11])));
					followupListResponseDTO.setFkStageId(Integer.parseInt(String.valueOf(obj[12])));
					followupListResponseDTO.setNextFollowup(String.valueOf(obj[13]));
					followupListResponseDTO.setFirstName(String.valueOf(obj[14]));
					followupListResponseDTO.setLastName(String.valueOf(obj[15]));
					followupListResponseDTO.setAddress1(String.valueOf(obj[16]));
					followupListResponseDTO.setAddress2(String.valueOf(obj[17]));
					followupListResponseDTO.setPhoneNumber(String.valueOf(obj[18]));
					followupListResponseDTO.setAlternateNumber(String.valueOf(obj[19]));
					followupListResponseDTO.setDesignation(String.valueOf(obj[20]));
					followupListResponseDTO.setAdditionalInfo(String.valueOf(obj[21]));
				}
			} else {
				String hql1 = "select sp.prospect_id,sp.prospect_name,sc.company_name,sp.created_on,sp.email,"
						+ "sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information \n"
						+ "from stp_prospect sp,stp_prospect_company  sc where sp.prospect_status!=2 and sp.fk_company_id=sc.company_id\n"
						+ "and sp.delete_status=0 and sp.prospect_id=" + prospectId;
				prospectDetails = entityManager.createNativeQuery(hql1).getResultList();
				if (prospectDetails.size() > 0) {
					Iterator itr = prospectDetails.iterator();
					while (itr.hasNext()) {
						Object[] obj = (Object[]) itr.next();
						followupListResponseDTO = new FollowupProspectListResponseDTO();
						followupListResponseDTO.setFkProspectId(Integer.parseInt(String.valueOf(obj[0])));
						followupListResponseDTO.setProspectName(String.valueOf(obj[1]));
						followupListResponseDTO.setCompanyName(String.valueOf(obj[2]));
						followupListResponseDTO.setCreatedOn(String.valueOf(obj[3]));
						followupListResponseDTO.setEmail(String.valueOf(obj[4]));
						followupListResponseDTO.setFirstName(String.valueOf(obj[5]));
						followupListResponseDTO.setLastName(String.valueOf(obj[6]));
						followupListResponseDTO.setAddress1(String.valueOf(obj[7]));
						followupListResponseDTO.setAddress2(String.valueOf(obj[8]));
						followupListResponseDTO.setPhoneNumber(String.valueOf(obj[9]));
						followupListResponseDTO.setAlternateNumber(String.valueOf(obj[10]));
						followupListResponseDTO.setDesignation(String.valueOf(obj[11]));
						followupListResponseDTO.setAdditionalInfo(String.valueOf(obj[12]));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return followupListResponseDTO;
	}

	@Override
	public UserResponseDTO addCompany(ProspectCompanyModel prospectCompanyModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			UserResponseDTO check = companyExistencyCheck(prospectCompanyModel.getCompanyName(),
					prospectCompanyModel.getCompanyLocation());
			if (check.getMessage().equals("Company Already Exists") && check.getStatusCode() == 2) {
				return check;
			} else {
				entityManager.persist(prospectCompanyModel);
				if (prospectCompanyModel.getCompanyId() != 0) {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("Company Added successfully.");
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Company Addition Failed.");
				}
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	private UserResponseDTO companyExistencyCheck(String companyName, String companyLocation) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql1 = "select * from stp_prospect_company  pc where pc.company_name='" + companyName
					+ "' and pc.company_location='" + companyLocation + "' and pc.delete_status=0";
			List companylist = entityManager.createNativeQuery(hql1).getResultList();
			System.out.println("Before existency check::" + companylist.size());
			if (companylist.size() > 0) {
				userResponseDTO.setStatusCode(2);
				userResponseDTO.setMessage("Company Already Exists");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Company Not Exists");
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public List<ProspectCompanyModel> getCompaniesList() {
		List<ProspectCompanyModel> list = new ArrayList<ProspectCompanyModel>();
		try {
			String hql = "From ProspectCompanyModel where deleteStatus=0 order by companyName";
			Query query = entityManager.createQuery(hql);
			list = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProspectListResposeDTO> getClosedProspectList(int userId,int userTypeId) {
		ProspectListResposeDTO prospectListResposeDTO = null;
		List<ProspectListResposeDTO> list = new ArrayList<ProspectListResposeDTO>();
		try {
			
			String hql =null;
			if(userTypeId==-2){
				hql="select sp.prospect_id,sp.prospect_name,sc.company_name,sp.updated_on,sp.email,sp.prospect_status,sp.fk_company_id,sp.phone_number "
					+ " from stp_prospect sp,stp_prospect_company  sc where sp.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%,"
					+ userId + ",%' or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%,"
					+ userId + "' or reporting_hierarchy like '" + userId + "' and delete_status=0) and "
					+ "sp.prospect_status=2 and sp.fk_company_id=sc.company_id order by  sp.updated_on desc";
			}else if(userTypeId==-1){
				hql="select sp.prospect_id,sp.prospect_name,sc.company_name,sp.updated_on,sp.email,sp.prospect_status,sp.fk_company_id,sp.phone_number "
						+ " from stp_prospect sp,stp_prospect_company  sc where sp.created_by="+userId+" and "
						+ "sp.prospect_status=2 and sp.fk_company_id=sc.company_id order by  sp.updated_on desc";
			}else{
				hql="select sp.prospect_id,sp.prospect_name,sc.company_name,sp.updated_on,sp.email,sp.prospect_status,sp.fk_company_id,sp.phone_number "
						+ " from stp_prospect sp,stp_prospect_company  sc where sp.created_by="+userTypeId+" and "
						+ "sp.prospect_status=2 and sp.fk_company_id=sc.company_id order by  sp.updated_on desc";
			}
			List<Object> prospectlist = entityManager.createNativeQuery(hql).getResultList();
			if (prospectlist.size() > 0) {
				Iterator itr = prospectlist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					prospectListResposeDTO = new ProspectListResposeDTO();
					prospectListResposeDTO.setProspectId(Integer.parseInt(String.valueOf(obj[0])));
					prospectListResposeDTO.setProspectName(String.valueOf(obj[1]));
					prospectListResposeDTO.setCompanyName(String.valueOf(obj[2]));
					prospectListResposeDTO.setCreatedOn(String.valueOf(obj[3]));
					prospectListResposeDTO.setEmail(String.valueOf(obj[4]));
					prospectListResposeDTO.setProspectStatus(Integer.parseInt(String.valueOf(obj[5])));
					prospectListResposeDTO.setFkCompanyId(Integer.parseInt(String.valueOf(obj[6])));
					prospectListResposeDTO.setPhoneNumber(String.valueOf(obj[7]));
					list.add(prospectListResposeDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private UserResponseDTO noChangesCheckForUpdate(ProspectUpdateModel prospectUpdateModel,
			ProspectModel prospectModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			Query hql1 = entityManager.createNativeQuery(
					"select sp.prospect_name,sf.fk_meeting_id, sf.fk_stage_id,sf.next_followup,sf.comments,sf.fk_prospect_id ,sf.contacted_on from stp_prospect sp,stp_prospect_followup sf "
							+ "where sp.prospect_name=:prospect_name and sp.first_name=:firstname and sp.last_name=:lastname and sp.phone_number=:phoneNumber and sp.alternate_number=:alternateNumber"
							+ " and sp.designation=:designation and sp.address1=:address1 and sp.address2=:address2 and sp.additional_information=:additionalInformation "
							+ " and  sf.fk_meeting_id=:fk_meeting_id and sf.next_followup=:next_followup and sf.fk_stage_id=:fk_stage_id"
							+ " and  sf.comments=:comments and sf.contacted_on=:contacted_on and sf.fk_prospect_id=:fk_prospect_id and sp.prospect_id=sf.fk_prospect_id and sp.prospect_status!=2 and sf.followup_status!=1");
			hql1.setParameter("prospect_name", prospectModel.getProspectName());
			hql1.setParameter("firstname", prospectModel.getFirstName());
			hql1.setParameter("lastname", prospectModel.getLastName());
			hql1.setParameter("phoneNumber", prospectModel.getPhoneNumber());
			hql1.setParameter("alternateNumber", prospectModel.getAlternateNumber());
			hql1.setParameter("address1", prospectModel.getAddress1());
			hql1.setParameter("address2", prospectModel.getAddress2());
			hql1.setParameter("designation", prospectModel.getDesignation());
			hql1.setParameter("additionalInformation", prospectModel.getAdditionalInformation());
			hql1.setParameter("fk_meeting_id", prospectUpdateModel.getMeetingTypeId());
			hql1.setParameter("fk_stage_id", prospectUpdateModel.getStageId());
			hql1.setParameter("next_followup", prospectUpdateModel.getNextFollowup());
			hql1.setParameter("comments", prospectUpdateModel.getComments());
			hql1.setParameter("contacted_on", prospectUpdateModel.getContactedOn());
			hql1.setParameter("fk_prospect_id", prospectUpdateModel.getFkProspectId());
			
			List prospectlist = hql1.getResultList();
			if (prospectlist.size() > 0) {
				userResponseDTO.setStatusCode(3);
				userResponseDTO.setMessage("No changes found ");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Changes Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;

	}

	@Override
	public List<FollowupProspectListResponseDTO> getTrackDetails(Integer prospectId) {
		FollowupProspectListResponseDTO followupListResponseDTO = null;
		List<FollowupProspectListResponseDTO> list = new ArrayList<FollowupProspectListResponseDTO>();
		try {
			String hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup \n"
					+ ",sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information"
					+ " from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
					+ " sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and sf.fk_prospect_id="+prospectId+" order by sf.followup_id desc";
			List<Object> followuplist = entityManager.createNativeQuery(hql).getResultList();
			if (followuplist.size() > 0) {
				Iterator itr = followuplist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					followupListResponseDTO = new FollowupProspectListResponseDTO();
					followupListResponseDTO.setFollowupId(Integer.parseInt(String.valueOf(obj[0])));
					followupListResponseDTO.setMeetingType(String.valueOf(obj[1]));
					followupListResponseDTO.setStage(String.valueOf(obj[2]));
					followupListResponseDTO.setCompanyName(String.valueOf(obj[3]));
					followupListResponseDTO.setProspectName(String.valueOf(obj[4]));
					followupListResponseDTO.setContactedOn(String.valueOf(obj[5]));
					followupListResponseDTO.setComments(String.valueOf(obj[6]));
					followupListResponseDTO.setCreatedOn(String.valueOf(obj[7]));
					followupListResponseDTO.setEmail(String.valueOf(obj[8]));
					followupListResponseDTO.setFkProspectId(Integer.parseInt(String.valueOf(obj[9])));
					followupListResponseDTO.setFkmeetingId(Integer.parseInt(String.valueOf(obj[10])));
					followupListResponseDTO.setFkCompanyId(Integer.parseInt(String.valueOf(obj[11])));
					followupListResponseDTO.setFkStageId(Integer.parseInt(String.valueOf(obj[12])));
					followupListResponseDTO.setNextFollowup(String.valueOf(obj[13]));
					followupListResponseDTO.setFirstName(String.valueOf(obj[14]));
					followupListResponseDTO.setLastName(String.valueOf(obj[15]));
					followupListResponseDTO.setAddress1(String.valueOf(obj[16]));
					followupListResponseDTO.setAddress2(String.valueOf(obj[17]));
					followupListResponseDTO.setPhoneNumber(String.valueOf(obj[18]));
					followupListResponseDTO.setAlternateNumber(String.valueOf(obj[19]));
					followupListResponseDTO.setDesignation(String.valueOf(obj[20]));
					followupListResponseDTO.setAdditionalInfo(String.valueOf(obj[21]));
					list.add(followupListResponseDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List getAllProspectList(int userId, int userTypeId) {
		ProspectListResposeDTO prospectListResposeDTO = null;
		FollowupProspectListResponseDTO followupListResponseDTO=null;
		List list = new ArrayList();
		try {
			String hql =null;
			String hql1=null;
			if(userTypeId==-2){
				hql= "select sp.prospect_id,sp.prospect_name,sc.company_name,sp.created_on,sp.email,sp.prospect_status,sp.fk_company_id,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information"
					+ " from stp_prospect sp,stp_prospect_company sc where sp.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%,"
					+ userId + ",%' or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%,"
					+ userId + "' or reporting_hierarchy like '" + userId + "' and delete_status=0) and "
					+ "sp.prospect_status=0 and sp.fk_company_id=sc.company_id and sp.delete_status=0";
				hql1 = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information \n"
						+ " from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
						+ " sp.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%," + userId
						+ ",%' or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%," + userId
						+ "' or reporting_hierarchy like '" + userId + "' and delete_status=0) and\n"
						+ " sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and sf.followup_status=0 and sf.delete_status=0 order by sf.created_on desc";
			}else if(userTypeId==-1){
				hql= "select sp.prospect_id,sp.prospect_name,sc.company_name,sp.created_on,sp.email,sp.prospect_status,sp.fk_company_id,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information"
						+ " from stp_prospect sp,stp_prospect_company sc where sp.created_by="+userId+" and "
						+ "sp.prospect_status=0 and sp.fk_company_id=sc.company_id and sp.delete_status=0";
				hql1="select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information \n"
						+ " from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
						+ " sp.created_by="+userId+" and"
						+ " sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and sf.followup_status=0 and sf.delete_status=0 order by sf.created_on desc";
			}else{
				hql= "select sp.prospect_id,sp.prospect_name,sc.company_name,sp.created_on,sp.email,sp.prospect_status,sp.fk_company_id,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information"
						+ " from stp_prospect sp,stp_prospect_company sc where sp.created_by="+userTypeId+" and "
						+ "sp.prospect_status=0 and sp.fk_company_id=sc.company_id and sp.delete_status=0";
				hql1= "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information \n"
						+ " from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
						+ " sp.created_by="+userTypeId+" and"
						+ " sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and sf.followup_status=0 and sf.delete_status=0 order by sf.created_on desc";
			}
			List<Object> prospectlist = entityManager.createNativeQuery(hql).getResultList();
			List<Object> followuplist = entityManager.createNativeQuery(hql1).getResultList();
			if (prospectlist.size() > 0) {
				Iterator itr = prospectlist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					prospectListResposeDTO = new ProspectListResposeDTO();
					prospectListResposeDTO.setProspectId(Integer.parseInt(String.valueOf(obj[0])));
					prospectListResposeDTO.setProspectName(String.valueOf(obj[1]));
					prospectListResposeDTO.setCompanyName(String.valueOf(obj[2]));
					prospectListResposeDTO.setCreatedOn(String.valueOf(obj[3]));
					prospectListResposeDTO.setEmail(String.valueOf(obj[4]));
					prospectListResposeDTO.setProspectStatus(Integer.parseInt(String.valueOf(obj[5])));
					prospectListResposeDTO.setFkCompanyId(Integer.parseInt(String.valueOf(obj[6])));
					prospectListResposeDTO.setFirstName(String.valueOf(obj[7]));
					prospectListResposeDTO.setLastName(String.valueOf(obj[8]));
					prospectListResposeDTO.setAddress1(String.valueOf(obj[9]));
					prospectListResposeDTO.setAddress2(String.valueOf(obj[10]));
					prospectListResposeDTO.setPhoneNumber(String.valueOf(obj[11]));
					prospectListResposeDTO.setAlternateNumber(String.valueOf(obj[12]));
					prospectListResposeDTO.setDesignation(String.valueOf(obj[13]));
					prospectListResposeDTO.setAdditionalInfo(String.valueOf(obj[14]));
					list.add(prospectListResposeDTO);
				}
			}
			if (followuplist.size() > 0) {
				Iterator itr = followuplist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					followupListResponseDTO = new FollowupProspectListResponseDTO();
					followupListResponseDTO.setFollowupId(Integer.parseInt(String.valueOf(obj[0])));
					followupListResponseDTO.setMeetingType(String.valueOf(obj[1]));
					followupListResponseDTO.setStage(String.valueOf(obj[2]));
					followupListResponseDTO.setCompanyName(String.valueOf(obj[3]));
					followupListResponseDTO.setProspectName(String.valueOf(obj[4]));
					followupListResponseDTO.setContactedOn(String.valueOf(obj[5]));
					followupListResponseDTO.setComments(String.valueOf(obj[6]));
					followupListResponseDTO.setCreatedOn(String.valueOf(obj[7]));
					followupListResponseDTO.setEmail(String.valueOf(obj[8]));
					followupListResponseDTO.setFkProspectId(Integer.parseInt(String.valueOf(obj[9])));
					followupListResponseDTO.setFkmeetingId(Integer.parseInt(String.valueOf(obj[10])));
					followupListResponseDTO.setFkCompanyId(Integer.parseInt(String.valueOf(obj[11])));
					followupListResponseDTO.setFkStageId(Integer.parseInt(String.valueOf(obj[12])));
					followupListResponseDTO.setNextFollowup(String.valueOf(obj[13]));
					followupListResponseDTO.setFirstName(String.valueOf(obj[14]));
					followupListResponseDTO.setLastName(String.valueOf(obj[15]));
					followupListResponseDTO.setAddress1(String.valueOf(obj[16]));
					followupListResponseDTO.setAddress2(String.valueOf(obj[17]));
					followupListResponseDTO.setPhoneNumber(String.valueOf(obj[18]));
					followupListResponseDTO.setAlternateNumber(String.valueOf(obj[19]));
					followupListResponseDTO.setDesignation(String.valueOf(obj[20]));
					followupListResponseDTO.setAdditionalInfo(String.valueOf(obj[21]));
					list.add(followupListResponseDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
