package com.nexiilabs.stp.resource;

import java.util.List;

import com.nexiilabs.stp.user.UserResponseDTO;


public interface PODetailsRepository {

	UserResponseDTO deleteSow(UploadSowModel uploadSowModel);

	UserResponseDTO addPODetails(POModel poModel, Integer fkCustomerId);

	UserResponseDTO updatePODetails(POModel poModel, Integer fkCustomerId);

	UserResponseDTO deletePODetails(POModel poModel, UploadPOModel uploadModel);

	List<POListResponseDTO> getPOList();

	UserResponseDTO saveUploadSowDetails(UploadSowModel uploadModel);

	UserResponseDTO updateSOWFile(UploadSowModel uploadModel);

	UserResponseDTO addPOUploadDetails(UploadPOModel uploadModel);

	UserResponseDTO updatePOUploadDetails(UploadPOModel uploadModel);

	UserResponseDTO getSOWFilesofEmployee(UploadSowModel uploadModel);

	UserResponseDTO getPOFilesofEmployee(UploadPOModel uploadModel);

	UserResponseDTO deletePOFiles(Integer fileId, Integer fkEmployeeId);

	UserResponseDTO deleteSOWFiles(Integer fileId, Integer fkEmployeeId);

	int updatePOStatusInEmployeeAsZero(UploadPOModel uploadModel);

	boolean checkFileExistancyForPO(UploadPOModel uploadModel, POModel poModel);

	boolean checkFileExistancyForSow(UploadSowModel uploadModel);
}
