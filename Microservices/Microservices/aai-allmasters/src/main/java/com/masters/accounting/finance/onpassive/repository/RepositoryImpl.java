package com.masters.accounting.finance.onpassive.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.masters.accounting.finance.onpassive.entity.AddColumnResoponse;


@Repository
public class RepositoryImpl {

	@Autowired
	private EntityManager em;

	public AddColumnResoponse addColumn(String columName) {
		// Employee empEmail=employeeRepository.findByEmailId(email);
		AddColumnResoponse response=new AddColumnResoponse();
		Session session = em.unwrap(Session.class);
		Transaction tx = session.beginTransaction();
		String query = " ALTER TABLE all_masters_table ADD "+columName+" varchar(255);";
		Query q = session.createNativeQuery(query);
		tx.commit();
		
		return response;
	}
		
	
	public AddColumnResoponse updateColumn(String oldColumName,String newColumName) {
		AddColumnResoponse response=new AddColumnResoponse();
		Session session = em.unwrap(Session.class);
		Transaction tx = session.beginTransaction();
		String query = " ALTER TABLE all_masters_table RENAME COLUMN "+oldColumName+" TO "+newColumName+" varchar(255);";
		Query q = session.createNativeQuery(query);
		q.executeUpdate();
		tx.commit();
		
		return response;
	}
	
	public AddColumnResoponse deleteColumn(String columName) {
		AddColumnResoponse response=new AddColumnResoponse();
		Session session = em.unwrap(Session.class);
		Transaction tx = session.beginTransaction();
		String query = " ALTER TABLE all_masters_table DROP COLUMN "+columName;
		
		System.out.println("deleteColumn query===="+query);
		
		Query q = session.createNativeQuery(query);
		q.executeUpdate();
		tx.commit();
		
		return response;
	}
	
}
