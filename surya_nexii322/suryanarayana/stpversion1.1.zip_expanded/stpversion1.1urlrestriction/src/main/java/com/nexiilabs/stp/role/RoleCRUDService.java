package com.nexiilabs.stp.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleCRUDService {

	@Autowired
	RoleCRUDRepository roleCRUDRepository;
	
	@Autowired
	PermissionRepository permissionRepository;
	
	public List<RoleCRUD> getRolesList(){
		System.out.println("involved on testing..............................");
		List<RoleCRUD> roleslist=new ArrayList<RoleCRUD>();
		roleCRUDRepository.findAll().forEach(roleslist::add);
		 return roleslist;
	}
	
	public RoleCRUD getRoleById(int id){
		return roleCRUDRepository.findOne(id);
	}
	
	public RoleCRUD addRole(RoleCRUD role){
		return roleCRUDRepository.save(role);
		 
	}
	
	public RoleCRUD updateRole( int roleId,RoleCRUD role){
		System.out.println(" in delete "+role.getRole_id()+" >>> "+role.getRole_name()+">>>>"+role.getDelete_status()+">>>>"+role.getDeleted_on());
		return roleCRUDRepository.save(role);
	}
	
	public void deleteRole(int id){
		roleCRUDRepository.delete(id);
	}

	public List<Permissions> getAllPermissionsList() {
		return permissionRepository.findAll();
	}

	/*public RoleCRUD getUserByEmailId(int email) {
		System.err.println(userRepository.getUserByEmailId(email));
		return userRepository.getUserByEmailId(email);
	}*/
	
}
