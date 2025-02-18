package com.nexiilabs.stp.prospect;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.nexiilabs.stp.user.UsersListResponseDTO;

@Transactional
@Repository
public class FollowupReportsRepositoryImpl implements FollowupReportsRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	Environment environment;
	
	@Override
	public int todayFollowupNotUpdatedCount(int userId, String localDate,int updatedType,int userTypeId) {
		List<BigInteger> todayCount =null;
		try{
			String hql =null;
			if(userTypeId==-2){
				hql = "select count(sf.followup_id) as todaycount from stp_prospect_followup sf,stp_prospect sp," + 
					"stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and " + 
					"sp.fk_company_id=sc.company_id and sp.created_by in(SELECT user_id FROM stp_user WHERE " + 
					"reporting_hierarchy like '%,"+userId+",%' or reporting_hierarchy like '"+userId+",%' or reporting_hierarchy like '%,"+userId+"' or " + 
					"reporting_hierarchy like '"+userId+"' and delete_status=0) and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id" + 
					" and sf.followup_status="+updatedType+"  and sf.next_followup LIKE '" + localDate + "%'" + 
					" and sf.delete_status=0";
			}else if(userTypeId==-1){
				hql = "select count(sf.followup_id) as todaycount from stp_prospect_followup sf,stp_prospect sp," + 
						"stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and " + 
						"sp.fk_company_id=sc.company_id and sp.created_by="+userId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id" + 
						" and sf.followup_status="+updatedType+"  and sf.next_followup LIKE '" + localDate + "%'" + 
						" and sf.delete_status=0";
			}else{
				hql = "select count(sf.followup_id) as todaycount from stp_prospect_followup sf,stp_prospect sp," + 
						"stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and " + 
						"sp.fk_company_id=sc.company_id and sp.created_by="+userTypeId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id" + 
						" and sf.followup_status="+updatedType+"  and sf.next_followup LIKE '" + localDate + "%'" + 
						" and sf.delete_status=0";
			}
			todayCount = entityManager.createNativeQuery(hql).getResultList();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return todayCount.get(0).intValue();
	}

	@Override
	public int getFollowupNotUpdatedCountInDateRange(int userId, String startDate, String endDate, int updatedType,int usertypeId) {
		List<BigInteger> todayCount =null;
		try{
			String hql =null;
			if(usertypeId==-2){
				hql = "select count(sf.followup_id) as weeklycount from stp_prospect_followup sf,stp_prospect sp," + 
					"		stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and " + 
					"		sp.fk_company_id=sc.company_id and sp.created_by in(SELECT user_id FROM stp_user WHERE " + 
					"		reporting_hierarchy like '%,"+userId+",%' or reporting_hierarchy like '"+userId+",%' or reporting_hierarchy like '%,"+userId+"' or " + 
					"		reporting_hierarchy like '"+userId+"' and delete_status=0) and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id" + 
					"		and sf.followup_status="+updatedType+" and CAST(sf.next_followup AS DATE) between '"+startDate+"' and '"+endDate+"' " + 
					"		and sf.delete_status=0";
			}else if(usertypeId==-1){
				hql = "select count(sf.followup_id) as weeklycount from stp_prospect_followup sf,stp_prospect sp," + 
						"		stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and " + 
						"		sp.fk_company_id=sc.company_id and sp.created_by="+userId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id" + 
						"		and sf.followup_status="+updatedType+" and CAST(sf.next_followup AS DATE) between '"+startDate+"' and '"+endDate+"' " + 
						"		and sf.delete_status=0";
			}else{
				hql = "select count(sf.followup_id) as weeklycount from stp_prospect_followup sf,stp_prospect sp," + 
						"		stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and " + 
						"		sp.fk_company_id=sc.company_id and sp.created_by="+usertypeId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id" + 
						"		and sf.followup_status="+updatedType+" and CAST(sf.next_followup AS DATE) between '"+startDate+"' and '"+endDate+"' " + 
						"		and sf.delete_status=0";
			}
			todayCount = entityManager.createNativeQuery(hql).getResultList();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return todayCount.get(0).intValue();
	}
	
	@Override
	public List<FollowupProspectListResponseDTO> todayNotUpdatedFollowupReportsList(int userId,String toDay,int updateStatus,int userTypeId) {
		
		List<FollowupProspectListResponseDTO> followupListResponseDTOList=new ArrayList<FollowupProspectListResponseDTO>();
		FollowupProspectListResponseDTO followupListResponseDTO=new FollowupProspectListResponseDTO();
		try{
			
			String hql =null;
			if(userTypeId==-2){
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information "
					+ ",u.user_name from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m,stp_user u where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
					+ " sp.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%," + userId
					+ ",%' or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%," + userId
					+ "' or reporting_hierarchy like '" + userId + "' and delete_status=0) and  sp.created_by=u.user_id and "
					+ " sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and sf.followup_status="+updateStatus+" and sf.next_followup LIKE '" + toDay + "%' and sf.delete_status=0";
			}else if(userTypeId==-1){
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information "
						+ ",u.user_name from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m,stp_user u where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
						+ " sp.created_by=u.user_id and  sp.created_by="+userId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and sf.followup_status="+updateStatus+" and sf.next_followup LIKE '" + toDay + "%' and sf.delete_status=0";
				
			}else{
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information "
						+ ",u.user_name from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m ,stp_user u where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
						+ "  sp.created_by=u.user_id and  sp.created_by="+userTypeId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and sf.followup_status="+updateStatus+" and sf.next_followup LIKE '" + toDay + "%' and sf.delete_status=0";
				
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
					followupListResponseDTO.setCreatedBy(String.valueOf(obj[22]));
					followupListResponseDTOList.add(followupListResponseDTO);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return followupListResponseDTOList;
	}

	@Override
	public List<FollowupProspectListResponseDTO> followupNotUpdatedReportsList(int userId, String startDate, String endDate,int updatedType,int userTypeId) {
		List<FollowupProspectListResponseDTO> followupListResponseDTOList=new ArrayList<FollowupProspectListResponseDTO>();
		FollowupProspectListResponseDTO followupListResponseDTO=new FollowupProspectListResponseDTO();
		try{
			
			String hql =null;
			if(userTypeId==-2){
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,"
					+ "sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,"
					+ "sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,"
					+ "sp.designation,sp.additional_information,u.user_name from stp_prospect_followup sf,stp_prospect sp," + 
					"stp_prospect_company  sc,stp_stage s,stp_meeting_type m ,stp_user u where sf.fk_prospect_id= sp.prospect_id and " + 
					"sp.fk_company_id=sc.company_id and sp.created_by in(SELECT user_id FROM stp_user WHERE " + 
					"reporting_hierarchy like '%,"+userId+",%' or reporting_hierarchy like '"+userId+",%' or reporting_hierarchy like '%,"+userId+"' or " + 
					"reporting_hierarchy like '"+userId+"' and delete_status=0) and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id " + 
					"and sf.followup_status="+updatedType+" and CAST(sf.next_followup AS DATE) between '"+startDate+"' and '"+endDate+"' " + 
					"and sf.delete_status=0 and  sp.created_by=u.user_id";
			}else if(userTypeId==-1){
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,"
						+ "sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,"
						+ "sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,"
						+ "sp.designation,sp.additional_information,u.user_name from stp_prospect_followup sf,stp_prospect sp," + 
						"stp_prospect_company  sc,stp_stage s,stp_meeting_type m,stp_user u where sf.fk_prospect_id= sp.prospect_id and " + 
						"sp.fk_company_id=sc.company_id and sp.created_by="+userId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id " + 
						"and sf.followup_status="+updatedType+" and CAST(sf.next_followup AS DATE) between '"+startDate+"' and '"+endDate+"' " + 
						"and sf.delete_status=0 and sp.created_by=u.user_id";
			}else{
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,"
						+ "sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,"
						+ "sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,"
						+ "sp.designation,sp.additional_information,u.user_name from stp_prospect_followup sf,stp_prospect sp," + 
						"stp_prospect_company  sc,stp_stage s,stp_meeting_type m,stp_user u where sf.fk_prospect_id= sp.prospect_id and " + 
						"sp.fk_company_id=sc.company_id and sp.created_by="+userTypeId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id " + 
						"and sf.followup_status="+updatedType+" and CAST(sf.next_followup AS DATE) between '"+startDate+"' and '"+endDate+"' " + 
						"and sf.delete_status=0 and sp.created_by=u.user_id";
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
					followupListResponseDTO.setCreatedBy(String.valueOf(obj[22]));
					followupListResponseDTOList.add(followupListResponseDTO);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return followupListResponseDTOList;
	}

	@Override
	public List<UsersListResponseDTO> getUsersListForProspets(int userId) {
		List<UsersListResponseDTO> testexelist = new ArrayList<UsersListResponseDTO>();
		String hql = " SELECT u.user_id,u.user_name,u.user_email,group_concat(p.permission_name) as permission FROM stp_user u,stp_permissions p,stp_role r"
				+ " where r.role_id=u.fk_role_id and FIND_IN_SET(p.permission_id, r.fk_permission_id) and(u.reporting_hierarchy LIKE '%," + userId
				+ ",%' or u.reporting_hierarchy LIKE '" + userId + ",%' " + "or  u.reporting_hierarchy LIKE '" + userId
				+ "' or u.reporting_hierarchy LIKE '%," + userId + "')"
				+ "and u.user_id!="+userId+" and u.delete_status='0' and u.fk_role_id!=" + environment.getProperty("app.resourceRoleId")
				+" group by u.user_id having  permission  LIKE '%,Reports,%' or permission  LIKE 'Reports,%' or permission  LIKE 'Reports' or permission  LIKE '%,Reports'";
		List<Object> userlist = entityManager.createNativeQuery(hql).getResultList();
		if (userlist.size() > 0) {
			Iterator itr = userlist.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				Integer user_id = Integer.parseInt(String.valueOf(obj[0]));
				UsersListResponseDTO usermodel = new UsersListResponseDTO();
				usermodel.setUserId(user_id);
				usermodel.setFirstName(String.valueOf(obj[1]));
				usermodel.setEmail(String.valueOf(obj[2]));
				testexelist.add(usermodel);
			}
		}
		return testexelist;
	}

	@Override
	public List<FollowupProspectListResponseDTO> todayFollowupUpdatedReportsList(int userId, String toDay,
			 int userTypeId) {
		List<FollowupProspectListResponseDTO> followupListResponseDTOList=new ArrayList<FollowupProspectListResponseDTO>();
		FollowupProspectListResponseDTO followupListResponseDTO=new FollowupProspectListResponseDTO();
		try{
			
			String hql =null;
			if(userTypeId==-2){
				hql = "select distinct sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information "
					+ ",u.user_name from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m,stp_user u where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
					+ " sp.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%," + userId
					+ ",%' or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%," + userId
					+ "' or reporting_hierarchy like '" + userId + "' and delete_status=0) and  sp.created_by=u.user_id and "
					+ " sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id  and sf.created_on  LIKE'" + toDay + "%'  and sf.delete_status=0";
			}else if(userTypeId==-1){
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information "
						+ ",u.user_name from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m,stp_user u where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
						+ " sp.created_by=u.user_id and  sp.created_by="+userId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and  sf.created_on  LIKE'" + toDay + "%'  and sf.delete_status=0";
				
			}else{
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information "
						+ ",u.user_name from stp_prospect_followup sf,stp_prospect sp,stp_prospect_company  sc,stp_stage s,stp_meeting_type m ,stp_user u where sf.fk_prospect_id= sp.prospect_id and sp.fk_company_id=sc.company_id and"
						+ "  sp.created_by=u.user_id and  sp.created_by="+userTypeId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id and  sf.created_on  LIKE'" + toDay + "%' and sf.delete_status=0";
				
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
					followupListResponseDTO.setCreatedBy(String.valueOf(obj[22]));
					followupListResponseDTOList.add(followupListResponseDTO);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return followupListResponseDTOList;
	}

	@Override
	public List<FollowupProspectListResponseDTO> followupUpdatedReportsList(int userId, String startDate,
			String endDate, int userTypeId) {
		List<FollowupProspectListResponseDTO> followupListResponseDTOList=new ArrayList<FollowupProspectListResponseDTO>();
		FollowupProspectListResponseDTO followupListResponseDTO=new FollowupProspectListResponseDTO();
		try{
			
			String hql =null;
			if(userTypeId==-2){
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,"
					+ "sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,"
					+ "sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,"
					+ "sp.designation,sp.additional_information,u.user_name from stp_prospect_followup sf,stp_prospect sp," + 
					"stp_prospect_company  sc,stp_stage s,stp_meeting_type m ,stp_user u where sf.fk_prospect_id= sp.prospect_id and " + 
					"sp.fk_company_id=sc.company_id and sp.created_by in(SELECT user_id FROM stp_user WHERE " + 
					"reporting_hierarchy like '%,"+userId+",%' or reporting_hierarchy like '"+userId+",%' or reporting_hierarchy like '%,"+userId+"' or " + 
					"reporting_hierarchy like '"+userId+"' and delete_status=0) and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id " + 
					" and CAST(sf.created_on AS DATE) between '"+startDate+"' and '"+endDate+"'"+
					"and sf.delete_status=0 and  sp.created_by=u.user_id";
			}else if(userTypeId==-1){
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,"
						+ "sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,"
						+ "sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,"
						+ "sp.designation,sp.additional_information,u.user_name from stp_prospect_followup sf,stp_prospect sp," + 
						"stp_prospect_company  sc,stp_stage s,stp_meeting_type m,stp_user u where sf.fk_prospect_id= sp.prospect_id and " + 
						"sp.fk_company_id=sc.company_id and sp.created_by="+userId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id " + 
						"and CAST(sf.created_on AS DATE) between '"+startDate+"' and '"+endDate+"'"+
						"and sf.delete_status=0 and sp.created_by=u.user_id";
			}else{
				hql = "select sf.followup_id,m.meeting_type,s.stage_name,sc.company_name,sp.prospect_name,sf.contacted_on,sf.comments,"
						+ "sp.created_on,sp.email,sf.fk_prospect_id,sf.fk_meeting_id,sp.fk_company_id,sf.fk_stage_id,"
						+ "sf.next_followup,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,"
						+ "sp.designation,sp.additional_information,u.user_name from stp_prospect_followup sf,stp_prospect sp," + 
						"stp_prospect_company  sc,stp_stage s,stp_meeting_type m,stp_user u where sf.fk_prospect_id= sp.prospect_id and " + 
						"sp.fk_company_id=sc.company_id and sp.created_by="+userTypeId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id " + 
						"and CAST(sf.created_on AS DATE) between '"+startDate+"' and '"+endDate+"'"+
						"and sf.delete_status=0 and sp.created_by=u.user_id";
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
					followupListResponseDTO.setCreatedBy(String.valueOf(obj[22]));
					followupListResponseDTOList.add(followupListResponseDTO);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return followupListResponseDTOList;
	}

	@Override
	public int todayFollowupUpdatedCount(int userId, String today, int usertypeId) {
		List<BigInteger> todayCount =null;
		try{
			String hql =null;
			if(usertypeId==-2){
				hql = "select distinct count(sf.followup_id) as todaycount from stp_prospect_followup sf,stp_prospect sp," + 
					"stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and " + 
					"sp.fk_company_id=sc.company_id and sp.created_by in(SELECT user_id FROM stp_user WHERE " + 
					"reporting_hierarchy like '%,"+userId+",%' or reporting_hierarchy like '"+userId+",%' or reporting_hierarchy like '%,"+userId+"' or " + 
					"reporting_hierarchy like '"+userId+"' and delete_status=0) and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id" + 
					" and sf.created_on LIKE '" + today + "%' "+
					" and sf.delete_status=0";
			}else if(usertypeId==-1){
				hql = "select distinct count(sf.followup_id) as todaycount from stp_prospect_followup sf,stp_prospect sp," + 
						"stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and " + 
						"sp.fk_company_id=sc.company_id and sp.created_by="+userId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id" + 
						" and sf.created_on LIKE '" + today + "%' "+
						" and sf.delete_status=0";
			}else{
				hql = "select distinct count(sf.followup_id) as todaycount from stp_prospect_followup sf,stp_prospect sp," + 
						"stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and " + 
						"sp.fk_company_id=sc.company_id and sp.created_by="+usertypeId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id" + 
						" and sf.created_on LIKE '"+today+"%'"+
						" and sf.delete_status=0";
			}
			todayCount = entityManager.createNativeQuery(hql).getResultList();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return todayCount.get(0).intValue();
	}

	@Override
	public int getFollowupUpdatedCountInDateRange(int userId, String startDate, String endDate, int usertypeId) {
		List<BigInteger> todayCount =null;
		try{
			String hql =null;
			if(usertypeId==-2){
				hql = "select distinct count(sf.followup_id) as weeklycount from stp_prospect_followup sf,stp_prospect sp," + 
					"		stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and " + 
					"		sp.fk_company_id=sc.company_id and sp.created_by in(SELECT user_id FROM stp_user WHERE " + 
					"		reporting_hierarchy like '%,"+userId+",%' or reporting_hierarchy like '"+userId+",%' or reporting_hierarchy like '%,"+userId+"' or " + 
					"		reporting_hierarchy like '"+userId+"' and delete_status=0) and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id" + 
					"		and  CAST(sf.created_on AS DATE) between '"+startDate+"' and '"+endDate+"'"+
					"		and sf.delete_status=0";
			}else if(usertypeId==-1){
				hql = "select  distinct count(sf.followup_id) as weeklycount from stp_prospect_followup sf,stp_prospect sp," + 
						"		stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and " + 
						"		sp.fk_company_id=sc.company_id and sp.created_by="+userId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id" + 
						"		and  CAST(sf.created_on AS DATE) between '"+startDate+"' and '"+endDate+"'"+
						"		and sf.delete_status=0";
			}else{
				hql = "select  distinct count(sf.followup_id) as weeklycount from stp_prospect_followup sf,stp_prospect sp," + 
						"		stp_prospect_company  sc,stp_stage s,stp_meeting_type m where sf.fk_prospect_id= sp.prospect_id and " + 
						"		sp.fk_company_id=sc.company_id and sp.created_by="+usertypeId+" and sf.fk_stage_id=s.stage_id and sf.fk_meeting_id=m.meeting_type_id" + 
						"		and CAST(sf.created_on AS DATE) between '"+startDate+"' and '"+endDate+"'"+
						"		and sf.delete_status=0";
			}
			todayCount = entityManager.createNativeQuery(hql).getResultList();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return todayCount.get(0).intValue();
	}


	

}
