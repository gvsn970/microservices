package com.nexiilabs.stp.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nexiilabs.stp.account.CustomerFileUpload;
import com.nexiilabs.stp.user.UserResponseDTO;

@Transactional
@Repository
public class PODetailsRepositoryImpl implements PODetailsRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserResponseDTO addPODetails(POModel poModel, Integer fkCustomerId) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			UserResponseDTO poExistencyCheck = poExistencyCheck(poModel.getPoNumber(), poModel.getEmployeeId());
			if (poExistencyCheck.getMessage().equals("PO Already Exists") && poExistencyCheck.getStatusCode() == 2) {
				return poExistencyCheck;
			} else {
				String hql = "select sc.billing_add_lane1,sc.billing_add_lane2,sc.billing_add_pincode,sc.billing_add_state,sc.ship_add_lane1 ,"
						+ "sc.ship_add_lane2,sc.ship_add_state,sc.ship_add_pincode,sc.vendor_add_lane1 ,sc.vendor_add_lane2"
						+ ",sc.vendor_add_state,sc.vendor_add_pincode ,sc.state_name,sc.state_code,sc.pan_number,sc.gst_in from stp_customer sc "
						+ "where sc.delete_status=0 and sc.customer_id=" + fkCustomerId;
				List<Object[]> address = entityManager.createNativeQuery(hql).getResultList();
				System.out.println("Address" + address.toString());
				System.out.println("address size:" + address.size());
				for (Object[] obj : address) {
					poModel.setBillingAddressLane1(String.valueOf(obj[0]));
					poModel.setBillingAddressLane2(String.valueOf(obj[1]));
					poModel.setBillingAddressPinCode(String.valueOf(obj[2]));
					poModel.setBillingAddressState(String.valueOf(obj[3]));
					poModel.setShippingAddressLane1(String.valueOf(obj[4]));
					poModel.setShippingAddressLane2(String.valueOf(obj[5]));
					poModel.setShippingAddressState(String.valueOf(obj[6]));
					poModel.setShippingAddressPinCode(String.valueOf(obj[7]));
					poModel.setVendorAddressLane1(String.valueOf(obj[8]));
					poModel.setVendorAddressLane2(String.valueOf(obj[9]));
					poModel.setVendorAddressState(String.valueOf(obj[10]));
					poModel.setVendorAddressPinCode(String.valueOf(obj[11]));
					poModel.setState(String.valueOf(obj[12]));
					poModel.setStateCode(String.valueOf(obj[13]));
					poModel.setPanNumber(String.valueOf(obj[14]));
					poModel.setGstIn(String.valueOf(obj[15]));
					poModel.setShippingState(String.valueOf(obj[12]));
					poModel.setShippingStateCode(String.valueOf(obj[13]));
					poModel.setShippingPanNumber(String.valueOf(obj[14]));
					poModel.setShippingGstIn(String.valueOf(obj[15]));

				}
				entityManager.persist(poModel);
				if (poModel.getPoId() != 0) {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setPoId(poModel.getPoId());
					userResponseDTO.setMessage("PO Details inserted Succesfully");
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("PO Details insertion Failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO addPOUploadDetails(UploadPOModel uploadModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			entityManager.persist(uploadModel);
			System.out.println("uploadModel.getPoUploadId():::" + uploadModel.getPoUploadId());
			if (uploadModel.getPoUploadId() != 0) {
				String hql = "update stp_employee se set se.po_status=1 where se.employee_id="
						+ uploadModel.getEmployeeId();
				int update = entityManager.createNativeQuery(hql).executeUpdate();
				// System.out.println("update::::::"+update);
				if (update == 1) {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("PO Upload Details inserted Succesfully");
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("PO Upload Details insertion Failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		// System.out.println(userResponseDTO.toString());
		return userResponseDTO;
	}

	@Override
	public int updatePOStatusInEmployeeAsZero(UploadPOModel uploadModel) {
		int update = 0;
		try {
			String hql = "update stp_employee se set se.po_status=0 where se.employee_id="
					+ uploadModel.getEmployeeId();
			update = entityManager.createNativeQuery(hql).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public UserResponseDTO updatePODetails(POModel poModel, Integer fkCustomerId) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String employeeCheck = "select * from stp_po where fk_employee_id=" + poModel.getEmployeeId()
					+ " and delete_status=0";
			List<Object[]> emp = entityManager.createNativeQuery(employeeCheck).getResultList();
			if (emp.size() == 0) {
				poModel.setCreatedBy(poModel.getUpdatedBy());
				return addPODetails(poModel, fkCustomerId);
			} else {
				UserResponseDTO noChangesCheck = noChangesCheck(poModel);
				System.err.println("noChangesCheck:::::::::::" + noChangesCheck.toString());
				if (noChangesCheck.getMessage().equals("No changes Found to update")
						&& noChangesCheck.getStatusCode() == 2) {

					return noChangesCheck;
				} else {
					String hql = "select sc.billing_add_lane1,sc.billing_add_lane2,sc.billing_add_pincode,sc.billing_add_state,sc.ship_add_lane1 ,"
							+ "sc.ship_add_lane2,sc.ship_add_state,sc.ship_add_pincode,sc.vendor_add_lane1 ,sc.vendor_add_lane2"
							+ ",sc.vendor_add_state,sc.vendor_add_pincode ,sc.state_name,sc.state_code,sc.pan_number,sc.gst_in from stp_customer sc "
							+ "where sc.delete_status=0 and sc.customer_id=" + fkCustomerId;
					List<Object[]> address = entityManager.createNativeQuery(hql).getResultList();
					System.out.println("Address" + address.toString());
					System.out.println("address size:" + address.size());
					for (Object[] obj : address) {
						poModel.setBillingAddressLane1(String.valueOf(obj[0]));
						poModel.setBillingAddressLane2(String.valueOf(obj[1]));
						poModel.setBillingAddressPinCode(String.valueOf(obj[2]));
						poModel.setBillingAddressState(String.valueOf(obj[3]));
						poModel.setShippingAddressLane1(String.valueOf(obj[4]));
						poModel.setShippingAddressLane2(String.valueOf(obj[5]));
						poModel.setShippingAddressState(String.valueOf(obj[6]));
						poModel.setShippingAddressPinCode(String.valueOf(obj[7]));
						poModel.setVendorAddressLane1(String.valueOf(obj[8]));
						poModel.setVendorAddressLane2(String.valueOf(obj[9]));
						poModel.setVendorAddressState(String.valueOf(obj[10]));
						poModel.setVendorAddressPinCode(String.valueOf(obj[11]));
						poModel.setState(String.valueOf(obj[12]));
						poModel.setStateCode(String.valueOf(obj[13]));
						poModel.setPanNumber(String.valueOf(obj[14]));
						poModel.setGstIn(String.valueOf(obj[15]));
					}
					String hql1 = "update stp_po set description='" + poModel.getDescription()
							+ "',supplier_ref_number='" + poModel.getSupplierRefNumber() + "',start_date='"
							+ poModel.getStartDate() + "'" + ",end_date='" + poModel.getEndDate()
							+ "',duration_months='" + poModel.getDurationMonths() + "'" + ",unit_price='"
							+ poModel.getUnitPrice() + "',currency='" + poModel.getCurrency() + "'"
							+ ",billing_add_lane1='" + poModel.getBillingAddressLane1() + "'" + ",billing_add_lane2='"
							+ poModel.getBillingAddressLane2() + "'" + ",billing_add_pincode='"
							+ poModel.getBillingAddressPinCode() + "'" + ",billing_add_state='"
							+ poModel.getBillingAddressState() + "'" + ",ship_add_lane1='"
							+ poModel.getShippingAddressLane1() + "'" + ",ship_add_lane2='"
							+ poModel.getShippingAddressLane2() + "'" + ",ship_add_state='"
							+ poModel.getShippingAddressState() + "'" + ",ship_add_pincode='"
							+ poModel.getShippingAddressPinCode() + "'" + ",vendor_add_lane1='"
							+ poModel.getVendorAddressLane1() + "'" + ",vendor_add_lane2='"
							+ poModel.getVendorAddressLane2() + "'" + ",vendor_add_state='"
							+ poModel.getVendorAddressState() + "'" + ",vendor_add_pincode='"
							+ poModel.getVendorAddressPinCode() + "'" + ",state_name='" + poModel.getState()
							+ "',state_code='" + poModel.getStateCode() + "',pan_number='" + poModel.getPanNumber()
							+ "',gst_in='" + poModel.getGstIn() + "',raised_by='" + poModel.getRaisedBy() + "'"
							+ ",raised_on='" + poModel.getRaisedOn() + "',updated_by='" + poModel.getUpdatedBy()
							+ "',updated_on=CURRENT_TIMESTAMP  where " + "fk_employee_id=" + poModel.getEmployeeId();
					int update = entityManager.createNativeQuery(hql1).executeUpdate();
					System.err.println("update:::::::" + update);
					if (update > 0) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setPoId(poModel.getPoId());
						userResponseDTO.setMessage("PO Details Updated Succesfully");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("PO Details Updation Failed");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO updatePOUploadDetails(UploadPOModel uploadModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			entityManager.persist(uploadModel);
			/*
			 * String hql = "update stp_po_upload sp set sp.file_name='" +
			 * uploadModel.getFileName() + "',sp.upload_path='" +
			 * uploadModel.getUploadPath()
			 * +"'sp.fk_employee_id='"+uploadModel.getEmployeeId(); int update =
			 * entityManager.createNativeQuery(hql).executeUpdate();
			 */
			if (uploadModel.getPoUploadId() != 0) {
				String hql = "update stp_employee se set se.po_status=1 where se.employee_id="
						+ uploadModel.getEmployeeId();
				int update = entityManager.createNativeQuery(hql).executeUpdate();
				if (update>0) {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("PO Upload Details uploaded Succesfully");
				} else {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("PO Upload Details Updated Succesfully");
				}
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("PO Upload Details Updation Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO deletePODetails(POModel poModel, UploadPOModel uploadModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String empcheck = "select * from stp_po where fk_employee_id=" + poModel.getEmployeeId()
					+ " and delete_status=0";
			List emp = entityManager.createNativeQuery(empcheck).getResultList();
			if (emp.size() <= 0) {

				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("PO Details are not inserted");
			} else {
				String hql = "update stp_po  se set se.delete_status=1,se.expiry_status=1 ,se.deleted_by='"
						+ poModel.getDeletedBy() + "',se.deleted_on=CURRENT_TIMESTAMP where se.fk_employee_id="
						+ poModel.getEmployeeId();
				int deletepo = entityManager.createNativeQuery(hql).executeUpdate();
				System.out.println("updatecheck:::" + deletepo);
				if (deletepo>0) {
					String hql1 = "update stp_po_upload su set su.delete_status=1,su.deleted_on=CURRENT_TIMESTAMP,su.deleted_by="
							+ uploadModel.getDeletedBy() + " where su.fk_employee_id=" + poModel.getEmployeeId();
					int deleteupload = entityManager.createNativeQuery(hql1).executeUpdate();
					if (deleteupload>0) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setMessage("PO Details Deleted Succesfully");
					}
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("PO Details Deletion Failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		System.out.println("userResponseDTO" + userResponseDTO.toString());
		return userResponseDTO;
	}

	@Override
	public List<POListResponseDTO> getPOList() {
		List<POListResponseDTO> list = new ArrayList<POListResponseDTO>();
		try {
			String hql = "select sc.po_id,sc.po_number,sc.description,sc.currency,sc.raised_on,sc.raised_by,sc.supplier_ref_number"
					+ " from stp_po sc where sc.delete_status='0'";
			List<Object> polist = entityManager.createNativeQuery(hql).getResultList();
			if (polist.size() > 0) {
				Iterator itr = polist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					Integer po_id = Integer.parseInt(String.valueOf(obj[0]));
					POListResponseDTO poModel = new POListResponseDTO();
					poModel.setPoId(po_id);
					poModel.setPoNumber(String.valueOf(obj[1]));
					poModel.setDescription(String.valueOf(obj[2]));
					poModel.setCurrency(String.valueOf(obj[3]));
					poModel.setRaisedOn(String.valueOf(obj[4]));
					poModel.setRaisedBy(String.valueOf(obj[5]));
					poModel.setSupplierRefNum(String.valueOf(obj[6]));
					list.add(poModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public UserResponseDTO poExistencyCheck(String poNumber, int employeeId) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql1 = "select * from stp_po sp where sp.po_number='" + poNumber + "'and fk_employee_id=" + employeeId
					+ " and sp.delete_status=0";
			List polist = entityManager.createNativeQuery(hql1).getResultList();
			System.out.println("Before existency check::" + polist.size());
			if (polist.size() > 0) {
				userResponseDTO.setStatusCode(2);
				userResponseDTO.setMessage("PO Already Exists");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("PO Not Exists");
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;

	}

	public UserResponseDTO noChangesCheck(POModel poModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql = "select * from stp_po sp where sp.description='" + poModel.getDescription()
					+ "'and sp.supplier_ref_number='" + poModel.getSupplierRefNumber() + "'" + " and sp.start_date='"
					+ poModel.getStartDate() + "'" + " and sp.end_date='" + poModel.getEndDate()
					+ "'and sp.duration_months='" + poModel.getDurationMonths() + "'" + " and sp.unit_price='"
					+ poModel.getUnitPrice() + "' and sp.currency='" + poModel.getCurrency() + "' and sp.raised_by='"
					+ poModel.getRaisedBy() + "'" + "and sp.raised_on='" + poModel.getRaisedOn() + "'";
			List<Object> polist = entityManager.createNativeQuery(hql).getResultList();
			if (polist.size() > 0) {
				/*
				 * String
				 * hql1="select * from stp_po_upload sp where sp.file_name='" +
				 * uploadModel.getFileName() +"'and sp.upload_path='" +
				 * uploadModel.getUploadPath() + "' and sp.fk_po_id=" + +
				 * poModel.getPoId(); List<Object> pouploadlist =
				 * entityManager.createNativeQuery(hql1).getResultList();
				 * if(pouploadlist.size()>0) {
				 */
				/*
				 * String
				 * hql2="select * from stp_sow_upload sp where sp.file_name='" +
				 * uploadsowModel.getFileName() +"'and sp.upload_path='" +
				 * uploadsowModel.getUploadPath() + "' and sp.fk_po_id=" + +
				 * poModel.getPoId(); List<Object> sowuploadlist =
				 * entityManager.createNativeQuery(hql2).getResultList();
				 * if(sowuploadlist.size()>0) {
				 */
				userResponseDTO.setStatusCode(2);
				userResponseDTO.setMessage("No changes Found to update");

			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Changes Found ");
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;

	}

	@Override
	public UserResponseDTO saveUploadSowDetails(UploadSowModel uploadModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {

			entityManager.persist(uploadModel);
			if (uploadModel.getSowUploadId() != 0) {
				String hql = "update stp_employee se set se.sow_status=1 where se.employee_id="
						+ uploadModel.getEmployeeId();
				int statusUpdate = entityManager.createNativeQuery(hql).executeUpdate();
				if (statusUpdate>0) {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("Upload SOW Details inserted Succesfully");
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage(" Upload SOW Details insertion Failed");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO updateSOWFile(UploadSowModel uploadModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		int sowFiles=0;
		try {
			entityManager.persist(uploadModel);
			// String hql = "update stp_sow_upload su set su.file_name='" +
			// uploadModel.getFileName() + "',su.upload_path='" +
			// uploadModel.getUploadPath() + "' where
			// su.fk_employee_id='"+uploadModel.getEmployeeId()+"'";
			// int statusUpdate =
			// entityManager.createNativeQuery(hql).executeUpdate();
			if (uploadModel.getSowUploadId() != 0) {
				String hql = "update stp_employee se set se.sow_status=1 where se.employee_id="
						+ uploadModel.getEmployeeId();
				int statusUpdate = entityManager.createNativeQuery(hql).executeUpdate();
				userResponseDTO=getSOWFilesofEmployee(uploadModel);
				 sowFiles=userResponseDTO.getNoOfSowfiles();
				if (statusUpdate>0) {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage(" SOW Details uploaded Succesfully");
					userResponseDTO.setNoOfSowfiles(sowFiles);

				} else {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("Upload SOW Details updated Succesfully");
					userResponseDTO.setNoOfSowfiles(sowFiles);
				}
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage(" Upload SOW Details updation Failed");
				userResponseDTO.setNoOfSowfiles(sowFiles);
			}

		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO deleteSow(UploadSowModel uploadSowModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String empcheck = "select * from stp_sow_upload where fk_employee_id=" + uploadSowModel.getEmployeeId()
					+ " and delete_status=0";
			List empList = entityManager.createNativeQuery(empcheck).getResultList();
			if (empList.size() <= 0) {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Sow Details are not inserted");
			} else {
				String hql = "update stp_sow_upload  se set se.delete_status=1,se.deleted_on=CURRENT_TIMESTAMP,se.deleted_by="
						+ uploadSowModel.getDeletedBy() + " where se.fk_employee_id=" + uploadSowModel.getEmployeeId();
				int updatecheck = entityManager.createNativeQuery(hql).executeUpdate();
				System.out.println("updatecheck:::" + updatecheck);
				if (updatecheck >0) {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("Sow Details Deleted Succesfully");
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Sow Details Deletion Failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO getSOWFilesofEmployee(UploadSowModel uploadModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql = "select * from stp_sow_upload  se  where  se.delete_status=0 and se.fk_employee_id="
					+ uploadModel.getEmployeeId();
			List sowList = entityManager.createNativeQuery(hql).getResultList();
			if (sowList.size() > 0) {

				userResponseDTO.setStatusCode(1);
				userResponseDTO.setNoOfSowfiles(sowList.size());
				userResponseDTO.setMessage("Sow Files are uploaded");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Sow files are not yet uploaded");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO getPOFilesofEmployee(UploadPOModel uploadModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql = "select * from stp_po_upload  se  where  se.delete_status=0 and se.fk_employee_id="
					+ uploadModel.getEmployeeId();
			List poList = entityManager.createNativeQuery(hql).getResultList();
			if (poList.size() > 0) {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setNoOfPOfiles(poList.size());
				userResponseDTO.setMessage("PO Files are uploaded");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("PO files are not yet uploaded");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO deletePOFiles(Integer fileId, Integer fkEmployeeId) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String sql = "update stp_po_upload su set su.delete_status=1,su.deleted_on=CURRENT_TIMESTAMP where su.po_upload_id ="
					+ fileId + " and su.fk_employee_id=" + fkEmployeeId;
			int updatecheck = entityManager.createNativeQuery(sql).executeUpdate();
			if (updatecheck >0) {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("PO File Delete Successfully");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("PO File Deletion Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO deleteSOWFiles(Integer fileId, Integer fkEmployeeId) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String sql = "update stp_sow_upload su set su.delete_status=1 ,su.deleted_on=CURRENT_TIMESTAMP where su.sow_upload_id ="
					+ fileId + " and su.fk_employee_id=" + fkEmployeeId;
			int updatecheck = entityManager.createNativeQuery(sql).executeUpdate();
			if (updatecheck>0) {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Sow File Delete Successfully");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Sow File Deletion Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public boolean checkFileExistancyForPO(UploadPOModel uploadModel, POModel poModel) {
		List<UploadPOModel> list = new ArrayList<UploadPOModel>();
		String hql="SELECT po_upload_id,file_name FROM stp_po_upload where delete_status=0 and upload_path=? and fk_employee_id="+poModel.getEmployeeId();
		List<Object> poFilelist = entityManager.createNativeQuery(hql).setParameter(1, uploadModel.getUploadPath()).getResultList();
		if(poFilelist.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean checkFileExistancyForSow(UploadSowModel uploadModel) {
		List<UploadSowModel> list = new ArrayList<UploadSowModel>();
		String hql="SELECT sow_upload_id,file_name FROM stp_sow_upload where delete_status=0 and upload_path=? and fk_employee_id="+uploadModel.getEmployeeId();
		List<Object> sowFilelist = entityManager.createNativeQuery(hql).setParameter(1, uploadModel.getUploadPath()).getResultList();
		if(sowFilelist.size()>0){
			return true;
		}else{
			return false;
		}
	}

}
