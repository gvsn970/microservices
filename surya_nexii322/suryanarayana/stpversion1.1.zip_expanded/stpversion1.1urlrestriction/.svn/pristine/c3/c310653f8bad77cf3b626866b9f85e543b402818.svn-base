package com.nexiilabs.stp.bankaccount;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.nexiilabs.stp.user.UserResponseDTO;

@Transactional
@Repository
public class TaxDetailsRepositoryImpl implements TaxDetailsRepository {
	private static final Logger log = LogManager.getLogger(TaxDetailsRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserResponseDTO addtaxDetails(TaxDetailsModel taxDetailsModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			UserResponseDTO taxDetailsExistencyCheck = taxDetailsExistencyCheck(taxDetailsModel);
			if (taxDetailsExistencyCheck.getMessage().equals("Tax Details with this GSTIN Already Exists")
					&& taxDetailsExistencyCheck.getStatusCode() == 2) {
				return taxDetailsExistencyCheck;
			} else {
				String hql="select * from stp_tax  st where st.is_Active=1 and st.delete_status=0";
				List taxlist = entityManager.createNativeQuery(hql).getResultList();
				if(taxlist.size()>0)
				{
					taxDetailsModel.setIsActive(0);
				}
				else
				{
					taxDetailsModel.setIsActive(1);
					userResponseDTO.setTaxDetailsActive(1);
				}
				entityManager.persist(taxDetailsModel);
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Tax Details added Succesfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage("Tax Details addition Failed");
		}
		return userResponseDTO;
	}

	public UserResponseDTO taxDetailsExistencyCheck(TaxDetailsModel taxDetailsModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql1 = "select * from stp_tax st where st.pan_number='" + taxDetailsModel.getPanNumber()
					+ "'and st.gst_in='" + taxDetailsModel.getGstIn() + "' and st.delete_status=0";
			List accountlist = entityManager.createNativeQuery(hql1).getResultList();
			if (accountlist.size() > 0) {
				userResponseDTO.setStatusCode(2);
				userResponseDTO.setMessage("Tax Details with this GSTIN Already Exists");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Tax Details  with this GSTIN Not Exists");
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO updateTaxDetails(TaxDetailsModel taxDetailsModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			UserResponseDTO updateCheck = changesForUpdateCheck(taxDetailsModel);
			if (updateCheck.getMessage().equals("No Changes Found to Update") && updateCheck.getStatusCode() == 2) {
				return updateCheck;
			} else {
				String hql = "update stp_tax st set st.pan_number='" + taxDetailsModel.getPanNumber()
						+ "',st.state_name='" + taxDetailsModel.getState() + "'" + ",st.state_code='"
						+ taxDetailsModel.getStateCode() + "',st.gst_in='" + taxDetailsModel.getGstIn() + "'"
						+ " where st.tax_id=" + taxDetailsModel.getTaxId() + " and st.delete_status=0";
				int update = entityManager.createNativeQuery(hql).executeUpdate();
				if (update == 1) {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("Tax Details Updated Succesfully");
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Tax Details Updation Failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	public UserResponseDTO changesForUpdateCheck(TaxDetailsModel taxDetailsModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql1 = "select * from stp_tax st where st.pan_number='" + taxDetailsModel.getPanNumber() + "' "
					+ "and st.state_code='" + taxDetailsModel.getStateCode() + "' and st.state_name='"
					+ taxDetailsModel.getState() + "'" + "and st.gst_in='" + taxDetailsModel.getGstIn() + "' "
					+ " and st.delete_status=0 and st.tax_id=" + taxDetailsModel.getTaxId();
			List accountlist = entityManager.createNativeQuery(hql1).getResultList();
			System.out.println("Before existency check::" + accountlist.size());
			if (accountlist.size() > 0) {
				userResponseDTO.setStatusCode(2);
				userResponseDTO.setMessage("No Changes Found to Update");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Changes Found to Update");
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;

	}

	@Override
	public UserResponseDTO deleteTaxDetails(TaxDetailsModel taxDetailsModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql = "update stp_tax st set st.delete_status='1',st.deleted_on=CURRENT_TIMESTAMP,st.deleted_by="
					+ taxDetailsModel.getDeletedBy() + " where st.tax_id =" + taxDetailsModel.getTaxId();
			int updatecheck = entityManager.createNativeQuery(hql).executeUpdate();
			if (updatecheck == 1) {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Tax Details Deleted Succesfully");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Tax Details Deletion Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO activeTaxDetails(TaxDetailsModel taxDetailsModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String sql="update stp_tax st set st.is_Active=0 where st.delete_status=0";
			entityManager.createNativeQuery(sql).executeUpdate();
			String hql = "update stp_tax st set st.is_Active=1 where st.tax_id =" + taxDetailsModel.getTaxId();
			int updatecheck = entityManager.createNativeQuery(hql).executeUpdate();
			if (updatecheck == 1) {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Tax Details Activated Succesfully");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Tax Details Activation Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public List<TaxDetailsListResponseDTO> getTaxDetailsList() {
		TaxDetailsListResponseDTO taxDetailsListResponseDTO = null;
		List<TaxDetailsListResponseDTO> list = new ArrayList<TaxDetailsListResponseDTO>();
		try {
			String hql = "select st.tax_id ,st.state_name,st.state_code,st.pan_number,st.gst_in,st.is_Active from stp_tax st where st.delete_status=0";
			List<Object> emplist = entityManager.createNativeQuery(hql).getResultList();
			if (emplist.size() > 0) {
				Iterator itr = emplist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					taxDetailsListResponseDTO = new TaxDetailsListResponseDTO();
					taxDetailsListResponseDTO.setTaxId(Integer.parseInt(String.valueOf(obj[0])));
					taxDetailsListResponseDTO.setState(String.valueOf(obj[1]));
					taxDetailsListResponseDTO.setStateCode(String.valueOf(obj[2]));
					taxDetailsListResponseDTO.setPanNumber(String.valueOf(obj[3]));
					taxDetailsListResponseDTO.setGstIn(String.valueOf(obj[4]));
					taxDetailsListResponseDTO.setIsActive(Integer.parseInt(String.valueOf(obj[5])));
					list.add(taxDetailsListResponseDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public TaxDetailsListResponseDTO getTaxDetails(TaxDetailsModel taxDetailsModel) {
		TaxDetailsListResponseDTO taxDetailsListResponseDTO = null;
		try {
			String hql = "select st.tax_id ,st.state_name,st.state_code,st.pan_number,st.gst_in from stp_tax st where st.tax_id ="
					+ taxDetailsModel.getTaxId() + " and st.delete_status=0 ";
			List<Object> taxlist = entityManager.createNativeQuery(hql).getResultList();
			if (taxlist.size() > 0) {
				Iterator itr = taxlist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					taxDetailsListResponseDTO = new TaxDetailsListResponseDTO();
					taxDetailsListResponseDTO.setTaxId(Integer.parseInt(String.valueOf(obj[0])));
					taxDetailsListResponseDTO.setState(String.valueOf(obj[1]));
					taxDetailsListResponseDTO.setStateCode(String.valueOf(obj[2]));
					taxDetailsListResponseDTO.setPanNumber(String.valueOf(obj[3]));
					taxDetailsListResponseDTO.setGstIn(String.valueOf(obj[4]));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taxDetailsListResponseDTO;
	}
}
