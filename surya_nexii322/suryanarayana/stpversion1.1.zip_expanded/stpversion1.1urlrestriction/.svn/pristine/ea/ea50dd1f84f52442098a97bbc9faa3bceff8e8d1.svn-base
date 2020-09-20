package com.nexiilabs.stp.account;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.nexiilabs.stp.user.UserResponseDTO;

@Service
public class CustomerServiceImpl implements CustomerService
{
	@Autowired
	CustomerRepository customerRepository;
	@Override
	public UserResponseDTO createAccount(CreateCustomerModel createCustomerModel) {
		UserResponseDTO userResponseDTO=customerRepository.createAccount(createCustomerModel);
		return userResponseDTO;
	}
	@Override
	public UserResponseDTO updateAccount(CreateCustomerModel createCustomerModel) {
		UserResponseDTO userResponseDTO=customerRepository.updateAccount(createCustomerModel);
		return userResponseDTO;
	}
	@Override
	public UserResponseDTO deleteAccount(CreateCustomerModel createCustomerModel) {
		UserResponseDTO userResponseDTO=customerRepository.deleteAccount(createCustomerModel);
		return userResponseDTO;
	}
	@Override
	public List<CustomersListResponseDTO> getAccountsList(int userId) {
			return  customerRepository.getAccountsList(userId);
		}
	@Override
	public UserResponseDTO saveUploadedFiles(CustomerFileUpload customerFileUpload) {
		UserResponseDTO userResponseDTO=customerRepository.saveUploadedFiles(customerFileUpload);
		return userResponseDTO;
	}
	@Override
	public UserResponseDTO updateUploadedFiles(CustomerFileUpload customerFileUpload) {
		UserResponseDTO userResponseDTO=customerRepository.updateUploadedFiles(customerFileUpload);
		return userResponseDTO;
	}
	@Override
	public UserResponseDTO deleteFiles(String fileIds,Integer customerId) {
		UserResponseDTO userResponseDTO=customerRepository.deleteFiles(fileIds,customerId);
		return userResponseDTO;
	}
	@Override
	public UserResponseDTO getFilesofCustomer(CreateCustomerModel createCustomerModel) {
		UserResponseDTO userResponseDTO=customerRepository.getFilesofCustomer(createCustomerModel);
		return userResponseDTO;
	}
	@Override
	public List<CustomerFileUpload> getAgreementsList(int customerId) {
		return customerRepository.getAgreementsList(customerId);
	}
	@Override
	public boolean saveFileToDisk(MultipartFile fileObject,String UPLOADED_FOLDER,String fileName,String filePath) {
		try{
			byte[] bytes = fileObject.getBytes();
			new File(UPLOADED_FOLDER + fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			stream.write(bytes);
			stream.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean checkFileExistancyForCustomer(CustomerFileUpload customerFileUpload,
			CreateCustomerModel createCustomerModel) {
		return customerRepository.checkFileExistancyForCustomer(customerFileUpload, createCustomerModel);
	}
	@Override
	public UserResponseDTO createDirectories(@PathVariable("companyName") String companyName,@PathVariable("location") String location,@PathVariable("subDirectory") String subDirectory) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			if (companyName !=null &&subDirectory!=null ) 
			{
				File file = new File(subDirectory+"/ACCOUNT_" +companyName+"_"+location);
				if (!file.exists()) {
					if (file.mkdir()) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setMessage("Account Directory is created");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Failed to create  Account directory");
					}
				} else {
					userResponseDTO.setStatusCode(2);
					userResponseDTO.setMessage("Account Directory already exists");
				}
				
				File file1 = new File(subDirectory+"/ACCOUNT_"+companyName+"_"+location+"/"+java.time.LocalDate.now());
				if (!file1.exists()) {
					if (file1.mkdir()) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setMessage("Date Directory is created");
						userResponseDTO.setUploadPath(file1.getPath());
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Failed to create Date directory");
					}
				} else {
					userResponseDTO.setStatusCode(2);
					userResponseDTO.setMessage("Date directory already exists");
					userResponseDTO.setUploadPath(file1.getPath()+"/");
				}

			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("All input fields are required");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("userResponseDTO:::"+userResponseDTO);
		return userResponseDTO;
	}
}
