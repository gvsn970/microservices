package com.nexiilabs.stp.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stp_employee")
public class EmployeeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private int employeeId;
	@Column(name = "emp_fname")
	private String firstName;
	@Column(name = "emp_lname")
	private String lastName;
	@Column(name = "emp_name")
	private String empName;
	@Column(name = "emp_email_id")
	private String empEmailID;
	@Column(name = "emp_contact")
	private String empContact;
	/*@Column(name = "fk_requirement_id")
	private String requirementId;*/
	@Column(name = "emp_joining_date")
	private String empJoiningDate;
	@Column(name = "fk_po_id")
	private int poId;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "updated_by")
	private int updatedBy;
	@Column(name = "deleted_by")
	private int deletedBy;
	@Column(name = "delete_status")
	private int deleteStatus;
	@Column(name = "experience_level")
	private String experienceLevel;
	@Column(name = "skill_set")
	private String skillSet;
	@Column(name = "sow_status")
	private int sowStatus;
	@Column(name = "po_status")
	private int poStatus;

	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpEmailID() {
		return empEmailID;
	}
	public void setEmpEmailID(String empEmailID) {
		this.empEmailID = empEmailID;
	}
	public String getEmpContact() {
		return empContact;
	}
	public void setEmpContact(String empContact) {
		this.empContact = empContact;
	}
	public String getEmpJoiningDate() {
		return empJoiningDate;
	}
	public void setEmpJoiningDate(String empJoiningDate) {
		this.empJoiningDate = empJoiningDate;
	}
	public int getPoId() {
		return poId;
	}
	public void setPoId(int poId) {
		this.poId = poId;
	}
	
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(int deletedBy) {
		this.deletedBy = deletedBy;
	}
	public int getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
	public String getExperienceLevel() {
		return experienceLevel;
	}
	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}
	public String getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}
	
	public int getSowStatus() {
		return sowStatus;
	}
	public void setSowStatus(int sowStatus) {
		this.sowStatus = sowStatus;
	}
	public int getPoStatus() {
		return poStatus;
	}
	public void setPoStatus(int poStatus) {
		this.poStatus = poStatus;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeModel [employeeId=");
		builder.append(employeeId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", empName=");
		builder.append(empName);
		builder.append(", empEmailID=");
		builder.append(empEmailID);
		builder.append(", empContact=");
		builder.append(empContact);
		builder.append(", empJoiningDate=");
		builder.append(empJoiningDate);
		builder.append(", poId=");
		builder.append(poId);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append(", deletedBy=");
		builder.append(deletedBy);
		builder.append(", deleteStatus=");
		builder.append(deleteStatus);
		builder.append(", experienceLevel=");
		builder.append(experienceLevel);
		builder.append(", skillSet=");
		builder.append(skillSet);
		builder.append(", sowStatus=");
		builder.append(sowStatus);
		builder.append(", poStatus=");
		builder.append(poStatus);
		builder.append("]");
		return builder.toString();
	}
	
}
