package com.nexiilabs.stp.role;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nexiilabs.stp.user.CreateUserModel;
@Transactional
@Repository
public class RoleRepositoryImpl implements RoleRepository {
	private static final Logger log=LogManager.getLogger(RoleRepositoryImpl.class);
	@PersistenceContext	
	private EntityManager entityManager;
	
	
	@Override
	public void saveRole(Role role) {
		try{
			 entityManager.persist(role);
		}
		catch(Exception e){
			System.out.println("in exception!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			log.error(e.getMessage());
		}
	}
	
	@Override
	public Role getById(int id) {
		Role role=null;
		try{
		 role = entityManager.find(Role.class, id);
		}
		catch(Exception e){
			System.out.println("in exception!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			log.error(e.getMessage());
		}
		return role;
	}

	@Override
	public void delete(int id) {
		try{
			 entityManager.remove(id);
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> rolesList=new ArrayList<Role>();
		try{
		String hql = "select r.role_id,r.role_name,GROUP_CONCAT(p.permission_name) as permissions,r.fk_permission_id from stp_role r,stp_permissions p where r.delete_status=0 and FIND_IN_SET(p.permission_id, r.fk_permission_id) GROUP BY r.role_id";
		List<Object> pojectlist = entityManager.createNativeQuery(hql).getResultList();
		if(pojectlist.size()>0){
		Iterator itr = pojectlist.iterator();
		while(itr.hasNext()){
		   Object[] obj = (Object[]) itr.next();
		   Integer role_id = Integer.parseInt(String.valueOf(obj[0])); //SERVICE assumed as int
		   Role rolemodel=new Role();
		   rolemodel.setRole_id(role_id);
		   rolemodel.setRole_name(String.valueOf(obj[1]));
		   rolemodel.setFk_permission_id(String.valueOf(obj[2]));
		   rolemodel.setPermission_ids(String.valueOf(obj[3]));
		   rolesList.add(rolemodel);
		}
		}
		}catch(Exception e){
			log.error(e.getMessage());
		}
	return rolesList;
	}

	@Override
	public Role checkRoleExistency(Role role) {
		try{
			String hql = "select r.role_id from stp_role r where r.role_name="+role.getRole_name() +" and r.fk_permission_id="+role.getFk_permission_id()+" and r.delete_status=0";
			 role = (Role) entityManager.createNativeQuery(hql).getResultList();
			}catch(Exception e){
				log.error(e.getMessage());
			}
		return role;
	}

	/*@Override
	public int checkRoleExistency(RoleCRUD role) {
		int id=0;
		try{
			String hql = "select r.role_id from stp_role r where r.role_name='"+role.getRole_name()+"' and r.delete_status=0";
			List<RoleCRUD> rolelist= entityManager.createNativeQuery(hql).getResultList();
			RoleCRUD role_check=rolelist.get(0);
			System.err.println("rolename "+role_check.getRole_name());
			 if(role_check!=null){
				 id=role_check.getRole_id(); 
			 }
			}catch(Exception e){
				log.error(e.getMessage());
			}
		return id;
	}*/
	
	@Override
	public int checkRoleExistency(RoleCRUD role) {
		int id=0;
		try{
			String hql = "from RoleCRUD where role_name=:role_name and delete_status=:delete_status";
			Query query= entityManager.createQuery(hql);
			query.setParameter("role_name", role.getRole_name());
			query.setParameter("delete_status", 0);
			List<RoleCRUD> role_check=query.getResultList();
			role=role_check.get(0);
			 if(role!=null){
				 id=role.getRole_id(); 
			 }
			}catch(Exception e){
				log.error(e.getMessage());
			}
		return id;
	}
	@Override
	public int checkUserExistencByRole(int roleId) {
		int id=0;
		try{
			String hql = "from CreateUserModel where roleId=:roleId and delete_status=:delete_status";
			Query query= entityManager.createQuery(hql);
			query.setParameter("roleId", roleId);
			query.setParameter("delete_status", 0);
			List<CreateUserModel> user_check=query.getResultList();
			CreateUserModel user=user_check.get(0);
			 if(user!=null){
				 id=user.getUserId(); 
			 }
			}catch(Exception e){
				log.error(e.getMessage());
			}
		return id;
	}
	
	/*@Override
	public int checkRoleExistencyForUpdate(RoleCRUD role) {
		int id=0;
		try{
			String hql = "from RoleCRUD where role_name=:role_name and role_id NOT IN(:role_id) and delete_status=:delete_status";
			Query query= entityManager.createQuery(hql);
			query.setParameter("role_name", role.getRole_name());
			query.setParameter("delete_status", 0);
			query.setParameter("role_id", role.getRole_id());
			List<RoleCRUD> rolelist=query.getResultList();
			RoleCRUD role_check=rolelist.get(0);
			System.err.println("role_check<<<<<<<<<<<<<<<<<<<<<<<<<<"+role_check);
			 if(role_check!=null){
				 id=role_check.getRole_id(); 
			 }else{
				 String hql1 = "from RoleCRUD where role_name=:role_name and fk_permission_id=:fk_permission_id and delete_status=:delete_status";
				 Query query1= entityManager.createQuery(hql1);
					query1.setParameter("role_name", role.getRole_name());
					query1.setParameter("delete_status", 0);
					query1.setParameter("fk_permission_id", role.getFk_permission_id());
					List<RoleCRUD> rolelist1=query1.getResultList();
					RoleCRUD role_check1=rolelist1.get(0);
					 if(role_check1!=null){
						 id=role_check1.getRole_id(); 
					 }
			 }
			}catch(Exception e){
				log.error(e.getMessage());
			}
		return id;
	}*/
	@Override
	public int checkRoleExistencyForUpdate(RoleCRUD role) {
		int id=0;
		try{
				 String hql1 = "from RoleCRUD where role_name=:role_name and fk_permission_id=:fk_permission_id and delete_status=:delete_status";
				 Query query1= entityManager.createQuery(hql1);
					query1.setParameter("role_name", role.getRole_name());
					query1.setParameter("delete_status", 0);
					query1.setParameter("fk_permission_id", role.getFk_permission_id());
					List<RoleCRUD> rolelist1=query1.getResultList();
					RoleCRUD role_check1=rolelist1.get(0);
					System.err.println("in role_check1"+role_check1.getRole_id());
					
					 if(role_check1!=null){
						 id=role_check1.getRole_id(); 
					 }
			}catch(Exception e){
				log.error(e.getMessage());
			}
		return id;
	}
	/*@Override
	public int checkRoleExistencyForUpdate(RoleCRUD role) {
		int id=0;
		try{
			String hql = "select r.role_id from stp_role r where r.role_name='"+role.getRole_name()+"' and r.role_id NOT IN("+role.getRole_id()+") and r.delete_status=0";
			RoleCRUD role_check= (RoleCRUD) entityManager.createNativeQuery(hql).getSingleResult();
			 if(role_check!=null){
				 id=role_check.getRole_id(); 
			 }else{
				 String hql1 = "select r.role_id from stp_role r where r.role_name='"+role.getRole_name() +"' and r.fk_permission_id='"+role.getFk_permission_id()+"' and r.delete_status=0";
				 RoleCRUD roleExistencycheck = (RoleCRUD) entityManager.createNativeQuery(hql1).getResultList();
				 id=roleExistencycheck.getRole_id();
			 }
			}catch(Exception e){
				log.error(e.getMessage());
			}
		return id;
	}*/

}
