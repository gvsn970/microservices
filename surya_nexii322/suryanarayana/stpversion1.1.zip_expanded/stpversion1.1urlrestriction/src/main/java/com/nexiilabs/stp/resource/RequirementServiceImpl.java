package com.nexiilabs.stp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequirementServiceImpl implements RequirementService{

	@Autowired
	private RequirementsDao requirementsDao;

	@Override
	public boolean saveRequirements(RequirementsModel requirementsModel) {
		return requirementsDao.saveRequirements(requirementsModel);
	}

	@Override
	public List<RequirementsModel> getRequirementsList() {
		return requirementsDao.getRequirementsList();
	}

	@Override
	public boolean updateRequirements(RequirementsModel requirementsModel) {
		return requirementsDao.updateRequirements(requirementsModel);
	}

	@Override
	public boolean requirementsExistanceCheck(RequirementsModel requirementsModel) {
		return requirementsDao.requirementsExistanceCheck(requirementsModel);
	}

	@Override
	public boolean requirementsExistanceCheckOForUpdate(RequirementsModel requirementsModel) {
		return requirementsDao.requirementsExistanceCheckOForUpdate(requirementsModel);
	}

	@Override
	public RequirementsModel getRequirementById(RequirementsModel requirementsModel) {
		return requirementsDao.getRequirementById(requirementsModel);
	}

	@Override
	public boolean deleteRequirements(RequirementsModel requirementsModel) {
		return requirementsDao.deleteRequirements(requirementsModel);
	}
	
}
