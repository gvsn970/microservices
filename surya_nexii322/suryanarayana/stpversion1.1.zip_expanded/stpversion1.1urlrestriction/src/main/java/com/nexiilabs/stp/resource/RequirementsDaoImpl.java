package com.nexiilabs.stp.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class RequirementsDaoImpl implements RequirementsDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean saveRequirements(RequirementsModel requirementsModel) {
		try {
			entityManager.persist(requirementsModel);
			return updateRequirementStatus(requirementsModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateRequirementStatus(RequirementsModel requirementsModel) {
		try {
				String hql = "UPDATE stp_employee SET req_status = 1 WHERE employee_id =" + requirementsModel.getFkEmployeeId();
				int update = entityManager.createNativeQuery(hql).executeUpdate();
				if (update > 0) {
					return true;
				}else{
					return false;
				}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateRequirements(RequirementsModel requirementsModel) {
		try {
			String empcheck="select * from stp_requirement  WHERE fk_employee_id =" + requirementsModel.getFkEmployeeId()+"  and delete_status=0";
			List<Object[]> emp=entityManager.createNativeQuery(empcheck).getResultList();
			if(emp.size()==0)
			{
				requirementsModel.setCreatedBy(requirementsModel.getUpdatedBy());
				return saveRequirements(requirementsModel);
			}
			else
			{
				String hql = "UPDATE stp_requirement SET project_name = '" + requirementsModel.getProjectName()
						+ "',project_description = '" + requirementsModel.getProjectDescription() + "'," + "hm_name = '" + requirementsModel.getHmName()
						+ "',hm_email = '" + requirementsModel.getHmEmail() + "',hm_contact = '"
						+ requirementsModel.getHmContact() + "'," + "pm_name = '" + requirementsModel.getPmName()
						+ "',pm_email = '" + requirementsModel.getPmEmail() + "',pm_contact = '"
						+ requirementsModel.getPmContact() + "'," + "location = '"+requirementsModel.getLocation() 
						+ "',rate_card =" + requirementsModel.getRateCard()  
					    + ",updated_by =" + requirementsModel.getUpdatedBy()
						+ " ,delete_status =0," + "fk_customer_id =" + requirementsModel.getFkCustomerId()
						+ ", updated_on=now() WHERE fk_employee_id =" + requirementsModel.getFkEmployeeId();
				int update = entityManager.createNativeQuery(hql).executeUpdate();
				if (update != 0) {
					return updateRequirementStatus(requirementsModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<RequirementsModel> getRequirementsList() {
		List<RequirementsModel> list = new ArrayList<RequirementsModel>();
		try {
			String hql = "FROM RequirementsModel WHERE deleteStatus=:delete_status";
			Query query = entityManager.createQuery(hql);
			query.setParameter("delete_status", 0);
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean requirementsExistanceCheck(RequirementsModel requirementsModel) {

		String hql = "FROM RequirementsModel WHERE projectName=:projectName and fkEmployeeId=:fkEmployeeId and deleteStatus=:deleteStatus";
		Query query = entityManager.createQuery(hql);
		query.setParameter("projectName", requirementsModel.getProjectName());
		query.setParameter("fkEmployeeId", requirementsModel.getFkEmployeeId());
		query.setParameter("deleteStatus", 0);
		List<RequirementsModel> list = query.getResultList();
		if (list.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean requirementsExistanceCheckOForUpdate(RequirementsModel requirementsModel) {
		String hql = "FROM RequirementsModel WHERE projectName=:projectName and fkEmployeeId=:fkEmployeeId and deleteStatus=:deleteStatus and requirementId!=:requirementId";
		Query query = entityManager.createQuery(hql);
		query.setParameter("projectName", requirementsModel.getProjectName());
		query.setParameter("fkEmployeeId", requirementsModel.getFkEmployeeId());
		query.setParameter("deleteStatus", 0);
		query.setParameter("requirementId", requirementsModel.getRequirementId());
		List<RequirementsModel> list = query.getResultList();
		if (list.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public RequirementsModel getRequirementById(RequirementsModel requirementsModel) {
		String hql = "FROM RequirementsModel WHERE deleteStatus=:deleteStatus and requirementId=:requirementId";
		Query query = entityManager.createQuery(hql);
		query.setParameter("deleteStatus", 0);
		query.setParameter("requirementId", requirementsModel.getRequirementId());
		List<RequirementsModel> list = query.getResultList();
		if (!list.isEmpty()) {

			return requirementsModel = list.get(0);
		}
		return requirementsModel = null;
	}
	@Override
	public boolean deleteRequirements(RequirementsModel requirementsModel) {

		try {
			if (requirementsModel!= null) {
				String empcheck="select * from stp_requirement  WHERE fk_employee_id =" + requirementsModel.getFkEmployeeId()+"  and delete_status=0";
				List<Object[]> emp=entityManager.createNativeQuery(empcheck).getResultList();
				if(emp.size()==0)
				{
					return true;
				}
				else
				{
					String hql = "UPDATE stp_requirement SET deleted_by =" + requirementsModel.getDeleteBy()
							+ " ,delete_status =1, deleted_on=now() WHERE fk_employee_id =" + requirementsModel.getFkEmployeeId();
					int update = entityManager.createNativeQuery(hql).executeUpdate();
					if (update != 0) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
