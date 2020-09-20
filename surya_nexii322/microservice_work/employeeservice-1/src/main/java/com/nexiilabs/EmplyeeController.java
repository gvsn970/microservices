package com.nexiilabs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmplyeeController {

	private EmployeeService employeeservice;
	
	EmplyeeController(@Autowired EmployeeService employeeservice){
		this.employeeservice=employeeservice;
	}
	
@GetMapping(value="/listEmp",produces="application/Json")
public List<Employee> getEmployee(){
	return employeeservice.listEmployee();
}
	
}
