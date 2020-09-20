package com.nexiilabs.stp.resource;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.nexiilabs.stp.account.CreateCustomerModel;
import com.nexiilabs.stp.account.CustomerFileUpload;
import com.nexiilabs.stp.user.CreateUserModel;
import com.nexiilabs.stp.user.UserResponseDTO;

@Service
public class PODetailsServiceImpl implements PODetailsService
{
	@Autowired
	 PODetailsRepository  poDetailsRepository;



	@Override
	public UserResponseDTO deleteSow(UploadSowModel uploadSowModel) {
		UserResponseDTO userResponseDTO=poDetailsRepository.deleteSow(uploadSowModel);
		return userResponseDTO;
	}


	@Override
	public UserResponseDTO addPODetails(POModel poModel,Integer fkCustomerId) {
		UserResponseDTO userResponseDTO=poDetailsRepository.addPODetails(poModel,fkCustomerId);
		return userResponseDTO;
	}


	@Override
	public UserResponseDTO updatePODetails(POModel poModel,Integer fkCustomerId) {
		UserResponseDTO userResponseDTO=poDetailsRepository.updatePODetails(poModel,fkCustomerId);
		return userResponseDTO;
	}


	@Override
	public UserResponseDTO deletePODetails(POModel poModel, UploadPOModel uploadModel) {
		UserResponseDTO userResponseDTO=poDetailsRepository.deletePODetails(poModel,uploadModel);
		return userResponseDTO;
	}


	@Override
	public List<POListResponseDTO> getPOList() {
		return poDetailsRepository.getPOList();
	}


	@Override
	public UserResponseDTO saveUploadSowDetails(UploadSowModel uploadModel) {
		UserResponseDTO userResponseDTO=poDetailsRepository.saveUploadSowDetails(uploadModel);
		return userResponseDTO;
	}


	@Override
	public UserResponseDTO updateSOWFile(UploadSowModel uploadModel) {
		UserResponseDTO userResponseDTO=poDetailsRepository.updateSOWFile(uploadModel);
		return userResponseDTO;	}


	@Override
	public UserResponseDTO addPOUploadDetails(UploadPOModel uploadModel) {
		UserResponseDTO userResponseDTO=poDetailsRepository.addPOUploadDetails(uploadModel);
		return userResponseDTO;
	}


	@Override
	public UserResponseDTO updatePOUploadDetails(UploadPOModel uploadModel) {
		UserResponseDTO userResponseDTO=poDetailsRepository.updatePOUploadDetails(uploadModel);
		return userResponseDTO;
	}


	@Override
	public UserResponseDTO getSOWFilesofEmployee(UploadSowModel uploadModel) {
		UserResponseDTO userResponseDTO=poDetailsRepository.getSOWFilesofEmployee(uploadModel);
		return userResponseDTO;
	}


	@Override
	public UserResponseDTO getPOFilesofEmployee(UploadPOModel uploadModel) {
		UserResponseDTO userResponseDTO=poDetailsRepository.getPOFilesofEmployee(uploadModel);
		return userResponseDTO;
	}


	@Override
	public UserResponseDTO deletePOFiles(Integer fileId,Integer fkEmployeeId) {
		UserResponseDTO userResponseDTO=poDetailsRepository.deletePOFiles(fileId,fkEmployeeId);
		return userResponseDTO;
	}


	@Override
	public UserResponseDTO deleteSOWFiles(Integer fileId,Integer fkEmployeeId) {
		UserResponseDTO userResponseDTO=poDetailsRepository.deleteSOWFiles(fileId,fkEmployeeId);
		return userResponseDTO;
	}

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
	public int updatePOStatusInEmployeeAsZero(UploadPOModel uploadModel) {
		int userResponseDTO=poDetailsRepository.updatePOStatusInEmployeeAsZero(uploadModel);
		return userResponseDTO;
	}
	@Override
	public UserResponseDTO createDirectories(@PathVariable("fkEmployeeId") Integer fkEmployeeId,@PathVariable("subDirectory") String subDirectory) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		try {
			if (fkEmployeeId != 0 &&subDirectory!=null ) 
			{
				File file = new File(subDirectory+"/Resource_" +fkEmployeeId);
				if (!file.exists()) {
					if (file.mkdir()) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setMessage("User Directory is created");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Failed to create  User directory");
					}
				} else {
					userResponseDTO.setStatusCode(2);
					userResponseDTO.setMessage("User Directory already exists");
				}
				
				File file1 = new File(subDirectory+"/Resource_"+fkEmployeeId+"/"+java.time.LocalDate.now());
				if (!file1.exists()) {
					if (file1.mkdir()) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setMessage("Date Directory is created");
						userResponseDTO.setUploadPath(file1.getPath()+"/");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Failed to create Date directory");
					}
				} else {
					userResponseDTO.setStatusCode(2);
					userResponseDTO.setMessage("Date directory already exists");
					userResponseDTO.setUploadPath(file1.getPath());
				}

			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("All input fields are required");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userResponseDTO;
	}
	@Override
	public boolean checkFileExistancyForPO(UploadPOModel uploadModel,POModel poModel) 
	{
		return poDetailsRepository.checkFileExistancyForPO(uploadModel, poModel);
	}
	@Override
	public boolean checkFileExistancyForSow(UploadSowModel uploadModel) 
	{
		return poDetailsRepository.checkFileExistancyForSow(uploadModel);
	}
	}
