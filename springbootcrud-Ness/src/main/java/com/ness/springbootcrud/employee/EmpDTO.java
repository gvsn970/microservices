package com.ness.springbootcrud.employee;

public class EmpDTO {

	private int empId;
	private String empName;
	private String email;
	private String phoneNumber;
	private String imageUrl;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "EmpDTO [empId=" + empId + ", empName=" + empName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", imageUrl=" + imageUrl + "]";
	}
	
		
	
}
