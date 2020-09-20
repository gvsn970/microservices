package com.snkit.springbootdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
	
	public UserEntity findByName(String name);
	
	
	
	@Query("select ue from UserEntity ue join fetch ue.addressEntity where ue.name=:firstname")
	public UserEntity getUserEntity(@Param ("firstname") String name);

}


