package com.nexiilabs.stp.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FileViewerServiceImpl implements FileViewerService
{
	@Autowired
	FileViewerRepository fileViewerRepository;

	@Override
	public FileViewerModel getPOfile(Integer fileId, int userId) {
		
		return fileViewerRepository.getPOfile(fileId,userId);
	}

	@Override
	public FileViewerModel getSOWfile(Integer fileId, int userId) {
		return fileViewerRepository.getSOWfile(fileId,userId);
	}

	@Override
	public FileViewerModel getAgreementfile(Integer fileId, int userId) {
		return fileViewerRepository.getAgreementfile(fileId,userId);
	}

}
