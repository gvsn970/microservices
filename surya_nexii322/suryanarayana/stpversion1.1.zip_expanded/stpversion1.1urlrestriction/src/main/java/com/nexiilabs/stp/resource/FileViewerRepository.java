package com.nexiilabs.stp.resource;

public interface FileViewerRepository {

	FileViewerModel getPOfile(Integer fileId, int userId);

	FileViewerModel getSOWfile(Integer fileId, int userId);

	FileViewerModel getAgreementfile(Integer fileId, int userId);

}
