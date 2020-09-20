package com.ness.springbootcrud.employee;

import java.util.List;

public interface Dao {

	ResponseEmp addEmp(EmpModel model);
	ResponseEmp updateEmp(EmpModel model);
	ResponseEmp deleteEmp(int id);
	List<EmpDTO> getEmpData();
	EmpDTO getEmpDataById(int id);
	ResponseEmp addEmpDp(UserImageUploadModel uploadModel);
	ResponseEmp addEmpFiles(ResumeUploadModel resumeModel);
	boolean isUserExist(String email, String phoneNumber);
	UserImageUploadModel getUserImageByImageId(int imageId);
}
