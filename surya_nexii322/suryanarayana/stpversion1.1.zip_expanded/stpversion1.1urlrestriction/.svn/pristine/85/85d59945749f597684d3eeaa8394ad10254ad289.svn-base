package com.nexiilabs.stp.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.nexiilabs.stp.resource.EmployeeModel;
import com.nexiilabs.stp.user.CreateUserModel;

@Transactional
@Repository
public class ProfileManagementDaoImpl implements ProfileManagementDao{
	
	private static final Logger log = LogManager.getLogger(ProfileManagementDaoImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public UserProfileResponseModel getUserprofile(CreateUserModel userModel) {
		log.info("get user profile started");
		List<EmployeeModel> list=new ArrayList<EmployeeModel>();
		UserProfileResponseModel userProfileResponseModel=null;
		try{
			String hql = "FROM EmployeeModel WHERE empEmailID=:empEmailID and deleteStatus=:deleteStatus";
			Query query = entityManager.createQuery(hql);
			query.setParameter("empEmailID", userModel.getEmail());
			query.setParameter("deleteStatus", 0);
			list = query.getResultList();
			if(!list.isEmpty()){
				EmployeeModel employeeModel=new EmployeeModel();
				userProfileResponseModel=new UserProfileResponseModel();
				employeeModel=list.get(0);
				userProfileResponseModel.setEmpId(employeeModel.getEmployeeId());
				userProfileResponseModel.setEmpMailId(userModel.getEmail());
				userProfileResponseModel.setEmpName(employeeModel.getEmpName());
				userProfileResponseModel.setContactNumber(employeeModel.getEmpContact());
				userProfileResponseModel.setSkillSet(employeeModel.getSkillSet());
				userProfileResponseModel.setExperience(employeeModel.getExperienceLevel());
				System.out.println("userProfileResponseModel....."+userProfileResponseModel.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userProfileResponseModel;
	}


	@Override
	public int updateUserprofile(EmployeeModel employeeModel) {
		int update =0;
		log.info("update user profile started");
		try{
			
			if (employeeModel != null) {
				String hql = "UPDATE stp_employee SET emp_name ='" + employeeModel.getEmpName()+ "',emp_contact ='" + employeeModel.getEmpContact()+
						"',experience_level ='" + employeeModel.getExperienceLevel()+"',skill_set ='" + employeeModel.getSkillSet()+ 
						"' WHERE emp_email_id ='" + employeeModel.getEmpEmailID()+"' AND employee_id="+employeeModel.getEmployeeId();
			
			 update = entityManager.createNativeQuery(hql).executeUpdate();
			 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}

}
