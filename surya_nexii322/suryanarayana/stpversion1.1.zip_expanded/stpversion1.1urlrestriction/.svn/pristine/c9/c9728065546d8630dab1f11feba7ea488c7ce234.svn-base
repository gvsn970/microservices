package com.nexiilabs.stp.authentication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nexiilabs.stp.role.Role;
import com.nexiilabs.stp.user.CreateUserModel;

@Transactional
@Repository
public class UtilitiesRepositoryImpl implements UtilitiesRepository {

	@PersistenceContext
	private EntityManager entityManager;
//change password method and forgot password update
	public boolean updatePassword(CreateUserModel userModel) {
		try{
			CreateUserModel userModel2 = entityManager.find(CreateUserModel.class, userModel.getUserId());
			if(userModel2!=null){
				userModel2.setUserPassword(userModel.getUserPassword());
				entityManager.persist(userModel2);
				return true;
			}else{
				return false;
			}
			}catch(Exception e){
				return false;
			}
		}
//Forgot Password mail checking
	@Override
	public CreateUserModel userExistancyCheck(String email) {
		CreateUserModel userModel=null;
		String hql="FROM CreateUserModel WHERE email=:email and delete_status=:delete_status";
		Query query = entityManager.createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("delete_status", 0);
		List<CreateUserModel> list = query.getResultList();
		if(!list.isEmpty()){
			userModel=list.get(0);
			return userModel;
		}
		return userModel;
	}
//get flasMailConfig for forgot password mail sending
	@Override
	public FlasMailConfig getflasMailConfig() {
		FlasMailConfig flasMailConfig =null;
		try{
			String hql="FROM FlasMailConfig";
			Query query = entityManager.createQuery(hql);
			List<FlasMailConfig> listflasMailConfig = query.getResultList();
			return flasMailConfig=listflasMailConfig.get(0);
		}catch(Exception e){
			return flasMailConfig;
		}
		
	}
	
	@Override
	public List<String> getMenuPermissions(int roleId) {
		List<String> menuList=null;
		try{
			String hql = "select p.permission_name from stp_role r,stp_permissions p WHERE r.delete_status=0 and r.role_id=? and FIND_IN_SET(p.permission_id, r.fk_permission_id) ";
				
			List<Object> menuPermissionList = entityManager.createNativeQuery(hql).setParameter(1,roleId).getResultList();
			if(menuPermissionList.size()>0){
				menuList=new ArrayList<String>();
				Iterator itr = menuPermissionList.iterator();
				while(itr.hasNext()){
					menuList.add(String.valueOf(itr.next()));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return menuList;
	}
}














