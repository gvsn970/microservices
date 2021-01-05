package com.masters.accounting.finance.onpassive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masters.accounting.finance.onpassive.entity.AllMasters;


@Repository
public interface AllMasterRepository extends JpaRepository<AllMasters, Integer>{

}
