package com.nexiilabs.stp.resource;

public class EmployeeListResponseDTO {
	private int employeeId;
	private String employeeName;
	private String emailID;
	private String contactNumber;
	private String projectName;
	private String joiningDate;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeListResponseDTO [employeeId=");
		builder.append(employeeId);
		builder.append(", employeeName=");
		builder.append(employeeName);
		builder.append(", emailID=");
		builder.append(emailID);
		builder.append(", contactNumber=");
		builder.append(contactNumber);
		builder.append(", projectName=");
		builder.append(projectName);
		builder.append(", joiningDate=");
		builder.append(joiningDate);
		builder.append("]");
		return builder.toString();
	}
	
}
