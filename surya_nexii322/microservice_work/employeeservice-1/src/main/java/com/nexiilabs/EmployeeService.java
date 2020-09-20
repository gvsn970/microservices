package com.nexiilabs;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
	int empId=0;
	public List<Employee> listEmployee(){
		List<Employee> list=new ArrayList<Employee>();
		Employee e=new Employee();
		e.setEmpId(++empId);
		e.setName("Rahul");
		e.setDesg("mts-1");
		list.add(e);
		return list;
		
	}

}
