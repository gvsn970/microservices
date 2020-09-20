package com.nexiilabs.stp.bench;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nexiilabs.stp.resource.EmployeeModel;
import com.nexiilabs.stp.resource.EmployeeRepositoryImpl;
import com.nexiilabs.stp.resource.PODetailsRepositoryImpl;
import com.nexiilabs.stp.resource.POModel;
import com.nexiilabs.stp.resource.RequirementsDaoImpl;
import com.nexiilabs.stp.resource.RequirementsModel;
import com.nexiilabs.stp.resource.UploadPOModel;
import com.nexiilabs.stp.resource.UploadSowModel;
import com.nexiilabs.stp.user.CreateUserModel;
import com.nexiilabs.stp.user.UserRepositoryImpl;
import com.nexiilabs.stp.user.UserResponseDTO;

@Transactional
@Repository
public class BenchRepositoryImpl implements BenchRepository {
	private static final Logger log = LogManager.getLogger(BenchRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	PODetailsRepositoryImpl pODetailsRepositoryImpl;
	
	@Autowired
	RequirementsDaoImpl requirementsDaoImpl;
	
	@Autowired
	UserRepositoryImpl userRepositoryImpl;
	
	@Autowired
	EmployeeRepositoryImpl employeeRepositoryImpl;

	@Override
	public List<BenchListResponseDTO> getBenchResourcesList(int expireIn,int id) {
		List<BenchListResponseDTO> list = new ArrayList<BenchListResponseDTO>();
		String hql=null;
		if(expireIn==-1){
			hql = "SELECT * FROM ("
				+ " SELECT e.employee_id,e.emp_name,e.emp_email_id,e.experience_level,e.skill_set,req.project_name,po.end_date,DATEDIFF(po.end_date, now()) as expire_in"
				+ " FROM stp_employee e,stp_po po,stp_requirement req"
				+ " WHERE e.delete_status=0 and e.po_status=1 and e.sow_status=1 and e.employee_id=po.fk_employee_id and e.employee_id=req.fk_employee_id and"
				+ " e.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%,"+ id +",%' or reporting_hierarchy like '"+ id
				+ ",%' or reporting_hierarchy like '%,"+ id +"' or reporting_hierarchy like '"+id+"' and delete_status=0)"
				+ ") AS inner_data"
				+ " WHERE inner_data.expire_in<>0 ORDER BY employee_id";
		}else if(expireIn==0){
			hql = "SELECT * FROM ("
					+ " SELECT e.employee_id,e.emp_name,e.emp_email_id,e.experience_level,e.skill_set,req.project_name,po.end_date,DATEDIFF(po.end_date, now()) as expire_in"
					+ " FROM stp_employee e,stp_po po,stp_requirement req"
					+ " WHERE e.delete_status=0 and e.po_status=1 and e.sow_status=1 and e.employee_id=po.fk_employee_id and e.employee_id=req.fk_employee_id and"
					+ " e.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%,"+id+",%' or reporting_hierarchy like '"+id
					+ ",%' or reporting_hierarchy like '%,"+id+"' or reporting_hierarchy like '"+id+"' and delete_status=0)"
					+ ") AS inner_data"
					+ " WHERE inner_data.expire_in<=0 ORDER BY employee_id";
		}else{
			hql = "SELECT * FROM ("
				+ " SELECT e.employee_id,e.emp_name,e.emp_email_id,e.experience_level,e.skill_set,req.project_name,po.end_date,DATEDIFF(po.end_date, now()) as expire_in"
				+ " FROM stp_employee e,stp_po po,stp_requirement req"
				+ " WHERE e.delete_status=0 and e.po_status=1 and e.sow_status=1 and e.employee_id=po.fk_employee_id and e.employee_id=req.fk_employee_id and"
				+ " e.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy like '%,"+id+",%' or reporting_hierarchy like '"+id
				+ ",%' or reporting_hierarchy like '%,"+id+"' or reporting_hierarchy like '"+id+"' and delete_status=0)"
				+ ") AS inner_data"
				+ " WHERE inner_data.expire_in<"+expireIn+" ORDER BY employee_id";
			
		}
		
		List<Object> resourceList = entityManager.createNativeQuery(hql).getResultList();
		if (resourceList.size() > 0) {
			Iterator<Object> itr = resourceList.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				Integer resourceId = Integer.parseInt(String.valueOf(obj[0]));
				BenchListResponseDTO benchListResponseDTO = new BenchListResponseDTO();
				benchListResponseDTO.setResourceId(resourceId);
				benchListResponseDTO.setResourceName(String.valueOf(obj[1]));
				benchListResponseDTO.setResourceEmail(String.valueOf(obj[2]));
				benchListResponseDTO.setResourceExperience(String.valueOf(obj[3]));
				benchListResponseDTO.setResourceSkillSet(String.valueOf(obj[4]));
				benchListResponseDTO.setResourceProjectName(String.valueOf(obj[5]));
				benchListResponseDTO.setResourceExpiryStatus( Integer.parseInt(String.valueOf(obj[7])) <=0 ? "expired" : Integer.parseInt(String.valueOf(obj[7]))+" Days");
				list.add(benchListResponseDTO);
			}
		}
		return list;
	}

