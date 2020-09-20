package com.nexiilabs.emplyoeeproducer;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
	int requestId=0;
	public List<Employee> listEmployee(){
		List<Employee> list=new ArrayList<Employee>();
		Employee e=new Employee();
		e.setEmpId(1);
		e.setName("surya");
		e.setDesg("mts-1");
		e.setRequestId(requestId++);
		list.add(e);
		Employee e1=new Employee();
		e1.setEmpId(2);
		e1.setName("venkat");
		e1.setDesg("mts-2");
		e1.setRequestId(requestId++);
		list.add(e1);
		return list;
		
	}

}
