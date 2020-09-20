package com.nexiilabs.stp.role;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexiilabs.stp.util.RBackUtility;

@RestController
@RequestMapping("/crud")
public class RoleCRUDController {
	private static final Logger log=LogManager.getLogger(RoleCRUDController.class);
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	RoleService roleService;
		
	
	@Autowired
	RoleCRUDService roleCRUDService;
	
	
	@RequestMapping("/welcome")
		public String getMsg(){
		return "Welcome !";
	}
	
	@GetMapping("/getallroles")
	public List<Role> getAllRoles(HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		List<Role> roles=null;
		try{
			if(userSession!=null){
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("User Management")){
					roles = roleService.getAllRoles();
					if (roles.isEmpty()) {
						log.debug("Roles does not exists");
					}
				}
			}
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return roles;
	}
	
	@GetMapping("/getallpermissions")
	public List<Permissions> getAllPermissions(HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		try{
			if(userSession!=null){
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("User Management")){
					return roleCRUDService.getAllPermissionsList();
				}
			}
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return null;
	}
	
	@GetMapping("/addrole")
	public ResponsePojo addRole(@RequestParam("roleName") String roleName,@RequestParam("permissions") String permissions,HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		ResponsePojo responsePojo = null;
			try{
				responsePojo=new ResponsePojo();
				if(userSession!=null){
					List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
					if(menuList.contains("User Management")){
						
						RoleCRUD roleCRUD=new RoleCRUD();
						roleCRUD.setRole_name(roleName);
						roleCRUD.setFk_permission_id(permissions);
						int role_check=roleService.checkRoleExistency(roleCRUD);
						System.err.println("role_check!!!!!!!!!!"+role_check);
						if(role_check!=0){
							responsePojo.setStatusCode(RBackUtility.ROLE_EXIST_STATUS_CODE);
							responsePojo.setMessage(RBackUtility.ROLE_EXIST);
						}else{
							RoleCRUD newRole= roleCRUDService.addRole(roleCRUD);
							if(newRole!=null){
								responsePojo.setStatusCode(RBackUtility.ROLE_SUCCESS_STATUS_CODE);
								responsePojo.setMessage(RBackUtility.ROLE_SUCCESS);
							}
						}
					}else{
						responsePojo.setStatusCode(RBackUtility.AUTHENTICATION_FAIL_STATUS_CODE);
						responsePojo.setMessage(RBackUtility.AUTHENTICATION_FAIL);
					}
				}else{
					responsePojo.setStatusCode(RBackUtility.SESSION_FAIL_STATUS_CODE);
					responsePojo.setMessage(RBackUtility.SESSION_FAIL);
				}
			}catch(Exception e){
				log.error(e.getMessage());
			}
		return responsePojo;
	}
	
