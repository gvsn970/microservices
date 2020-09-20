package com.nexiilabs.stp.role;

import java.util.List;

public interface RoleRepository {

	void saveRole(Role role);

	Role getById(int id);

	void delete(int id);

	List<Role> getAllRoles();

	Role checkRoleExistency(Role role);

	int checkRoleExistency(RoleCRUD role);

	int checkRoleExistencyForUpdate(RoleCRUD role);

	int checkUserExistencByRole(int roleId);
}
