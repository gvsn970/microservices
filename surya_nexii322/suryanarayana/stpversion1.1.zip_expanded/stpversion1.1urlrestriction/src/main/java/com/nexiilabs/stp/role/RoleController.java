package com.nexiilabs.stp.role;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nexiilabs.stp.util.RBackUtility;

@RestController
@RequestMapping("/role")
public class RoleController {
	private static final Logger log=LogManager.getLogger(RoleController.class);
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@Autowired
	RoleService roleService;
		
	@RequestMapping("/Hi")
		public String getMsg(){
		log.info("in role controller!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return "hello !";
	}
	
	 @RequestMapping(value = "/addrole", method = RequestMethod.GET,produces = "application/json")
	//@PostMapping("/addrole")
	//public ResponseEntity<Role> addRole(@RequestBody Role role) {
	 public ResponseEntity<Role> addRole(HttpServletRequest request,HttpServletResponse response) {
		try{
			HttpSession userSession = request.getSession(false);
			System.out.println("in add role");
			Role role=new Role();
			role.setRole_name("Test Lead");
			role.setFk_permission_id("1,2,3");
			role.setDelete_status(0);
			//role.setCreated_on(new Date()+"");
			Role role_check=roleService.checkRoleExistency(role);
			if(role_check==null){
		roleService.saveRole(role);
			}else{
				return new ResponseEntity("Role already existed", HttpStatus.OK);
			}
		log.debug("Added:: " + role);
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return new ResponseEntity("Role created successfully", HttpStatus.OK);
		
		//return new ResponseEntity<Role>(role, HttpStatus.CREATED);
	}
	 
	 @RequestMapping(value = "/updaterole/{id}",method = RequestMethod.GET)
		//public ResponseEntity<Role> updateRole(@RequestBody Role role) {
	 public ResponseEntity<Role> updateRole(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {		
		 //Role existingRole = roleService.getById(role.getRole_id);
		 HttpSession userSession = request.getSession(false);
		 Role existingRole = roleService.getById(id);
			if (existingRole == null) {
				log.debug("Role with id " + id + " does not exists");
				return new ResponseEntity("Role does not exist",HttpStatus.OK);
			} else {
				Role role_check=roleService.checkRoleExistency(existingRole);
				if(role_check!=null){
					return new ResponseEntity("Role already existed",HttpStatus.OK);
				}else{
					existingRole.setRole_name("updated_role_name");
					 existingRole.setFk_permission_id("1,5");
					 Date date = new Date();
					 existingRole.setUpdated_on(dateFormat.format(date));
						System.out.println(dateFormat.format(date));
				roleService.saveRole(existingRole);
				return new ResponseEntity("Role updated successfully",HttpStatus.OK);
			}
			}
		}


		@RequestMapping(value = "/getrolebyid/{id}", method = RequestMethod.GET)
		public ResponseEntity<Role> getRoleById(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
			HttpSession userSession = request.getSession(false);
			Role role = roleService.getById(id);
			if (role == null) {
				log.debug("Role with id " + id + " does not exists");
				return new ResponseEntity(RBackUtility.DATA_IS_EMPTY, HttpStatus.OK);
				//return new ResponseEntity<Role>(role,HttpStatus.NOT_FOUND);
			}
			log.debug("Found Role:: " + role);
			return new ResponseEntity<Role>(role, HttpStatus.OK);
		}


		@RequestMapping(value = "/getallroles",method = RequestMethod.GET)
		public ResponseEntity<List<Role>> getAllRoles(HttpServletRequest request,HttpServletResponse response) {
			HttpSession userSession = request.getSession(false);
			List<Role> roles = roleService.getAllRoles();
			if (roles.isEmpty()) {
				log.debug("Roles does not exists");
				return new ResponseEntity(RBackUtility.DATA_IS_EMPTY, HttpStatus.OK);
				//return new ResponseEntity<List<Role>>(HttpStatus.NO_CONTENT);
			}
			log.debug("Found " + roles.size() + " roles");
			log.debug(roles);
			log.debug(Arrays.toString(roles.toArray()));
			return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
		}


		@RequestMapping(value = "/deleterole/{id}", method = RequestMethod.GET)
		public ResponseEntity<Role> deleteRole(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
			HttpSession userSession = request.getSession(false);
			Role role = roleService.getById(id);
			if (role == null) {
				log.debug("Role with id " + id + " does not exists");
				return new ResponseEntity("Role does not exist",HttpStatus.OK);
			} else {
				//roleService.delete(id);
				 role.setDelete_status(1);
				 Date date = new Date();
				 role.setDeleted_on(dateFormat.format(date));
			roleService.saveRole(role);
				log.debug("Role with id " + id + " deleted");
				return new ResponseEntity("Role deleted successfully",HttpStatus.OK);
			}
		}
	
	}
