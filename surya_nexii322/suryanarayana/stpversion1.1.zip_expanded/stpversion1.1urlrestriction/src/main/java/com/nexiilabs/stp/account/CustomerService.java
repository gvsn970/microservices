package com.nexiilabs.stp.account;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nexiilabs.stp.user.UserResponseDTO;

public interface CustomerService {


	UserResponseDTO createAccount(CreateCustomerModel createCustomerModel);

	UserResponseDTO updateAccount(CreateCustomerModel createCustomerModel);

	UserResponseDTO deleteAccount(CreateCustomerModel createCustomerModel);

	List<CustomersListResponseDTO> getAccountsList(int userId);

	UserResponseDTO saveUploadedFiles(CustomerFileUpload customerFileUpload);

	UserResponseDTO updateUploadedFiles(CustomerFileUpload customerFileUpload);

	UserResponseDTO deleteFiles(String fileIds, Integer customerId);

	UserResponseDTO getFilesofCustomer(CreateCustomerModel createCustomerModel);
	
	List<CustomerFileUpload> getAgreementsList(int customerId);
	
	boolean saveFileToDisk(MultipartFile fileObject,String UPLOADED_FOLDER,String fileName,String filePath);
	
	boolean checkFileExistancyForCustomer(CustomerFileUpload customerFileUpload, CreateCustomerModel createCustomerModel);

	UserResponseDTO createDirectories(String companyName, String location, String agreementsFolder);

}
