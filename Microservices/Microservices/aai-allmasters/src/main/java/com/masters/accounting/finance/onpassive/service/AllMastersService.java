package com.masters.accounting.finance.onpassive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masters.accounting.finance.onpassive.entity.AddColumnResoponse;
import com.masters.accounting.finance.onpassive.repository.AllMasterRepository;
import com.masters.accounting.finance.onpassive.repository.RepositoryImpl;

@Service
public class AllMastersService {

	@Autowired
	private AllMasterRepository allMastersRepository;
	
	private RepositoryImpl repositoryImpl;

	public AddColumnResoponse createColumnMasters(String columnName) {
		return repositoryImpl.addColumn(columnName);
	}
	
	public AddColumnResoponse updateColumnMasters(String oldColumName,String newColumName) {
		return repositoryImpl.updateColumn(oldColumName,newColumName);
	}
	
	public AddColumnResoponse deleteColumnMasters(String columnName) {
		return repositoryImpl.deleteColumn(columnName);
	}
}
