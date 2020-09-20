package com.nexiilabs.stp.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nexiilabs.stp.authentication.FlasMailConfig;
import com.nexiilabs.stp.util.MailUtils;

@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository {
	private static final Logger log = LogManager.getLogger(UserRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	Environment environment;

	@Override
	public UserResponseDTO createUser(CreateUserModel createUserModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			UserResponseDTO check = userExistencyCheck(createUserModel.getEmail(), createUserModel.getEmpNum());
			if (check.getMessage().equals("User Already Exists")) {
				return check;
			} else {
				entityManager.persist(createUserModel);
				int userId = createUserModel.getUserId();
				System.out.println("USERID::" + userId);
				if (createUserModel.getReportHierarchy().equals("0")) {
					createUserModel.setReportingTo("" + userId);
					createUserModel.setReportHierarchy("" + userId);
				} else {
					createUserModel.setReportHierarchy(createUserModel.getReportHierarchy() + ',' + userId);
				}
				createUserModel.setUserId(userId);
				System.out.println("createUserModel::" + createUserModel);
				entityManager.persist(createUserModel);
				FlasMailConfig mailconfig = getMailDetails(1);
				System.out.println("mailconfig:::" + mailconfig);
				MailUtils.sendUserCredentialsMail(createUserModel, mailconfig);
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("User Created Succesfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage("User Creation Failed");
		}
		return userResponseDTO;
	}

	public String getReportIds(String reportTo) {
		String reportee = null;
		try {
			String hql = "select fs.reporting_hierarchy from stp_user fs where fs.user_id=" + reportTo;
			List report = entityManager.createNativeQuery(hql).getResultList();
			if (report.size() > 0) {
				reportee = report.get(0).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportee;
	}

	public UserResponseDTO userExistencyCheck(String email, String empNum) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql1 = "select * from stp_user fs where (fs.user_email='" + email + "' OR fs.user_empnum='" + empNum
					+ "') and fs.delete_status=0";
			List userlist = entityManager.createNativeQuery(hql1).getResultList();
			System.out.println("Before existency check::" + userlist.size());
			if (userlist.size() > 0) {
				// System.out.println("In exitescy check"+userlist.size());
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("User Already Exists");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("User Not Exists");
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	public UserResponseDTO updateUser(CreateUserModel createUserModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			UserResponseDTO changes = changesCheck(createUserModel);
			if (changes.getStatusCode() == 0) {
				return changes;
			} else {
				String hql = "update stp_user fs set fs.first_name='" + createUserModel.getFirstName() + "'"
						+ ",fs.last_name='" + createUserModel.getLastName() + "'" + ",fs.fk_role_id="
						+ createUserModel.getRoleId() + ",fs.reporting_to='" + createUserModel.getReportingTo() + "'"
						+ ",fs.reporting_hierarchy='" + createUserModel.getReportHierarchy() + "'"
						+ ",fs.user_location='" + createUserModel.getUserLocation() + "'" + ",fs.user_name='"
						+ createUserModel.getUserName() + "'" + ",fs.updated_on=CURRENT_TIMESTAMP " + ",fs.updated_by="
						+ createUserModel.getUpdatedBy() + "  where fs.user_id=" + createUserModel.getUserId();
				int update = entityManager.createNativeQuery(hql).executeUpdate();
				if (update == 1) {
					String hql1 = "select user_id,reporting_hierarchy,reporting_to from stp_user  where (reporting_hierarchy LIKE '%,"
							+ createUserModel.getUserId() + ",%' and user_id!='" + createUserModel.getUserId()
							+ "' and delete_status='0'" + " or reporting_hierarchy LIKE '" + createUserModel.getUserId()
							+ ",%' and user_id!='" + createUserModel.getUserId()
							+ "'and delete_status='0' or reporting_hierarchy LIKE '%," + createUserModel.getUserId()
							+ "'and user_id!='" + createUserModel.getUserId() + "'and delete_status='0'"
							+ " or reporting_hierarchy LIKE '" + createUserModel.getUserId() + "' and user_id!='"
							+ createUserModel.getUserId() + "' and delete_status='0') ORDER BY reporting_to";
					List<Object[]> updatehierrachy = entityManager.createNativeQuery(hql1).getResultList();
					for (Object[] obj : updatehierrachy) {
						int userId = Integer.parseInt(String.valueOf(obj[0]));
						String reportingHierarchy = String.valueOf(obj[1]);
						String reporting_to = String.valueOf(obj[2]);
						String[] ids = reportingHierarchy.split("\\,");
						for (String id : ids) {
							if (Integer.parseInt(id) == (createUserModel.getUserId())) {
								String hql2 = "select reporting_hierarchy from stp_user where user_id=" + reporting_to;
								List getHierarchy = entityManager.createNativeQuery(hql2).getResultList();
								reportingHierarchy = getHierarchy.get(0)+ "," +userId;
							}
						}
						//System.err.println("reportingHierarchy::" + reportingHierarchy);
						String hql3 = "update stp_user set reporting_hierarchy='" + reportingHierarchy
								+ "' where user_id=" + userId;
						int updateUsers = entityManager.createNativeQuery(hql3).executeUpdate();
					}
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("User Updated Succesfully");
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("User Updation Failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	public UserResponseDTO changesCheck(CreateUserModel createUserModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			//System.out.println("IN DAO::::" + createUserModel.getUserId());
			String hql = "select * from stp_user fs where fs.first_name='" + createUserModel.getFirstName() + "'"
					+ " and fs.last_name='" + createUserModel.getLastName() + "'" + " and fs.fk_role_id="
					+ createUserModel.getRoleId() + " and fs.reporting_to='" + createUserModel.getReportingTo() + "'"
					+ " and fs.reporting_hierarchy='" + createUserModel.getReportHierarchy() + "'"
					+ " and fs.user_location='" + createUserModel.getUserLocation() + "'" + " and fs.user_name='"
					+ createUserModel.getUserName() + "'" + "   and fs.user_id=" + createUserModel.getUserId();
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

	public UserResponseDTO deleteUser(CreateUserModel createUserModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			int userId = createUserModel.getUserId();
			UserResponseDTO status = checkDeleteUser(userId);
			System.out.println("Status:::" + status.toString());
			if (status.getStatusCode() == 0) {
				//System.out.println("STATUS CODE" + status);
				return status;
			} else {
				String hql1 = "update stp_user fs  set fs.delete_status=1,fs.deleted_on=CURRENT_TIMESTAMP,fs.deleted_by="
						+ createUserModel.getDeletedBy() + " where fs.user_id=" + userId;
				int updatecheck = entityManager.createNativeQuery(hql1).executeUpdate();
				//System.out.println("updatecheck:::" + updatecheck);
				if (updatecheck == 1) {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("User Deleted Succesfully");
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("User Deletion Failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	public UserResponseDTO checkDeleteUser(int userId) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql = "select * from stp_user fs where fs.reporting_hierarchy LIKE '%," + userId
					+ ",%' and fs.user_id!='" + userId + "' and fs.delete_status='0'"
					+ " or fs.reporting_hierarchy LIKE '" + userId + ",%' and fs.user_id!='" + userId
					+ "'and fs.delete_status='0' or fs.reporting_hierarchy LIKE '%," + userId + "' and fs.user_id!='"
					+ userId + "'and fs.delete_status='0'" + " or fs.reporting_hierarchy LIKE '" + userId
					+ "'and fs.user_id!='" + userId + "'and fs.delete_status='0'";
			List userlist = entityManager.createNativeQuery(hql).getResultList();
			System.out.println("Before user existency check::" + userlist.size());
			if (userlist.size() > 0) {
				System.out.println("In exitescy check" + userlist.size());
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("User Deletion is Not possible because user has reportees");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("User Deleted Successfully");
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	public FlasMailConfig getMailDetails(int configId) {
		FlasMailConfig flasMailConfig = null;
		try {
			flasMailConfig = entityManager.find(FlasMailConfig.class, configId);
			return flasMailConfig;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flasMailConfig;
	}

	public List<UsersListResponseDTO> getUsersList(int userId) {
		List<UsersListResponseDTO> testexelist = new ArrayList<UsersListResponseDTO>();
		String hql = " SELECT u.user_id,u.first_name,u.last_name,u.user_email,u.user_empnum,"
				+ "r.role_name,CONCAT(u2.first_name,' ',u2.last_name,'-','(',u2.user_email,')') as"
				+ "reportingName ,u.user_location,u.fk_role_id,u.reporting_to FROM stp_user u,stp_role r,stp_user u2"
				+ " where (u.created_by=" + userId + " or u.reporting_hierarchy LIKE '%," + userId
				+ ",%' or u.reporting_hierarchy LIKE '" + userId + ",%' " + "or  u.reporting_hierarchy LIKE '" + userId
				+ "' or u.reporting_hierarchy LIKE '%," + userId + "')"
				+ "and u.delete_status='0' and u2.user_id=u.reporting_to  and r.role_id = u.fk_role_id "
				+ "and u.fk_role_id!=" + environment.getProperty("app.resourceRoleId");
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
				usermodel.setEmpNum(String.valueOf(obj[4]));
				usermodel.setRoleName(String.valueOf(obj[5]));
				usermodel.setReportTo(String.valueOf(obj[6]));
				usermodel.setLocation(String.valueOf(obj[7]));
				usermodel.setRoleId(Integer.parseInt(String.valueOf(obj[8])));
				usermodel.setReportingId(String.valueOf(obj[9]));
				testexelist.add(usermodel);
			}
		}
		//System.err.println("USERS LIST::::" + testexelist);
		return testexelist;
	}

	@Override
	public List<UsersListResponseDTO> getUsersListForReporting() {
		List<UsersListResponseDTO> testexelist = new ArrayList<UsersListResponseDTO>();
		String hql = " SELECT user_id,first_name,last_name FROM stp_user  where delete_status=0 and fk_role_id!="
				+ environment.getProperty("app.resourceRoleId");
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
				testexelist.add(usermodel);
			}
		}
		//System.err.println("USERS LIST::::" + testexelist);
		return testexelist;
	}
}
