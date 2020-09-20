package com.ness.springbootcrud.employee;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServiceEmpImpl implements ServiceEmp {

	@Autowired
	private Dao dao;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	
	public boolean isValidEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public boolean isValidNumber(String number) {
		Pattern p = Pattern.compile("(0|91)?[6-9][0-9]{9}");
		Matcher m = p.matcher(number);
		return (m.find() && m.group().equals(number));
	}
	
	@Override
	public boolean saveFileToDisk(MultipartFile file_object, String UPLOADED_FOLDER, String fileName, String filePath) {
		try {
			byte[] bytes = file_object.getBytes();
			new File(UPLOADED_FOLDER + fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			stream.write(bytes);
			stream.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ResponseDTO createDirectories(String FOLDER) {
		ResponseDTO userResponseDTO = new ResponseDTO();
		// System.err.println("in create directories:::::::::::::::::::::::"+id);
		try {
			if (FOLDER != null) {
				File file = new File(FOLDER);
				if (!file.exists()) {
					if (file.mkdir()) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setStatusMessage("Tenant Directory is created");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setStatusMessage("Failed to create  Tenant directory");
					}
				} else {
					userResponseDTO.setStatusCode(2);
					userResponseDTO.setStatusMessage("Tenant Directory already exists");
				}
				// System.err.println("tenant path"+file.getPath());
				File file1 = new File(file.getPath() + "/");
				if (!file1.exists()) {
					if (file1.mkdir()) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setStatusMessage("User Directory is created");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setStatusMessage("Failed to create  User directory");
					}
				} else {
					userResponseDTO.setStatusCode(2);
					userResponseDTO.setStatusMessage("User Directory already exists");
				}
				File file2 = new File(file1.getPath() + "/" + java.time.LocalDate.now());
				if (!file2.exists()) {
					if (file2.mkdir()) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setStatusMessage("Date Directory is created");
						userResponseDTO.setUploadPath(file2.getPath() + "/");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setStatusMessage("Failed to create Date directory");
					}
				} else {
					userResponseDTO.setStatusCode(2);
					userResponseDTO.setStatusMessage("Date directory already exists");
					userResponseDTO.setUploadPath(file2.getPath());
				}

			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setStatusMessage("All input fields are required");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("In create directory:::::::::::::" + userResponseDTO.getStatusMessage() + "path::::::::::"
				+ userResponseDTO.getUploadPath());
		return userResponseDTO;
	}

	@Override
	public ResponseEmp addEmp(EmpModel model) {
		// TODO Auto-generated method stub
		return dao.addEmp(model);
	}

	@Override
	public ResponseEmp updateEmp(EmpModel model) {
		// TODO Auto-generated method stub
		return dao.updateEmp(model);
	}

	@Override
	public ResponseEmp deleteEmp(int id) {
		// TODO Auto-generated method stub
		return dao.deleteEmp(id);
	}

	
	@Override
	public List<EmpDTO> getEmpData() {
		// TODO Auto-generated method stub
		return dao.getEmpData();
	}

	@Override
	public EmpDTO getEmpDataById(int id) {
		// TODO Auto-generated method stub
		return dao.getEmpDataById(id);
	}

	@Override
	public ResponseEmp addEmpDp(UserImageUploadModel uploadModel) {
		// TODO Auto-generated method stub
		return dao.addEmpDp(uploadModel);
	}

	@Override
	public ResponseEmp addEmpFiles(ResumeUploadModel resumeModel) {
		return dao.addEmpFiles(resumeModel);
	}

	@Override
	public boolean isUserExist(String email, String phoneNumber) {
		return dao.isUserExist(email, phoneNumber);
	}

	@Override
	public UserImageUploadModel getUserImageByImageId(int imageId) {
		return dao.getUserImageByImageId(imageId);
	} 
	
	
}