	@Override
	public UserResponseDTO expiredResourceReassign(int resourceId, int userId) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		POModel poModel = null;
		UploadPOModel uploadPOModel = null;
		UploadSowModel uploadSowModel = null;
		RequirementsModel requirementsModel=null;
		CreateUserModel createUserModel=null;
		EmployeeModel employeeModel=new EmployeeModel();
		EmployeeModel employeeModel1=new EmployeeModel();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String joiningDate=dateFormat.format(date);
		try {
			// entityManager.getTransaction().begin();
			String hql = "update stp_employee se set se.deleted_by='" + userId
					+ "',se.delete_status='1',se.deleted_on=CURRENT_TIMESTAMP where se.employee_id="
					+ resourceId;
			//System.out.println("entityManager::: in Emp:::::::"+entityManager);
			int updatecheck = entityManager.createNativeQuery(hql).executeUpdate();
			System.out.println("updatecheck:::" + updatecheck);
			if (updatecheck == 1) {
				poModel = new POModel();
				uploadPOModel = new UploadPOModel();
				poModel.setEmployeeId(resourceId);
				poModel.setDeletedBy(userId);
				uploadPOModel.setEmployeeId(resourceId);
				UserResponseDTO poDelete = pODetailsRepositoryImpl.deletePODetails(poModel, uploadPOModel);
				if (poDelete.getStatusCode() == 1) 
				{
					uploadSowModel=new UploadSowModel();
					uploadSowModel.setEmployeeId(resourceId);
					UserResponseDTO sowDelete = pODetailsRepositoryImpl.deleteSow(uploadSowModel);
					if (sowDelete.getStatusCode() == 1) 
					{
						requirementsModel=new RequirementsModel();
						requirementsModel.setFkEmployeeId(resourceId);
						requirementsModel.setDeleteBy(userId);
						boolean status=requirementsDaoImpl.deleteRequirements(requirementsModel);
						if(status){
							employeeModel.setEmployeeId(resourceId);
							employeeModel=entityManager.find(EmployeeModel.class, employeeModel.getEmployeeId());
							if(employeeModel!=null){
								employeeModel1.setFirstName(employeeModel.getFirstName());
								employeeModel1.setLastName(employeeModel.getLastName());
								employeeModel1.setEmpName(employeeModel.getEmpName());
								employeeModel1.setEmpEmailID(employeeModel.getEmpEmailID());
								employeeModel1.setEmpContact(employeeModel.getEmpContact());
								employeeModel1.setSkillSet(employeeModel.getSkillSet());
								employeeModel1.setExperienceLevel(employeeModel.getExperienceLevel());
								employeeModel1.setEmpJoiningDate(joiningDate);
								employeeModel1.setCreatedBy(userId);
								entityManager.persist(employeeModel1);
								if(employeeModel1.getEmployeeId()!=0){
									//System.err.println(employeeModel1.getEmployeeId()+".....employeeModel1.getEmployeeId().."+employeeModel1.getEmpEmailID());
									/*createUserModel=new CreateUserModel();
									String hqls="FROM CreateUserModel WHERE email=:email and delete_status=0";
									Query query = entityManager.createQuery(hqls);
									query.setParameter("email", employeeModel1.getEmpEmailID());
									List<CreateUserModel> list = query.getResultList();
									if(!list.isEmpty()){
										createUserModel=list.get(0);
										createUserModel.setReportingTo(userId+"");
										String reporting=userRepositoryImpl.getReportIds(employeeModel1.getCreatedBy()+"");
										//System.err.println(reporting+".....reporting..");
										createUserModel.setReportHierarchy(reporting+","+createUserModel.getUserId());
										createUserModel.setUserId(createUserModel.getUserId());
										entityManager.persist(createUserModel);
										//System.err.println(createUserModel.getUserId()+"..........createUserModel");
										 * */
									userResponseDTO.setStatusCode(1);
									userResponseDTO.setMessage("Employee Reassigned Successfully");
								}else{
									userResponseDTO.setStatusCode(0);
									userResponseDTO.setMessage("Employee reassignment failed");
								}
								
							}else{
								userResponseDTO.setStatusCode(0);
								userResponseDTO.setMessage("Employee details not found to reassign");
							}
						}else{
							userResponseDTO.setStatusCode(0);
							userResponseDTO.setMessage("Employee Deletion Failed due to requirement not deleted");
						}
						
					}else {
						//entityManager.getTransaction().rollback();
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Employee Deletion Failed");
					}
				}
				else {
					//entityManager.getTransaction().rollback();
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Employee Deletion Failed");
				}

			} else {
				//entityManager.getTransaction().rollback();
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Employee Deletion Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}
}







