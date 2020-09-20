package com.snkit.springbootdatajpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<UserEntity> {
	UserReq req;
	
	public UserSpecification(UserReq req) {
		this.req = req;
	}

	@Override
	public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		 Predicate p = cb.conjunction();
	Join<UserEntity,AddressEntity>  userAddJoin = 	root.join("addressEntity",JoinType.INNER);
	
	  if (req.getName() != null) {
		  p.getExpressions().add(cb.equal(root.get("name"),req.getName()));
	  }
		
	  if (req.getCity() != null) {
		  p.getExpressions().add(cb.equal(userAddJoin.get("city"),req.getCity()));
	  }
		
		return p;
	}

}
