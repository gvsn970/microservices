package com.ness.springbootcrud.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emp")
public class EmpModel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="emp_id")
	private int empId;
    
    @Column(name="email")
    private String email;
    
    @Column(name="name")
    private String name;
    
    @Column(name="phonenumber")
    private String phonenumber;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Override
	public String toString() {
		return "EmpModel [empId=" + empId + ", email=" + email + ", name=" + name + ", phonenumber=" + phonenumber
				+ "]";
	}

    

	
	
}
