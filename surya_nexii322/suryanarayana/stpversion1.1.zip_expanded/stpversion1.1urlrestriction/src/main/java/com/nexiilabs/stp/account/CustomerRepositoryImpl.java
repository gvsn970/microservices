package com.nexiilabs.stp.account;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nexiilabs.stp.user.UserResponseDTO;

@Transactional
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	private static final Logger log = LogManager.getLogger(CustomerRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserResponseDTO createAccount(CreateCustomerModel createCustomerModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			UserResponseDTO check = customerExistencyCheck(createCustomerModel.getCustomerName(),
					createCustomerModel.getCustomerLocation());
			if (check.getMessage().equals("Customer Already Exists")) {
				check.setStatusCode(2);
				return check;
			} else {
				entityManager.persist(createCustomerModel);
				userResponseDTO.setCustomerId(createCustomerModel.getCustomerId());
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Customer Created Succesfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage("Customer Creation Failed");
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO updateAccount(CreateCustomerModel createCustomerModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			UserResponseDTO companyNameCheck = customerNameExistencyCheck(createCustomerModel.getCustomerName(),
					createCustomerModel.getCustomerId(), createCustomerModel.getCustomerLocation());
			// if 1 allow user to update
			if (companyNameCheck.getStatusCode() == 1) {
				UserResponseDTO changesStatus = checkChangesForUpdate(createCustomerModel);
				if (changesStatus.getStatusCode() == 1) {
					// changes found
					String hql = "update stp_customer set customer_name='" + createCustomerModel.getCustomerName() + "'"
							+ ",customer_location='" + createCustomerModel.getCustomerLocation() + "'"
							+ ",contact_number=" + createCustomerModel.getContactNumber() + ",contact_person_name='"
							+ createCustomerModel.getContactPerson() + "'" + ",billing_add_lane1='"
							+ createCustomerModel.getBillingAddressLane1() + "'" + ",billing_add_lane2='"
							+ createCustomerModel.getBillingAddressLane2() + "'" + ",billing_add_pincode='"
							+ createCustomerModel.getBillingAddressPinCode() + "'" + ",billing_add_state='"
							+ createCustomerModel.getBillingAddressState() + "'" + ",ship_add_lane1='"
							+ createCustomerModel.getShippingAddressLane1() + "'" + ",ship_add_lane2='"
							+ createCustomerModel.getShippingAddressLane2() + "'" + ",ship_add_state='"
							+ createCustomerModel.getShippingAddressState() + "'" + ",ship_add_pincode='"
							+ createCustomerModel.getShippingAddressPinCode() + "'" + ",vendor_add_lane1='"
							+ createCustomerModel.getVendorAddressLane1() + "'" + ",vendor_add_lane2='"
							+ createCustomerModel.getVendorAddressLane2() + "'" + ",vendor_add_state='"
							+ createCustomerModel.getVendorAddressState() + "'" + ",vendor_add_pincode='"
							+ createCustomerModel.getVendorAddressPinCode() + "'" + ",state_name='"
							+ createCustomerModel.getState() + "',state_code='" + createCustomerModel.getStateCode()
							+ "'" + ",pan_number='" + createCustomerModel.getPanNumber() + "',gst_in='"
							+ createCustomerModel.getGstIn() + "'" + ",updated_by=" + createCustomerModel.getUpdatedBy()
							+ ",updated_on=CURRENT_TIMESTAMP " + "where customer_id="
							+ createCustomerModel.getCustomerId();
					int update = entityManager.createNativeQuery(hql).executeUpdate();
					if (update == 1) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setCustomerId(createCustomerModel.getCustomerId());
						userResponseDTO.setMessage("Customer Updated Succesfully");
						String hql1 = "update stp_po sp,stp_requirement sr set " + " sp.billing_add_lane1='"
								+ createCustomerModel.getBillingAddressLane1() + "'" + ",sp.billing_add_lane2='"
								+ createCustomerModel.getBillingAddressLane2() + "'" + ",sp.billing_add_pincode='"
								+ createCustomerModel.getBillingAddressPinCode() + "'" + ",sp.billing_add_state='"
								+ createCustomerModel.getBillingAddressState() + "'" + ",sp.ship_add_lane1='"
								+ createCustomerModel.getShippingAddressLane1() + "'" + ",sp.ship_add_lane2='"
								+ createCustomerModel.getShippingAddressLane2() + "'" + ",sp.ship_add_state='"
								+ createCustomerModel.getShippingAddressState() + "'" + ",sp.ship_add_pincode='"
								+ createCustomerModel.getShippingAddressPinCode() + "'" + ",sp.vendor_add_lane1='"
								+ createCustomerModel.getVendorAddressLane1() + "'" + ",sp.vendor_add_lane2='"
								+ createCustomerModel.getVendorAddressLane2() + "'" + ",sp.vendor_add_state='"
								+ createCustomerModel.getVendorAddressState() + "'" + ",sp.vendor_add_pincode='"
								+ createCustomerModel.getVendorAddressPinCode() + "'"+",sp.state_name='"
								+createCustomerModel.getState()+"'"+",sp.pan_number='"+createCustomerModel.getPanNumber()+"'"
								+",sp.state_code='"+createCustomerModel.getStateCode()+"'"+",sp.gst_in='"+createCustomerModel.getGstIn()+"'"
								+",sp.shipping_state_name='"+createCustomerModel.getState()+"'"+",sp.shipping_state_code='"+createCustomerModel.getStateCode()+"'"
								+",sp.shipping_pan_number='"+createCustomerModel.getPanNumber()+"'"+",sp.shipping_gst_in='"+createCustomerModel.getGstIn()+"'"
								+ "where sp.fk_employee_id=sr.fk_employee_id and sr.fk_customer_id="
								+ createCustomerModel.getCustomerId();
						int poupdate = entityManager.createNativeQuery(hql1).executeUpdate();
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Customer Updation Failed");
					}
				} else {
					userResponseDTO.setStatusCode(2);
					userResponseDTO.setMessage("No Changes found");
				}
			} else {
				userResponseDTO.setStatusCode(3);
				userResponseDTO.setMessage("Company Name already exist Please choose different name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	public UserResponseDTO checkChangesForUpdate(CreateCustomerModel createCustomerModel) {
		UserResponseDTO userResponseDTO = null;
		try {
			userResponseDTO = new UserResponseDTO();
			String hql = "select * from stp_customer sc where sc.customer_name='"
					+ createCustomerModel.getCustomerName() + "'" + " and sc.customer_location='"
					+ createCustomerModel.getCustomerLocation() + "'" + " and sc.contact_number="
					+ createCustomerModel.getContactNumber() + " and sc.contact_person_name='"
					+ createCustomerModel.getContactPerson() + "' and sc.customer_id="
					+ createCustomerModel.getCustomerId() + " and sc.billing_add_lane1='"
					+ createCustomerModel.getBillingAddressLane1() + "'" + " and sc.billing_add_lane2='"
					+ createCustomerModel.getBillingAddressLane2() + "'" + " and sc.billing_add_pincode='"
					+ createCustomerModel.getBillingAddressPinCode() + "'" + " and sc.billing_add_state='"
					+ createCustomerModel.getBillingAddressState() + "'" + " and sc.ship_add_lane1='"
					+ createCustomerModel.getShippingAddressLane1() + "'" + " and sc.ship_add_lane2='"
					+ createCustomerModel.getShippingAddressLane2() + "'" + " and sc.ship_add_state='"
					+ createCustomerModel.getShippingAddressState() + "'" + " and sc.ship_add_pincode='"
					+ createCustomerModel.getShippingAddressPinCode() + "'" + " and sc.vendor_add_lane1='"
					+ createCustomerModel.getVendorAddressLane1() + "'" + " and sc.vendor_add_lane2='"
					+ createCustomerModel.getVendorAddressLane2() + "'" + " and sc.vendor_add_state='"
					+ createCustomerModel.getVendorAddressState() + "'" + " and sc.vendor_add_pincode='"
					+ createCustomerModel.getVendorAddressPinCode() + "'" + " and sc.pan_number='"
					+ createCustomerModel.getPanNumber() + "' and sc.gst_in='" + createCustomerModel.getGstIn() + "'"
					+ " and sc.state_name='" + createCustomerModel.getState() + "' and sc.state_code='"
					+ createCustomerModel.getStateCode() + "'";
			List customerlist = entityManager.createNativeQuery(hql).getResultList();
			System.out.println("Before existency check::" + customerlist.size());
			if (customerlist.size() > 0) {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("No changes found to update");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Changes found");
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO deleteAccount(CreateCustomerModel createCustomerModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql2 = "select * from stp_requirement where fk_customer_id=" + createCustomerModel.getCustomerId()
					+ " and delete_status=0";
			List deleteCheck = entityManager.createNativeQuery(hql2).getResultList();
			if (deleteCheck.size() == 0) {
				String hql = "update stp_customer sc  set sc.delete_status='1',sc.deleted_on=CURRENT_TIMESTAMP,sc.deleted_by="
						+ createCustomerModel.getDeletedBy() + " where sc.customer_id="
						+ createCustomerModel.getCustomerId();
				int updatecheck = entityManager.createNativeQuery(hql).executeUpdate();
				System.out.println("updatecheck:::" + updatecheck);
				if (updatecheck == 1) {
					String hql1 = "update stp_customer_upload su set su.delete_status=1,su.deleted_on=CURRENT_TIMESTAMP,su.deleted_by="
							+ createCustomerModel.getDeletedBy() + " where su.fk_customer_id="
							+ createCustomerModel.getCustomerId();
					entityManager.createNativeQuery(hql1).executeUpdate();
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("Customer Deleted Succesfully");
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Customer Deletion Failed");
				}
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Customer Deletion not possible because Resources exist with the Customer");
			}

		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	public UserResponseDTO customerNameExistencyCheck(String customerName, int id, String customerLocation) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql = "select * from stp_customer sc where sc.customer_name='" + customerName
					+ "' and sc.customer_id!='" + id + "' and sc.customer_location='" + customerLocation
					+ "' and sc.delete_status='0'";
			List customerlist = entityManager.createNativeQuery(hql).getResultList();
			System.out.println("Company Names with other Id ::" + customerlist.size());
			if (customerlist.size() > 0) {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("does not allow user to update");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("allow user to update");
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	public UserResponseDTO customerExistencyCheck(String customerName, String customerLocation) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql1 = "select * from stp_customer sc where sc.customer_name='" + customerName
					+ "' and sc.customer_location='" + customerLocation + "' and sc.delete_status=0";
			List customerlist = entityManager.createNativeQuery(hql1).getResultList();
			System.out.println("Before existency check::" + customerlist.size());
			if (customerlist.size() > 0) {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Customer Already Exists");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Customer Not Exists");
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public List<CustomersListResponseDTO> getAccountsList(int userId) {
		List<CustomersListResponseDTO> list = new ArrayList<CustomersListResponseDTO>();
		String hql = "SELECT sc.customer_id,sc.customer_name,sc.customer_location,sc.contact_number,sc.contact_person_name,"
				+ "sc.billing_add_lane1,sc.billing_add_lane2,sc.billing_add_pincode,sc.billing_add_state,sc.ship_add_lane1"
				+ ",sc.ship_add_lane2,sc.ship_add_state,sc.ship_add_pincode,sc.vendor_add_lane1 ,sc.vendor_add_lane2"
				+ ",sc.vendor_add_state,sc.vendor_add_pincode ,sc.pan_number,sc.gst_in,sc.state_name,sc.state_code FROM stp_customer sc "
				+ " where sc.delete_status=0 and sc.created_by in(SELECT user_id FROM stp_user WHERE reporting_hierarchy LIKE '%,"
				+ userId + ",%'" + " or reporting_hierarchy like '" + userId + ",%' or reporting_hierarchy like '%,"
				+ userId + "' or reporting_hierarchy like '" + userId + "' and delete_status=0)";
		List<Object> customerlist = entityManager.createNativeQuery(hql).getResultList();
		if (customerlist.size() > 0) {
			Iterator itr = customerlist.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				Integer customer_id = Integer.parseInt(String.valueOf(obj[0])); // SERVICE
																				// assumed
																				// as
																				// int
				CustomersListResponseDTO customermodel = new CustomersListResponseDTO();
				customermodel.setCustomerId(customer_id);
				;
				customermodel.setCustomerName(String.valueOf(obj[1]));
				customermodel.setLocation(String.valueOf(obj[2]));
				customermodel.setContactNumber(String.valueOf(obj[3]));
				customermodel.setContactPerson(String.valueOf(obj[4]));
				customermodel.setBillingAddressLane1(String.valueOf(obj[5]));
				customermodel.setBillingAddressLane2(String.valueOf(obj[6]));
				customermodel.setBillingAddressPinCode(String.valueOf(obj[7]));
				customermodel.setBillingAddressState(String.valueOf(obj[8]));
				customermodel.setShippingAddressLane1(String.valueOf(obj[9]));
				customermodel.setShippingAddressLane2(String.valueOf(obj[10]));
				customermodel.setShippingAddressState(String.valueOf(obj[11]));
				customermodel.setShippingAddressPinCode(String.valueOf(obj[12]));
				customermodel.setVendorAddressLane1(String.valueOf(obj[13]));
				customermodel.setVendorAddressLane2(String.valueOf(obj[14]));
				customermodel.setVendorAddressState(String.valueOf(obj[15]));
				customermodel.setVendorAddressPinCode(String.valueOf(obj[16]));
				customermodel.setPanNumber(String.valueOf(obj[17]));
				customermodel.setGstIn(String.valueOf(obj[18]));
				customermodel.setState(String.valueOf(obj[19]));
				customermodel.setStateCode(String.valueOf(obj[20]));
				list.add(customermodel);
			}
		}
		return list;
	}

	@Override
	public UserResponseDTO saveUploadedFiles(CustomerFileUpload customerFileUpload) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			System.err.println("customerFileUpload::" + customerFileUpload.toString());
			entityManager.persist(customerFileUpload);
			userResponseDTO.setStatusCode(1);
			userResponseDTO.setMessage("Account Created Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage("Agreement File Info insertion Failed");
		}
		return userResponseDTO;

	}

	@Override
	public UserResponseDTO updateUploadedFiles(CustomerFileUpload customerFileUpload) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			System.err.println("customerFileUpload::" + customerFileUpload.toString());
			entityManager.persist(customerFileUpload);
			if (customerFileUpload.getCustomerUploadId() != 0) {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Customer File update Success");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Customer File update Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO deleteFiles(String fileIds, Integer customerId) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String sql = "update stp_customer_upload su set su.delete_status=1,su.deleted_on=CURRENT_TIMESTAMP where su.customer_upload_id in("
					+ fileIds + ") and fk_customer_id=" + customerId;
			int updatecheck = entityManager.createNativeQuery(sql).executeUpdate();
			if (updatecheck == 1) {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Customer File Delete Success");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Customer File Deletion Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO getFilesofCustomer(CreateCustomerModel createCustomerModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String sql = "select * from stp_customer_upload su where su.fk_customer_id="
					+ createCustomerModel.getCustomerId() + " and su.delete_status=0";
			List updatecheck = entityManager.createNativeQuery(sql).getResultList();
			if (updatecheck.size() > 0) {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setFilesUploadedforCustomer(updatecheck.size());
				userResponseDTO.setMessage("File is uploaded");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Files are not uploaded");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public List<CustomerFileUpload> getAgreementsList(int customerId) {
		List<CustomerFileUpload> list = new ArrayList<CustomerFileUpload>();
		String hql = "SELECT customer_upload_id,file_name FROM stp_customer_upload where delete_status=0 and fk_customer_id="
				+ customerId;
		List<Object> agreementlist = entityManager.createNativeQuery(hql).getResultList();
		if (agreementlist.size() > 0) {
			Iterator<Object> itr = agreementlist.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				CustomerFileUpload customermodel = new CustomerFileUpload();
				Integer customer_upload_id = Integer.parseInt(String.valueOf(obj[0]));
				customermodel.setCustomerUploadId(customer_upload_id);
				customermodel.setCustomerId(customerId);
				customermodel.setFileName(String.valueOf(obj[1]));
				list.add(customermodel);
			}
		}
		return list;
	}

	@Override
	public boolean checkFileExistancyForCustomer(CustomerFileUpload customerFileUpload,
			CreateCustomerModel createCustomerModel) {
		List<CustomerFileUpload> list = new ArrayList<CustomerFileUpload>();
		String hql = "SELECT customer_upload_id,file_name FROM stp_customer_upload where delete_status=0 and upload_path=? and fk_customer_id="
				+ createCustomerModel.getCustomerId();
		List<Object> agreementlist = entityManager.createNativeQuery(hql)
				.setParameter(1, customerFileUpload.getUploadPath()).getResultList();
		if (agreementlist.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
