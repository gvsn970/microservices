package com.ness.springbootcrud.employee;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ServiceEmp {

	ResponseEmp addEmp(EmpModel model);

	ResponseEmp updateEmp(EmpModel model);

	ResponseEmp deleteEmp(int id);

	List<EmpDTO> getEmpData();
	
	EmpDTO getEmpDataById(int id);
	
	boolean saveFileToDisk(MultipartFile file_object, String UPLOADED_FOLDER, String fileName, String filePath);

	ResponseDTO createDirectories( String RELEASE_FOLDER);

	ResponseEmp addEmpDp(UserImageUploadModel uploadModel);

	boolean isValidEmail(String email);

	boolean isValidNumber(String phoneNumber);

	ResponseEmp addEmpFiles(ResumeUploadModel resumeModel);

	boolean isUserExist(String email, String phoneNumber);

	UserImageUploadModel getUserImageByImageId(int imageId);

}
