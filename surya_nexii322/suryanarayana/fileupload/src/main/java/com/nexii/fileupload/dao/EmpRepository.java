package com.nexii.fileupload.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nexii.fileupload.model.EmpModel;

@Repository
public interface EmpRepository  extends CrudRepository<EmpModel, Integer>{

}
