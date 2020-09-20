package com.nexiilabs.stp.resource;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface RequirementsDao {

	boolean saveRequirements(RequirementsModel requirementsModel);

	boolean updateRequirements(RequirementsModel requirementsModel);
	
	boolean updateRequirementStatus(RequirementsModel requirementsModel);

	List<RequirementsModel> getRequirementsList();

	boolean requirementsExistanceCheck(RequirementsModel requirementsModel);

	boolean requirementsExistanceCheckOForUpdate(RequirementsModel requirementsModel);

	RequirementsModel getRequirementById(RequirementsModel requirementsModel);

	boolean deleteRequirements(RequirementsModel requirementsModel);

}
