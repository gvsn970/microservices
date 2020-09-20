package com.nexiilabs.stp.resource;

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

import com.nexiilabs.stp.authentication.FlasMailConfig;
import com.nexiilabs.stp.user.CreateUserModel;
import com.nexiilabs.stp.user.UserRepositoryImpl;
import com.nexiilabs.stp.user.UserResponseDTO;
import com.nexiilabs.stp.user.UsersListResponseDTO;
import com.nexiilabs.stp.util.MailUtils;
import com.nexiilabs.stp.util.PasswordGenerator;

@Transactional
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	PODetailsRepositoryImpl pODetailsRepositoryImpl;
	@Autowired
	RequirementsDaoImpl requirementsDaoImpl;
	@Autowired
	UserRepositoryImpl userRepositoryImpl;
	@Autowired
	Environment environment;

	@Override
	public UserResponseDTO createResource(EmployeeModel empModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		CreateUserModel createUserModel = new CreateUserModel();
		try {
			UserResponseDTO check = employeeExistencyCheck(empModel.getEmpEmailID());
			if (check.getMessage().equals("Employee Already Exists")) {
				return check;
			} else {
				entityManager.persist(empModel);
				if (empModel.getEmployeeId() != 0) {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setEmployeeId(empModel.getEmployeeId());
					userResponseDTO.setMessage("Employee Created Succesfully");
					createUserModel.setFirstName(empModel.getFirstName());
					createUserModel.setLastName(empModel.getLastName());
					createUserModel.setUserName(empModel.getEmpName());
					createUserModel.setEmail(empModel.getEmpEmailID());
					createUserModel.setRoleId(Integer.parseInt(environment.getProperty("app.resourceRoleId")));
					createUserModel.setReportingTo(empModel.getCreatedBy() + "");
					String password = PasswordGenerator.generateRandomPassword();
					createUserModel.setUserPassword(password);
					entityManager.persist(createUserModel);
					if (createUserModel.getUserId() != 0) {
						FlasMailConfig mailconfig = userRepositoryImpl.getMailDetails(1);
						System.out.println("mailconfig:::" + mailconfig);
						MailUtils.sendUserCredentialsMail(createUserModel, mailconfig);
						String reporting = userRepositoryImpl.getReportIds(empModel.getCreatedBy() + "");
						createUserModel.setReportHierarchy(reporting + "," + createUserModel.getUserId());
						createUserModel.setUserId(createUserModel.getUserId());
						entityManager.persist(createUserModel);
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setMessage("Employee Created Sucessfully");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Employee Creation Failed");
					}

				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Employee Creation Failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage("Employee Creation Failed");
		}
		return userResponseDTO;
	}

	public UserResponseDTO employeeExistencyCheck(String email) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql1 = "select * from stp_employee sc where sc.emp_email_id='" + email + "' and sc.delete_status=0";
			List employeelist = entityManager.createNativeQuery(hql1).getResultList();
			System.out.println("Before existency check::" + employeelist.size());
			if (employeelist.size() > 0) {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Employee Already Exists");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Employee Not Exists");
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO extendEmployeeJoinDate(EmployeeModel empModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql = "update stp_employee se  set se.emp_joining_date='" + empModel.getEmpJoiningDate()
					+ "' where se.employee_id=" + empModel.getEmployeeId();
			int updatecheck = entityManager.createNativeQuery(hql).executeUpdate();
			System.out.println("updatecheck:::" + updatecheck);
			if (updatecheck == 1) {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Employee Join Date updated Succesfully");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Employee Join Date updation Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO updateResource(EmployeeModel empModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			UserResponseDTO changes = changesCheck(empModel);
			if (changes.getStatusCode() == 0) {
				return changes;
			} else {
				String hql = "update stp_employee se set se.emp_fname='" + empModel.getFirstName() + "'"
						+ ",se.emp_lname='" + empModel.getLastName() + "'" + ",se.emp_name='" + empModel.getEmpName()
						+ "'" + ",se.emp_contact='" + empModel.getEmpContact() + "'" + ",se.skill_set='"
						+ empModel.getSkillSet() + "'" + ",se.experience_level='" + empModel.getExperienceLevel() + "'"
						+ ",se.emp_joining_date='" + empModel.getEmpJoiningDate() + "',se.updated_by='"
						+ empModel.getUpdatedBy() + "',se.updated_on=CURRENT_TIMESTAMP  where se.employee_id="
						+ empModel.getEmployeeId();
				int update = entityManager.createNativeQuery(hql).executeUpdate();
				if (update == 1) {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("Employee Updated Succesfully");
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Employee Updation Failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	public UserResponseDTO changesCheck(EmployeeModel empModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql = " select * from stp_employee se where se.emp_fname='" + empModel.getFirstName()
					+ "' and se.emp_lname='" + empModel.getLastName() + "'" + " and se.emp_name='"
					+ empModel.getEmpName() + "' and se.emp_contact='" + empModel.getEmpContact() + "'"
					+ " and se.skill_set='" + empModel.getSkillSet() + "' and se.experience_level='"
					+ empModel.getExperienceLevel() + "'" + " and se.emp_joining_date='" + empModel.getEmpJoiningDate()
					+ "' and se.employee_id=" + empModel.getEmployeeId();
			List changeslist = entityManager.createNativeQuery(hql).getResultList();
			if (changeslist.size() > 0) {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("No changes found to update");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Changes found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	/*
	 * @Transactional public UserResponseDTO deleteResource(EmployeeModel
	 * empModel) { UserResponseDTO userResponseDTO = new UserResponseDTO();
	 * POModel poModel = null; UploadPOModel uploadPOModel = null;
	 * UploadSowModel uploadSowModel = null; RequirementsModel requirementsModel
	 * = null; EntityTransaction transaction =null; try { transaction =
	 * entityManager.getTransaction(); transaction.begin(); String hql =
	 * "update stp_employee se set se.deleted_by='" + empModel.getDeletedBy() +
	 * "',se.delete_status='1',se.deleted_on=CURRENT_TIMESTAMP where se.employee_id="
	 * + empModel.getEmployeeId(); // System.out.println("entityManager::: in //
	 * Emp:::::::"+entityManager); int updatecheck =
	 * entityManager.createNativeQuery(hql).executeUpdate();
	 * System.out.println("updatecheck:::" + updatecheck); if (updatecheck == 1)
	 * { poModel = new POModel(); uploadPOModel = new UploadPOModel();
	 * poModel.setEmployeeId(empModel.getEmployeeId());
	 * poModel.setDeletedBy(empModel.getDeletedBy());
	 * uploadPOModel.setEmployeeId(empModel.getEmployeeId()); String hql1 =
	 * "update stp_po  se set se.delete_status=1,se.deleted_by='" +
	 * poModel.getDeletedBy() +
	 * "',se.deleted_on=CURRENT_TIMESTAMP where se.fk_employee_id=" +
	 * poModel.getEmployeeId(); int deletepo =
	 * entityManager.createNativeQuery(hql1).executeUpdate();
	 * System.out.println("updatecheck:::" + deletepo); if (deletepo == 1) {
	 * String hql2 =
	 * "update stp_po_upload su set su.delete_status=1 where su.fk_employee_id="
	 * + poModel.getEmployeeId(); int deletepoupload =
	 * entityManager.createNativeQuery(hql2).executeUpdate(); if (deletepoupload
	 * == 1) { uploadSowModel = new UploadSowModel();
	 * uploadSowModel.setEmployeeId(empModel.getEmployeeId()); String hql3 =
	 * "update stp_sow_upload  se set se.delete_status=1 where se.fk_employee_id="
	 * + uploadSowModel.getEmployeeId(); int deletesowupload =
	 * entityManager.createNativeQuery(hql3).executeUpdate(); if
	 * (deletesowupload == 1) { requirementsModel = new RequirementsModel();
	 * requirementsModel.setFkEmployeeId(empModel.getEmployeeId());
	 * requirementsModel.setDeleteBy(empModel.getDeletedBy()); String hql4 =
	 * "UPDATE stp_requirement SET deleted_by =" +
	 * requirementsModel.getDeleteBy() +
	 * " ,delete_status =1, deleted_on=now() WHERE fk_employee_id =" +
	 * requirementsModel.getFkEmployeeId(); int deleteReq =
	 * entityManager.createNativeQuery(hql4).executeUpdate(); if (deleteReq ==
	 * 1) { transaction.commit(); userResponseDTO.setStatusCode(1);
	 * userResponseDTO.setMessage("Employee Deleted Successfully"); } else {
	 * transaction.rollback(); userResponseDTO.setStatusCode(0);
	 * userResponseDTO.setMessage("Employee Deletion Failed"); } } else {
	 * transaction.rollback(); userResponseDTO.setStatusCode(0);
	 * userResponseDTO.setMessage("Employee Deletion Failed"); } } else {
	 * transaction.rollback(); userResponseDTO.setStatusCode(0);
	 * userResponseDTO.setMessage("Employee Deletion Failed"); }
	 * 
	 * } else { transaction.rollback(); userResponseDTO.setStatusCode(0);
	 * userResponseDTO.setMessage("Employee Deletion Failed"); } }
	 * 
	 * else { transaction.rollback(); userResponseDTO.setStatusCode(0);
	 * userResponseDTO.setMessage("Employee Deletion Failed"); } } catch
	 * (Exception e) { e.printStackTrace(); userResponseDTO.setStatusCode(0);
	 * userResponseDTO.setMessage(e.getMessage()); } return userResponseDTO; }
	 */
	@Override
	public UserResponseDTO deleteResource(EmployeeModel empModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		POModel poModel = null;
		UploadPOModel uploadPOModel = null;
		UploadSowModel uploadSowModel = null;
		RequirementsModel requirementsModel = null;
		CreateUserModel createUserModel = null;
		try {
			userResponseDTO=checkDeleteResource(empModel.getEmployeeId());
			// entityManager.getTransaction().begin();
			String hql = "update stp_employee se set se.deleted_by='" + empModel.getDeletedBy()
					+ "',se.delete_status='1',se.deleted_on=CURRENT_TIMESTAMP where se.employee_id="
					+ empModel.getEmployeeId();
			// System.out.println("entityManager::: in
			// Emp:::::::"+entityManager);
			int updatecheck = entityManager.createNativeQuery(hql).executeUpdate();
			System.out.println("updatecheck:::" + updatecheck);
			if (updatecheck == 1) {
				poModel = new POModel();
				uploadPOModel = new UploadPOModel();
				poModel.setEmployeeId(empModel.getEmployeeId());
				poModel.setDeletedBy(empModel.getDeletedBy());
				uploadPOModel.setEmployeeId(empModel.getEmployeeId());
				UserResponseDTO poDelete = pODetailsRepositoryImpl.deletePODetails(poModel, uploadPOModel);
				if (poDelete.getStatusCode() == 1) {
					uploadSowModel = new UploadSowModel();
					uploadSowModel.setEmployeeId(empModel.getEmployeeId());
					UserResponseDTO sowDelete = pODetailsRepositoryImpl.deleteSow(uploadSowModel);
					if (sowDelete.getStatusCode() == 1) {
						createUserModel = new CreateUserModel();
						createUserModel.setDeletedBy(empModel.getDeletedBy());
						createUserModel.setEmail(empModel.getEmpEmailID());
						System.err.println("email:::::::::::" + empModel.getEmpEmailID());
						String deleteUser = "update stp_user fs  set fs.delete_status=1,fs.deleted_on=CURRENT_TIMESTAMP,fs.deleted_by="
								+ createUserModel.getDeletedBy() + " where fs.user_email='" + createUserModel.getEmail()
								+ "'";
						int deleteUsercheck = entityManager.createNativeQuery(deleteUser).executeUpdate();
						if (deleteUsercheck >0) {
							requirementsModel = new RequirementsModel();
							requirementsModel.setFkEmployeeId(empModel.getEmployeeId());
							requirementsModel.setDeleteBy(empModel.getDeletedBy());
							requirementsDaoImpl.deleteRequirements(requirementsModel);
							userResponseDTO.setStatusCode(1);
							userResponseDTO.setMessage("Employee Deleted Succesfully");
						} else {
							userResponseDTO.setStatusCode(0);
							userResponseDTO.setMessage("Employee Deletion Failed");
						}
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Employee Deletion Failed");
					}
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Employee Deletion Failed");
				}

			} else {
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

	@Override
	public List<EmployeeListResponseDTO> getActiveResourceList(int userId) {
		List<EmployeeListResponseDTO> list = new ArrayList<EmployeeListResponseDTO>();
		try {
			String hql = "select sc.employee_id,sc.emp_name,sc.emp_email_id,sc.emp_contact,"
					+ "sr.project_name,sc.emp_joining_date from stp_employee sc ,"
					+ "stp_requirement sr where sc.employee_id=sr.fk_employee_id "
					+ "and sc.delete_status=0 and sc.po_status=1 and sc.sow_status=1 "
					+ "and sc.req_status=1 and sc.created_by in(SELECT user_id FROM stp_user"
					+ " WHERE reporting_hierarchy like '%," + userId + ",%' or reporting_hierarchy like '" + userId
					+ ",%' or " + "reporting_hierarchy like '%," + userId + "' or reporting_hierarchy like '" + userId
					+ "' and delete_status=0)";
			List<Object> emplist = entityManager.createNativeQuery(hql).getResultList();
			if (emplist.size() > 0) {
				Iterator itr = emplist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					Integer emp_id = Integer.parseInt(String.valueOf(obj[0])); // SERVICE
																				// assumed
																				// as
																				// int
					EmployeeListResponseDTO employeeModel = new EmployeeListResponseDTO();
					employeeModel.setEmployeeId(emp_id);
					employeeModel.setEmployeeName(String.valueOf(obj[1]));
					employeeModel.setEmailID(String.valueOf(obj[2]));
					employeeModel.setContactNumber(String.valueOf(obj[3]));
					employeeModel.setProjectName(String.valueOf(obj[4]));
					employeeModel.setJoiningDate(String.valueOf(obj[5]));
					list.add(employeeModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public UserResponseDTO activateEmployee(EmployeeModel empModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql = "update stp_employee se  set se.fk_po_id='" + empModel.getPoId() + "' where se.employee_id="
					+ empModel.getEmployeeId();
			int updatecheck = entityManager.createNativeQuery(hql).executeUpdate();
			System.out.println("updatecheck:::" + updatecheck);
			if (updatecheck == 1) {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Employee Activated Succesfully");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Employee Activation Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public List<EmployeeListResponseDTO> getInActiveResourceList(int userId) {
		List<EmployeeListResponseDTO> list = new ArrayList<EmployeeListResponseDTO>();
		try {
			String hql = "select sc.employee_id,sc.emp_name,sc.emp_email_id,sc.emp_contact,sr.project_name,sc.emp_joining_date  from stp_employee sc  LEFT JOIN stp_requirement sr "
					+ " ON sr.fk_employee_id=sc.employee_id where (sr.fk_employee_id IS NOT NULL or sr.fk_employee_id IS NULL) and sc.delete_status=0 "
					+ "and (sc.po_status=0 or sc.sow_status=0 or sc.req_status=0) and sc.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy LIKE '%,"
					+ userId + ",%' or reporting_hierarchy like '" + userId + ",%' or " + "reporting_hierarchy like '%,"
					+ userId + "' or reporting_hierarchy like '" + userId + "' and delete_status=0" + ")";
			List<Object> emplist = entityManager.createNativeQuery(hql).getResultList();
			if (emplist.size() > 0) {
				Iterator itr = emplist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					Integer emp_id = Integer.parseInt(String.valueOf(obj[0])); // SERVICE
																				// assumed
																				// as
																				// int
					EmployeeListResponseDTO employeeModel = new EmployeeListResponseDTO();
					employeeModel.setEmployeeId(emp_id);
					employeeModel.setEmployeeName(String.valueOf(obj[1]));
					employeeModel.setEmailID(String.valueOf(obj[2]));
					employeeModel.setContactNumber(String.valueOf(obj[3]));
					employeeModel.setProjectName(String.valueOf(obj[4]));
					employeeModel.setJoiningDate(String.valueOf(obj[5]));
					list.add(employeeModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public EmployeeResponseDTO getEmployeeDetails(Integer empId) {
		EmployeeResponseDTO employeeModel = null;
		try {
			String hql = "select emp.employee_id,emp.emp_fname,emp.emp_lname, emp.emp_email_id,emp.emp_contact,emp.skill_set,"
					+ "emp.experience_level ,emp.emp_joining_date from stp_employee emp where  emp.delete_status=0 and emp.employee_id="
					+ empId;
			List<Object> emplist = entityManager.createNativeQuery(hql).getResultList();
			if (emplist.size() > 0) {
				Iterator itr = emplist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					Integer emp_id = Integer.parseInt(String.valueOf(obj[0])); // SERVICE
																				// assumed
																				// as
																				// int
					employeeModel = new EmployeeResponseDTO();
					employeeModel.setEmployeeId(emp_id);
					employeeModel.setFirstName(String.valueOf(obj[1]));
					employeeModel.setLastName(String.valueOf(obj[2]));
					employeeModel.setEmailID(String.valueOf(obj[3]));
					employeeModel.setContactNumber(String.valueOf(obj[4]));
					employeeModel.setSkillSet(String.valueOf(obj[5]));
					employeeModel.setExperienceLevel(String.valueOf(obj[6]));
					employeeModel.setJoiningDate(String.valueOf(obj[7]));
				}
				return employeeModel;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeModel;
	}

	@Override
	public EmployeeResponseDTO getProjectDetails(Integer empId) {
		EmployeeResponseDTO employeeModel = null;
		try {
			String hql = "select req.project_name,req.project_description,req.hm_name,req.hm_contact,req.hm_email,req.pm_name,req.pm_email,"
					+ "req.pm_contact,req.fk_customer_id,req.location,req.rate_card,c.customer_name from stp_requirement req,stp_customer c where  c.customer_id=req.fk_customer_id and req.delete_status=0 and req.fk_employee_id="
					+ empId;
			List<Object> emplist = entityManager.createNativeQuery(hql).getResultList();
			// System.err.println("emplist:::::::::::"+emplist.size());
			if (emplist.size() > 0) {
				Iterator itr = emplist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					employeeModel = new EmployeeResponseDTO();
					employeeModel.setProjectName(String.valueOf(obj[0]));
					employeeModel.setProjectDesc(String.valueOf(obj[1]));
					employeeModel.setHmName(String.valueOf(obj[2]));
					employeeModel.setHmEmail(String.valueOf(obj[4]));
					employeeModel.setHmContact(String.valueOf(obj[3]));
					employeeModel.setPmName(String.valueOf(obj[5]));
					employeeModel.setPmEmail(String.valueOf(obj[6]));
					employeeModel.setPmContact(String.valueOf(obj[7]));
					employeeModel.setFkCustomerId(Integer.parseInt(String.valueOf(obj[8])));
					employeeModel.setLocation(String.valueOf(obj[9]));
					employeeModel.setRateCard(String.valueOf(obj[10]));
					employeeModel.setCustomerName(String.valueOf(obj[11]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeModel;
	}

	@Override
	public EmployeeResponseDTO getPoDetails(Integer empId) {
		EmployeeResponseDTO employeeModel = null;
		try {
			String hql = "select po.po_number,po.description,po.supplier_ref_number,po.currency,po.raised_by,po.raised_on,po.start_date,po.end_date,po.unit_price,"
					+ "po.duration_months from stp_po po, stp_employee emp where po.delete_status=0 and "
					+ "po.fk_employee_id=" + empId;
			List<Object> emplist = entityManager.createNativeQuery(hql).getResultList();
			if (emplist.size() > 0) {
				Iterator itr = emplist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					employeeModel = new EmployeeResponseDTO();
					employeeModel.setPoNumber(String.valueOf(obj[0]));
					employeeModel.setDescription(String.valueOf(obj[1]));
					employeeModel.setSupplierRefNum(String.valueOf(obj[2]));
					employeeModel.setCurrency(String.valueOf(obj[3]));
					employeeModel.setRaisedBy(String.valueOf(obj[4]));
					employeeModel.setRaisedOn(String.valueOf(obj[5]));
					employeeModel.setStartDate(String.valueOf(obj[6]));
					employeeModel.setEndDate(String.valueOf(obj[7]));
					employeeModel.setUnitPrice(String.valueOf(obj[8]));
					employeeModel.setDuration(String.valueOf(obj[9]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeModel;
	}

	@Override
	public List<FilesListResponseDTO> getSowDetails(Integer empId) {
		FilesListResponseDTO filesListResponseDTO = null;
		List<FilesListResponseDTO> list = new ArrayList<FilesListResponseDTO>();
		try {
			String hql = "select sow.sow_upload_id ,sow.file_name  "
					+ "from stp_sow_upload sow, stp_employee emp where sow.delete_status=0 and emp.employee_id= sow.fk_employee_id and sow.fk_employee_id="
					+ empId;
			List<Object> emplist = entityManager.createNativeQuery(hql).getResultList();
			if (emplist.size() > 0) {
				Iterator itr = emplist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					filesListResponseDTO = new FilesListResponseDTO();
					filesListResponseDTO.setSowFileId(Integer.parseInt(String.valueOf(obj[0])));
					filesListResponseDTO.setSowFileName(String.valueOf(obj[1]));
					list.add(filesListResponseDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<FilesListResponseDTO> getPoUploadDetails(Integer empId) {
		FilesListResponseDTO filesListResponseDTO = null;
		List<FilesListResponseDTO> list = new ArrayList<FilesListResponseDTO>();
		try {
			String hql = "select  poup.po_upload_id,poup.file_name"
					+ " from stp_po_upload poup, stp_employee emp where poup.delete_status=0 and emp.employee_id=poup.fk_employee_id and poup.fk_employee_id="
					+ empId;
			List<Object> emplist = entityManager.createNativeQuery(hql).getResultList();
			if (emplist.size() > 0) {
				Iterator itr = emplist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					filesListResponseDTO = new FilesListResponseDTO();
					filesListResponseDTO.setPoFileId(Integer.parseInt(String.valueOf(obj[0])));
					filesListResponseDTO.setPoFileName(String.valueOf(obj[1]));
					list.add(filesListResponseDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public UserResponseDTO reassignResource(Integer employeeId, String email, Integer newcreatedBy) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql = "update  stp_employee set created_by=" + newcreatedBy + " where employee_id=" + employeeId;
			int updateEmp = entityManager.createNativeQuery(hql).executeUpdate();
			if (updateEmp == 1) {
				String hql1 = "Select user_id from stp_user where user_email='" + email + "'";
				List getUserId = entityManager.createNativeQuery(hql1).getResultList();
				int userId = (int) getUserId.get(0);
				String reporting = userRepositoryImpl.getReportIds(newcreatedBy + "");
				String reporting_hierrachy = reporting + "," + userId;
				String hql2 = "update stp_user set reporting_hierarchy='" + reporting_hierrachy + "',reporting_to='"
						+ newcreatedBy + "' where user_id=" + userId;
				entityManager.createNativeQuery(hql2).executeUpdate();
				userResponseDTO.setMessage("Resource reassigned Successfully");
				userResponseDTO.setStatusCode(1);
			} else {
				userResponseDTO.setMessage("Resource reassignment failed");
				userResponseDTO.setStatusCode(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userResponseDTO;
	}

	@Override
	public List<UsersListResponseDTO> getUserListForReassign() {
		List<UsersListResponseDTO> testexelist = new ArrayList<UsersListResponseDTO>();
		try {
			String hql = "select u.user_id, u.first_name,u.last_name,u.user_email,u.fk_role_id,r.fk_permission_id,group_concat(p.permission_name) as permission   "
					+ "from stp_permissions p ,stp_user u, stp_role r where r.role_id=u.fk_role_id and FIND_IN_SET(p.permission_id, r.fk_permission_id) and u.delete_status=0 "
					+ "GROUP BY u.user_id having  permission  LIKE '%,Resources,%' or permission  LIKE 'Resources,%' or permission  LIKE 'Resources' or permission  LIKE '%,Resources' ";
			List<Object> userlist = entityManager.createNativeQuery(hql).getResultList();
			if (userlist.size() > 0) {
				Iterator itr = userlist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					Integer user_id = Integer.parseInt(String.valueOf(obj[0]));
					UsersListResponseDTO usermodel = new UsersListResponseDTO();
					usermodel.setUserId(user_id);
					usermodel.setFirstName(String.valueOf(obj[1]));
					usermodel.setLastName(String.valueOf(obj[2]));
					usermodel.setEmail(String.valueOf(obj[3]));
					usermodel.setRoleId(Integer.parseInt(String.valueOf(obj[4])));
					testexelist.add(usermodel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testexelist;
	}

	@Override
	public UserResponseDTO checkDeleteResource(Integer employeeId) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
				String hql1 = "SELECT DATEDIFF(po.end_date, now()) as expireIn  FROM stp_employee e,stp_po po where  po.fk_employee_id=e.employee_id and e.employee_id="+employeeId;
				List expiryDays = entityManager.createNativeQuery(hql1).getResultList();
				BigInteger bigInteger =(BigInteger) expiryDays.get(0);
				int days=bigInteger.intValue();
				if(days>0){
					userResponseDTO.setMessage("Resource is in contract for"+days+"days");
					userResponseDTO.setExpireIn(days);
					userResponseDTO.setStatusCode(1);
			} else {
				userResponseDTO.setMessage("Resource already expired");
				userResponseDTO.setExpireIn(days);
				userResponseDTO.setStatusCode(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userResponseDTO;
	}

}