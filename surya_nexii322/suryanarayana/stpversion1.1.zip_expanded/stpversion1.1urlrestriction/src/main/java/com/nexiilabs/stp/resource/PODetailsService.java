package com.nexiilabs.stp.resource;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nexiilabs.stp.user.UserResponseDTO;

public interface PODetailsService {

	UserResponseDTO deleteSow(UploadSowModel uploadSowModel);
	
	UserResponseDTO saveUploadSowDetails(UploadSowModel uploadModel);

	UserResponseDTO addPODetails(POModel poModel, Integer fkCustomerId);

	UserResponseDTO updatePODetails(POModel poModel, Integer fkCustomerId);

	UserResponseDTO deletePODetails(POModel poModel, UploadPOModel uploadModel);

	List<POListResponseDTO> getPOList();

	UserResponseDTO updateSOWFile(UploadSowModel uploadModel);

	UserResponseDTO addPOUploadDetails(UploadPOModel uploadModel);

	UserResponseDTO updatePOUploadDetails(UploadPOModel uploadModel);

	UserResponseDTO getSOWFilesofEmployee(UploadSowModel uploadModel);

	UserResponseDTO getPOFilesofEmployee(UploadPOModel uploadModel);

	UserResponseDTO deletePOFiles(Integer fileId, Integer fkEmployeeId);

	UserResponseDTO deleteSOWFiles(Integer fileId, Integer fkEmployeeId);

	boolean saveFileToDisk(MultipartFile file_object, String uPLOADED_FOLDER, String fileName, String filePath);

	int updatePOStatusInEmployeeAsZero(UploadPOModel uploadModel);

	UserResponseDTO createDirectories(Integer fkEmployeeId, String subDirectory);

	boolean checkFileExistancyForPO(UploadPOModel uploadModel, POModel poModel);

	boolean checkFileExistancyForSow(UploadSowModel uploadModel);
}
