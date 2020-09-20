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
public class BankAccountRepositoryImpl implements BankAccoutRepository {
	private static final Logger log = LogManager.getLogger(BankAccountRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserResponseDTO addBankAccount(BankAccountModel bankAccountModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			UserResponseDTO bankExistencyCheck = bankAccountExistencyCheck(bankAccountModel.getAccountNumber());
			if (bankExistencyCheck.getMessage().equals("Bank Account with this Account Number Already Exists")
					&& bankExistencyCheck.getStatusCode() == 2) {
				return bankExistencyCheck;
			} else {
				String hql="select * from stp_bank_account sc where sc.is_Active=1 and sc.delete_status=0";
				List active = entityManager.createNativeQuery(hql).getResultList();
				if(active.size()>0)
				{
					bankAccountModel.setIsActive(0);
				}
				else
				{
					bankAccountModel.setIsActive(1);
					userResponseDTO.setBankAccountActive(1);
				}
				entityManager.persist(bankAccountModel);
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Bank Details added Succesfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage("Bank Details addition Failed");
		}
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO updateBankAccount(BankAccountModel bankAccountModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			UserResponseDTO updateCheck = changesForUpdateCheck(bankAccountModel);
			if (updateCheck.getMessage().equals("No Changes Found to Update") && updateCheck.getStatusCode() == 2) {
				return updateCheck;
			} else {
				String hql = "update stp_bank_account sc set sc.account_holder_name='" + bankAccountModel.getAccountHolderName() + "'"
						+ ",sc.account_number='" + bankAccountModel.getAccountNumber() + "'" + ",sc.account_ifsc='"
						+ bankAccountModel.getAccountIFSC() + "',sc.account_branch_name='"
						+ bankAccountModel.getAccountBranchName() + "',sc.bank_name='"
						+ bankAccountModel.getBankName() + "'" + ",sc.updated_by='" + bankAccountModel.getUpdatedBy()
						+ "'" + ",sc.updated_on=CURRENT_TIMESTAMP " + "where sc.bank_account_id="
						+ bankAccountModel.getAccountId();
				int update = entityManager.createNativeQuery(hql).executeUpdate();
				if (update == 1) {
					userResponseDTO.setStatusCode(1);
					userResponseDTO.setMessage("Bank Details Updated Succesfully");
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Bank Details Updation Failed");
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
	public UserResponseDTO deleteBankAccount(BankAccountModel bankAccountModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql = "update stp_bank_account sc  set sc.delete_status='1',sc.deleted_on=CURRENT_TIMESTAMP,sc.deleted_by="
					+ bankAccountModel.getDeletedBy() + " where sc.bank_account_id=" + bankAccountModel.getAccountId();
			int updatecheck = entityManager.createNativeQuery(hql).executeUpdate();
			System.out.println("updatecheck:::" + updatecheck);
			if (updatecheck == 1) {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Bank Account Deleted Succesfully");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Bank Account Deletion Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	public UserResponseDTO bankAccountExistencyCheck(String accountNumber) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql1 = "select * from stp_bank_account sb where sb.account_number='" + accountNumber
					+ "' and sb.delete_status=0";
			List accountlist = entityManager.createNativeQuery(hql1).getResultList();
			System.out.println("Before existency check::" + accountlist.size());
			if (accountlist.size() > 0) {
				userResponseDTO.setStatusCode(2);
				userResponseDTO.setMessage("Bank Account with this Account Number Already Exists");
			} else {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Bank Account with this Account Number Not Exists");
			}
		} catch (Exception e) {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;

	}

	public UserResponseDTO changesForUpdateCheck(BankAccountModel bankAccountModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String hql1 = "select * from stp_bank_account sc where sc.account_number='"
					+ bankAccountModel.getAccountNumber() + "' and sc.account_holder_name='"
					+ bankAccountModel.getAccountHolderName() + "'" + "and sc.account_ifsc='"
					+ bankAccountModel.getAccountIFSC() + "' and sc.account_branch_name='"
					+ bankAccountModel.getAccountBranchName() + "' " + " and sc.delete_status=0 and sc.bank_account_id="
					+ bankAccountModel.getAccountId();
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
	public List<BankAccountListResponseDTO> getBankAccountsList() {
		BankAccountListResponseDTO bankAccountListResponseDTO = null;
		List<BankAccountListResponseDTO> list = new ArrayList<BankAccountListResponseDTO>();
		try {
			String hql = "select sa.bank_account_id ,sa.account_holder_name,sa.account_number,sa.account_ifsc,sa.account_branch_name,sa.bank_name,sa.is_Active from stp_bank_account sa where sa.delete_status=0";
			List<Object> emplist = entityManager.createNativeQuery(hql).getResultList();
			if (emplist.size() > 0) {
				Iterator itr = emplist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					bankAccountListResponseDTO = new BankAccountListResponseDTO();
					bankAccountListResponseDTO.setAccountId(Integer.parseInt(String.valueOf(obj[0])));
					bankAccountListResponseDTO.setAccountHolderName(String.valueOf(obj[1]));
					bankAccountListResponseDTO.setAccountNumber(String.valueOf(obj[2]));
					bankAccountListResponseDTO.setAccountIFSC(String.valueOf(obj[3]));
					bankAccountListResponseDTO.setAccountBranchName(String.valueOf(obj[4]));
					bankAccountListResponseDTO.setBankName(String.valueOf(obj[5]));
					bankAccountListResponseDTO.setIsActive(Integer.parseInt(String.valueOf(obj[6])));
					list.add(bankAccountListResponseDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public UserResponseDTO activeBankAccount(BankAccountModel bankAccountModel) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			String sql="update stp_bank_account st set st.is_Active=0 where st.delete_status=0";
			 entityManager.createNativeQuery(sql).executeUpdate();
			String hql = "update stp_bank_account st set st.is_Active=1 where st.bank_account_id="
					+ bankAccountModel.getAccountId();
			int updatecheck = entityManager.createNativeQuery(hql).executeUpdate();
			if (updatecheck == 1) {
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Bank Account Activated Succesfully");
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Bank Account Activation Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage(e.getMessage());
		}
		return userResponseDTO;
	}

	@Override
	public BankAccountListResponseDTO getBankAccountDetails(BankAccountModel bankAccountModel) {
		BankAccountListResponseDTO bankAccountListResponseDTO = null;
		try {
			String hql = "select sa.bank_account_id ,sa.account_holder_name,sa.account_number,sa.account_ifsc,sa.account_branch_name,sa.bank_name "
					+ "from stp_bank_account sa where sa.delete_status=0 and sa.bank_account_id="+bankAccountModel.getAccountId();
			List<Object> banklist = entityManager.createNativeQuery(hql).getResultList();
			if (banklist.size() > 0) {
				Iterator itr = banklist.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					bankAccountListResponseDTO = new BankAccountListResponseDTO();
					bankAccountListResponseDTO.setAccountId(Integer.parseInt(String.valueOf(obj[0])));
					bankAccountListResponseDTO.setAccountHolderName(String.valueOf(obj[1]));
					bankAccountListResponseDTO.setAccountNumber(String.valueOf(obj[2]));
					bankAccountListResponseDTO.setAccountIFSC(String.valueOf(obj[3]));
					bankAccountListResponseDTO.setAccountBranchName(String.valueOf(obj[4]));
					bankAccountListResponseDTO.setBankName(String.valueOf(obj[5]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankAccountListResponseDTO;
	}
}
