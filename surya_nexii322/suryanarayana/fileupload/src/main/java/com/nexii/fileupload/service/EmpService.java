package com.nexii.fileupload.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nexii.fileupload.dao.EmpRepository;
import com.nexii.fileupload.model.EmpModel;

@Service
@Transactional
public class EmpService {
	private final EmpRepository empRepository;

	public EmpService(EmpRepository empRepository) {
		
		this.empRepository = empRepository;
	}

	public void save(EmpModel empModel) {
		empRepository.save(empModel);
	}

}
