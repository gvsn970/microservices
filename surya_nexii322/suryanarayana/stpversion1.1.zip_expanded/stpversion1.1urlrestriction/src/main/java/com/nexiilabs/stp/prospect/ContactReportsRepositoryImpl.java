package com.nexiilabs.stp.prospect;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class ContactReportsRepositoryImpl implements ContactReportsRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int getContactCount(int userId, int userTypeId) {
		List<BigInteger> todayCount = null;
		try {
			if (userTypeId == -2) {
				Query allCount = entityManager.createNativeQuery("select count(p.prospect_id) from stp_prospect p where"
						+ " p.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%," + userId
						+ ",%' or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%," + userId
						+ "' or reporting_hierarchy like '" + userId + "' and delete_status=0) and"
						+ " p.created_on LIKE '" + LocalDate.now() + " %'");
				todayCount = allCount.getResultList();
			} else {
				Query individualCount = entityManager
						.createNativeQuery("select count(p.prospect_id) from stp_prospect p where p.created_by="
								+ userId + " and p.created_on LIKE'" + LocalDate.now() + " %'");
				todayCount = individualCount.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todayCount.get(0).intValue();
	}

	@Override
	public int getBetweenCount(String startDate, String endDate, int userId, int userTypeId) {
		List<BigInteger> count = null;
		try {
			if (userTypeId == -2) {
				Query allCount = entityManager.createNativeQuery("select count(p.prospect_id) from stp_prospect p where"
						+ " p.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%," + userId
						+ ",%' or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%," + userId
						+ "' or reporting_hierarchy like '" + userId + "' and delete_status=0) and"
						+ " CAST(p.created_on as DATE) between '" + startDate + "' and '" + endDate + "'");
				count = allCount.getResultList();
			} else {
				Query individualCount = entityManager.createNativeQuery(
						"select count(p.prospect_id) from stp_prospect p where " + " p.created_by=" + userId
								+ " and  CAST(p.created_on as DATE) between '" + startDate + "' and '" + endDate + "'");
				count = individualCount.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count.get(0).intValue();
	}

	@Override
	public List<ProspectListResposeDTO> getTodayContactList(int userId, int userTypeId) {
		ProspectListResposeDTO prospectListResposeDTO = null;
		List<ProspectListResposeDTO> list = new ArrayList<ProspectListResposeDTO>();
		List<Object> prospectlist = null;
		try {
			if (userTypeId == -2) {
				String hql1 = "select sp.prospect_id,sp.prospect_name,sc.company_name,sp.created_on,sp.email,sp.prospect_status,sp.fk_company_id,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information,u.user_name"
						+ " from stp_prospect sp,stp_prospect_company sc,stp_user u where sp.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%,"
						+ userId + ",%' or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%,"
						+ userId + "' or reporting_hierarchy like '" + userId + "' and delete_status=0) and sp.created_by=u.user_id and  "
						+ " sp.fk_company_id=sc.company_id and sp.delete_status=0 and sp.created_on LIKE '"
						+ LocalDate.now() + " %'";
				prospectlist = entityManager.createNativeQuery(hql1).getResultList();
			} else {
				String hql2 = "select sp.prospect_id,sp.prospect_name,sc.company_name,sp.created_on,sp.email,sp.prospect_status,sp.fk_company_id,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information,u.user_name"
						+ " from stp_prospect sp,stp_prospect_company sc,stp_user u where sp.created_by=" + userId
						+ " and sp.fk_company_id=sc.company_id and sp.delete_status=0 and sp.created_by=u.user_id and sp.created_on  LIKE '"
						+ LocalDate.now() + " %'";
				prospectlist = entityManager.createNativeQuery(hql2).getResultList();
			}
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
					prospectListResposeDTO.setCreatedBy(String.valueOf(obj[15]));
					list.add(prospectListResposeDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProspectListResposeDTO> getBetweenDatesContactList(String startDate, String endDate, int userId,
			int userTypeId) {
		ProspectListResposeDTO prospectListResposeDTO = null;
		List<Object> prospectlist = null;
		List<ProspectListResposeDTO> list = new ArrayList<ProspectListResposeDTO>();
		try {
			if (userTypeId == -2) {
				String hql1 = "select sp.prospect_id,sp.prospect_name,sc.company_name,sp.created_on,sp.email,sp.prospect_status,sp.fk_company_id,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information"
						+ ",u.user_name from stp_prospect sp,stp_prospect_company sc,stp_user u where sp.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%,"
						+ userId + ",%' or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%,"
						+ userId + "' or reporting_hierarchy like '" + userId + "' and delete_status=0)and sp.created_by=u.user_id and "
						+ " sp.fk_company_id=sc.company_id and sp.delete_status=0 and CAST(sp.created_on as DATE) between '"
						+ startDate + "' and '" + endDate + "'";
				prospectlist = entityManager.createNativeQuery(hql1).getResultList();
			} else {
				String hql2 = "select sp.prospect_id,sp.prospect_name,sc.company_name,sp.created_on,sp.email,sp.prospect_status,sp.fk_company_id,sp.first_name,sp.last_name,sp.address1,sp.address2,sp.phone_number,sp.alternate_number,sp.designation,sp.additional_information"
						+ ",u.user_name from stp_prospect sp,stp_prospect_company sc,stp_user u where sp.created_by=" + userId
						+ " and sp.created_by=u.user_id and sp.fk_company_id=sc.company_id and sp.delete_status=0 and CAST(sp.created_on as DATE) between '"
						+ startDate + "' and '" + endDate + "'";
				prospectlist = entityManager.createNativeQuery(hql2).getResultList();
			}
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
					prospectListResposeDTO.setCreatedBy(String.valueOf(obj[15]));
					list.add(prospectListResposeDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
