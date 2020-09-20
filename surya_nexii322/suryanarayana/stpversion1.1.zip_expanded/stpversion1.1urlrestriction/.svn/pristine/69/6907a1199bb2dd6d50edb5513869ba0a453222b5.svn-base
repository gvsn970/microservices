package com.nexiilabs.stp.bankaccount;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexiilabs.stp.user.CreateUserModel;
import com.nexiilabs.stp.user.UserResponseDTO;

@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {
	private static final Logger log = LogManager.getLogger(BankAccountController.class);

	@Autowired
	BankAccountService bankAccountService;

	@RequestMapping(value = "/addBankAccount", method = RequestMethod.POST)
	public UserResponseDTO addBankAccount(@RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("accountNumber") String accountNumber, @RequestParam("accountIFSC") String accountIFSC,
			@RequestParam("branchName") String branchName,@RequestParam("bankName") String bankName, HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		if(userSession!=null){
			List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
			if(menuList.contains("Bank Accounts")){
				BankAccountModel bankAccountModel = null;
				int userId = 0;
				try {
						CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
						userId = userModel.getUserId();
						if (accountHolderName != null && accountNumber != null && accountIFSC != null
								&& branchName != null) {
							bankAccountModel = new BankAccountModel();
							bankAccountModel.setCreatedBy(userId);
							bankAccountModel.setAccountHolderName(accountHolderName);
							bankAccountModel.setAccountNumber(accountNumber);
							bankAccountModel.setAccountIFSC(accountIFSC);
							bankAccountModel.setAccountBranchName(branchName);
							bankAccountModel.setBankName(bankName);
							userResponseDTO = bankAccountService.addBankAccount(bankAccountModel);
							int isActive=userResponseDTO.getBankAccountActive();
							if (userResponseDTO.getStatusCode() == 1) {
								userResponseDTO.setStatusCode(1);
								userResponseDTO.setBankAccountActive(isActive);
								userResponseDTO.setMessage("Bank Account added successfully.");
							} else if (userResponseDTO.getStatusCode() == 2) {
								userResponseDTO.setStatusCode(2);
								userResponseDTO.setMessage("Bank Account Already Exists.");
							} else {
								userResponseDTO.setStatusCode(0);
								userResponseDTO.setMessage("Bank Account Creation Failed");
							}
						} else {
							userResponseDTO.setStatusCode(0);
							userResponseDTO.setMessage("All input fields are Mandatory");
						}
					
				} catch (Exception e) {
					e.printStackTrace();
					userResponseDTO.setMessage(e.getMessage());
					userResponseDTO.setStatusCode(0);
				}
			}else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Authentication require to access this");
			}
		} else {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage("Login Required to Access this service");
		}
		
		return userResponseDTO;
	}

	@RequestMapping(value = "/updateBankAccount", method = RequestMethod.POST)
	public UserResponseDTO updateBankAccount(@RequestParam("accountId") Integer accountId,
			@RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("accountNumber") String accountNumber, @RequestParam("accountIFSC") String accountIFSC,
			@RequestParam("branchName") String branchName,@RequestParam("bankName") String bankName, HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		if (userSession != null) {
			BankAccountModel bankAccountModel = null;
			List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
			if(menuList.contains("Bank Accounts")){
				int userId = 0;
				try {
					
						CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
						userId = userModel.getUserId();
						if (accountHolderName != null && accountNumber != null && accountIFSC != null
								&& branchName != null) {
							bankAccountModel = new BankAccountModel();
							bankAccountModel.setUpdatedBy(userId);
							bankAccountModel.setAccountId(accountId);
							bankAccountModel.setAccountHolderName(accountHolderName);
							bankAccountModel.setAccountNumber(accountNumber);
							bankAccountModel.setAccountIFSC(accountIFSC);
							bankAccountModel.setAccountBranchName(branchName);
							bankAccountModel.setBankName(bankName);
							userResponseDTO = bankAccountService.updateBankAccount(bankAccountModel);
							if (userResponseDTO.getStatusCode() == 1) {
								userResponseDTO.setStatusCode(1);
								userResponseDTO.setMessage("Bank Account Details updated Successfully.");
							} else if (userResponseDTO.getStatusCode() == 2) {
								userResponseDTO.setStatusCode(2);
								userResponseDTO.setMessage("No changes found to update");
							} else {
								userResponseDTO.setStatusCode(0);
								userResponseDTO.setMessage("Bank Account Details updation Failed.");
							}
						} else {
							userResponseDTO.setStatusCode(0);
							userResponseDTO.setMessage("All input fields are Mandatory");
						}
					
				} catch (Exception e) {
					e.printStackTrace();
					userResponseDTO.setMessage(e.getMessage());
					userResponseDTO.setStatusCode(0);
				}
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("You are not authenticated to access this");
			}
		} else {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage("Login Required to Access this service");
		}
		return userResponseDTO;
	}

	@RequestMapping(value = "/deleteBankAccount/{accountId}", method = RequestMethod.POST)
	public UserResponseDTO deleteBankAccount(@PathVariable("accountId") Integer accountId, HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession userSession = request.getSession(false);
		UserResponseDTO userResponseDTO = new UserResponseDTO();
			if (userSession != null) {
				BankAccountModel bankAccountModel = null;
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("Bank Accounts")){
				int userId = 0;
				try {
					CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
					userId = userModel.getUserId();
					bankAccountModel = new BankAccountModel();
					bankAccountModel.setAccountId(accountId);
					bankAccountModel.setDeletedBy(userId);
					userResponseDTO = bankAccountService.deleteBankAccount(bankAccountModel);
					if (userResponseDTO.getStatusCode() == 1) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setMessage("Bank Account Deleted Successfully");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Bank Account Deletion Failed");
					}
				} catch (Exception e) {
					e.printStackTrace();
					userResponseDTO.setMessage(e.getMessage());
					userResponseDTO.setStatusCode(0);
				}
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Authentication require to access this");
			}
		} else {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage("Login Required to Access this service");
		}
		return userResponseDTO;
	}

	@RequestMapping(value = "/bankAccountList", method = RequestMethod.GET)
	public List<BankAccountListResponseDTO> getBankAccountsList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<BankAccountListResponseDTO> bankAccountListResponseDTO = null;
		if(userSession!=null){
			List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
			if(menuList.contains("Bank Accounts")){
				try {
					bankAccountListResponseDTO = bankAccountService.getBankAccountsList();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return bankAccountListResponseDTO;
	}

	@RequestMapping(value = "/activeBankAccount/{accountId}", method = RequestMethod.POST)
	public UserResponseDTO activeBankAccount(@PathVariable("accountId") Integer accountId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		BankAccountModel bankAccountModel = null;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		if (userSession != null) {
			List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
			if(menuList.contains("Bank Accounts")){
				//int userId = 0;
				try {
					//CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
					//userId = userModel.getUserId();
					bankAccountModel = new BankAccountModel();
					bankAccountModel.setAccountId(accountId);
					userResponseDTO = bankAccountService.activeBankAccount(bankAccountModel);
					if (userResponseDTO.getStatusCode() == 1) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setMessage("Bank  Account activated Successfully");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Bank  Account activation Failed");
					}
				} catch (Exception e) {
					e.printStackTrace();
					userResponseDTO.setMessage(e.getMessage());
					userResponseDTO.setStatusCode(0);
				}
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Authentication require to access this");
			}
		} else {
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage("Login Required to Access this service");
		}
		return userResponseDTO;
	}
	@RequestMapping(value = "/getBankAccountDetails/{accountId}", method = RequestMethod.GET)
	public BankAccountListResponseDTO getBankAccountDetails(@PathVariable("accountId") Integer accountId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		BankAccountListResponseDTO bankAccountListResponseDTO = null;
		if (userSession != null) {
			List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
			if(menuList.contains("Bank Accounts")){
				BankAccountModel bankAccountModel=null;
				//int userId = 0;
				try {
					//CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
					//userId = userModel.getUserId();
					bankAccountModel = new BankAccountModel();
					bankAccountModel.setAccountId(accountId);;
					bankAccountListResponseDTO = bankAccountService.getBankAccountDetails(bankAccountModel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return bankAccountListResponseDTO;
	}

}
