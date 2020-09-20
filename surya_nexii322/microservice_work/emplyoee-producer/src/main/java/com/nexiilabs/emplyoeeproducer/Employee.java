package com.nexiilabs.emplyoeeproducer;

public class Employee {
	
	public Employee() {
		
	}
	
	
public Employee(String name, String desg) {
		super();
		this.name = name;
		this.desg = desg;
		
	}


private int empId;
private String name;
private String desg;
private int requestId;

public int getRequestId() {
	return requestId;
}
public void setRequestId(int requestId) {
	this.requestId = requestId;
}
public int getEmpId() {
	return empId;
}
public void setEmpId(int empId) {
	this.empId = empId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDesg() {
	return desg;
}
public void setDesg(String desg) {
	this.desg = desg;
}

}