	@PutMapping("/updaterole")
	public ResponsePojo updateRole(@RequestParam("id") int roleId,@RequestParam("roleName") String roleName,@RequestParam("permissions") String permissions,HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		ResponsePojo responsePojo = null;
		int role_check=0;
			try{
				responsePojo=new ResponsePojo();
				if(userSession!=null){
					List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
					if(menuList.contains("User Management")){
						
						RoleCRUD role=new RoleCRUD();
						role.setRole_id(roleId);
						role.setRole_name(roleName);
						role.setFk_permission_id(permissions);
						int roleid=roleService.checkRoleExistency(role);
						System.err.println("roleid::::::::::"+roleid);
						//if role not found with given name then update else check menu permissions changed or not if changed go for update else give error message
						if(roleid==0){
							Date date = new Date();
							role.setUpdated_on(dateFormat.format(date));
							System.out.println(dateFormat.format(date));
							RoleCRUD newRole= roleCRUDService.updateRole(roleId ,role);
							if(newRole!=null){
								responsePojo.setStatusCode(RBackUtility.ROLE_SUCCESS_STATUS_CODE);
								responsePojo.setMessage(RBackUtility.ROLE_UPDATE_SUCCESS);
							}
							else{
								responsePojo.setStatusCode(RBackUtility.ROLE_EXIST_STATUS_CODE);
								responsePojo.setMessage(RBackUtility.ROLE_UPDATE_FAIL);
							}
						}
						else if(roleid!=role.getRole_id()){
							//role_check=role.getRole_id();
							responsePojo.setStatusCode(RBackUtility.ROLE_EXIST_STATUS_CODE);
							responsePojo.setMessage(RBackUtility.ROLE_EXIST);
						}else{
							role_check=roleService.checkRoleExistencyForUpdate(role);
							if(role_check!=0){
								responsePojo.setStatusCode(RBackUtility.ROLE_EXIST_STATUS_CODE);
								responsePojo.setMessage(RBackUtility.ROLE_UPDATE_NOCHANGE);
							}else{
								Date date = new Date();
								role.setUpdated_on(dateFormat.format(date));
								System.out.println(dateFormat.format(date));
								RoleCRUD newRole= roleCRUDService.updateRole(roleId ,role);
								if(newRole!=null){
									responsePojo.setStatusCode(RBackUtility.ROLE_SUCCESS_STATUS_CODE);
									responsePojo.setMessage(RBackUtility.ROLE_UPDATE_SUCCESS);
								}
								else{
									responsePojo.setStatusCode(RBackUtility.ROLE_EXIST_STATUS_CODE);
									responsePojo.setMessage(RBackUtility.ROLE_UPDATE_FAIL);
								}
							}
						}
					}else{
						responsePojo.setStatusCode(RBackUtility.AUTHENTICATION_FAIL_STATUS_CODE);
						responsePojo.setMessage(RBackUtility.AUTHENTICATION_FAIL);
					}
				}else{
					responsePojo.setStatusCode(RBackUtility.SESSION_FAIL_STATUS_CODE);
					responsePojo.setMessage(RBackUtility.SESSION_FAIL);
				}
				System.err.println("role_check!!!!!!!!!!"+role_check);
			}catch(Exception e){
				log.error(e.getMessage());
			}
		
		return responsePojo;
	}
	
	@RequestMapping(value = "/getrolebyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<Role> getRoleById(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		Role role=null;
			try{
				if(userSession!=null){
					List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
					if(menuList.contains("User Management")){
						role = roleService.getById(id);
						if (role == null) {
							return new ResponseEntity(RBackUtility.DATA_IS_EMPTY, HttpStatus.OK);
						}
					}
				}
			}catch(Exception e){
				log.error(e.getMessage());
			}
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}
	/*@GetMapping("/getrole/{id}")
	public RoleCRUD getRoleById(@PathVariable("id") int roleId){
		return roleCRUDService.getRoleById(roleId);
		
	}*/
	
	@DeleteMapping("/deleterole/{id}")
	public ResponsePojo deleteRole(@PathVariable("id") int roleId,HttpServletRequest request,HttpServletResponse response){
		HttpSession userSession = request.getSession(false);
		ResponsePojo responsePojo = null;
		try{
			responsePojo=new ResponsePojo();
			if(userSession!=null){
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("User Management")){
					int userid=roleService.checkUserExistencByRole(roleId);
					if(userid!=0){
						responsePojo.setStatusCode(RBackUtility.ROLE_ASSOCIATE_STATUS_CODE);
						responsePojo.setMessage(RBackUtility.ROLE_ASSOCIATE_SUCCESS);
					}else{
						//roleCRUDService.deleteRole(roleId);
						RoleCRUD role = roleCRUDService.getRoleById(roleId);
						System.out.println(roleId+" and "+role);
						if(role!=null){
							Date date = new Date();
							role.setDelete_status(1);
							role.setDeleted_on(dateFormat.format(date));
							RoleCRUD newRole= roleCRUDService.updateRole(roleId ,role);
							System.out.println("after delete"+newRole.getDelete_status());
							if(newRole!=null){
								responsePojo.setStatusCode(RBackUtility.ROLE_SUCCESS_STATUS_CODE);
								responsePojo.setMessage(RBackUtility.ROLE_DELETE_SUCCESS);
							}
						}
					}
				}else{
					responsePojo.setStatusCode(RBackUtility.AUTHENTICATION_FAIL_STATUS_CODE);
					responsePojo.setMessage(RBackUtility.AUTHENTICATION_FAIL);
				}
			}else{
				responsePojo.setStatusCode(RBackUtility.SESSION_FAIL_STATUS_CODE);
				responsePojo.setMessage(RBackUtility.SESSION_FAIL);
			}
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return responsePojo;
		
	}
	
	}
