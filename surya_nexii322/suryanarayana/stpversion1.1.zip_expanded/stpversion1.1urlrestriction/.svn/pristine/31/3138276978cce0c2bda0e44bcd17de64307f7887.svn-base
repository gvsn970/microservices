package com.nexiilabs.stp.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void saveRole(Role role) {
		roleRepository.saveRole(role);	
	}

	@Override
	public Role getById(int id) {
		return roleRepository.getById(id);
	}

	@Override
	public void delete(int id) {
		roleRepository.delete(id);	
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.getAllRoles();
	}

	@Override
	public Role checkRoleExistency(Role role) {
		return roleRepository.checkRoleExistency(role);
	}

	@Override
	public int checkRoleExistency(RoleCRUD role) {
		return roleRepository.checkRoleExistency(role);
	}

	@Override
	public int checkRoleExistencyForUpdate(RoleCRUD role) {
		return roleRepository.checkRoleExistencyForUpdate(role);
	}

	@Override
	public int checkUserExistencByRole(int roleId) {
		return roleRepository.checkUserExistencByRole(roleId);
	}
	
}
