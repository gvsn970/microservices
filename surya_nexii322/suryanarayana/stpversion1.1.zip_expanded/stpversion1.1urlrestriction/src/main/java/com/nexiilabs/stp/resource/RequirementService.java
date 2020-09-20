package com.nexiilabs.stp.resource;

import java.util.List;

public interface RequirementService {

	boolean saveRequirements(RequirementsModel requirementsModel);

	List<RequirementsModel> getRequirementsList();

	boolean updateRequirements(RequirementsModel requirementsModel);

	boolean requirementsExistanceCheck(RequirementsModel requirementsModel);

	boolean requirementsExistanceCheckOForUpdate(RequirementsModel requirementsModel);

	RequirementsModel getRequirementById(RequirementsModel requirementsModel);

	boolean deleteRequirements(RequirementsModel requirementsModel);

}
