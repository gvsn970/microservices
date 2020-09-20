package com.nexiilabs.stp.account;

import java.util.List;

import com.nexiilabs.stp.user.UserResponseDTO;

public interface CustomerRepository 
{

	UserResponseDTO createAccount(CreateCustomerModel createCustomerModel);

	UserResponseDTO updateAccount(CreateCustomerModel createCustomerModel);

	UserResponseDTO deleteAccount(CreateCustomerModel createCustomerModel);

	List<CustomersListResponseDTO> getAccountsList(int userId);

	UserResponseDTO saveUploadedFiles(CustomerFileUpload customerFileUpload);

	UserResponseDTO updateUploadedFiles(CustomerFileUpload customerFileUpload);

	UserResponseDTO deleteFiles(String fileIds, Integer customerId);

	UserResponseDTO getFilesofCustomer(CreateCustomerModel createCustomerModel);
	
	List<CustomerFileUpload> getAgreementsList(int customerId);
	
	boolean checkFileExistancyForCustomer(CustomerFileUpload customerFileUpload, CreateCustomerModel createCustomerModel);
}
