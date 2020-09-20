package com.nexiilabs.stp.prospect;

public class ProspectListResposeDTO {
private int prospectId;
private String prospectName;
private String companyName;
private String email;
private String createdOn;
private int prospectStatus;
private int fkCompanyId;
private String firstName;
private String lastName;
private String phoneNumber;
private String alternateNumber;
private String address1;
private String address2;
private String designation;
private String additionalInfo;
private String createdBy;
public int getProspectId() {
	return prospectId;
}
public void setProspectId(int prospectId) {
	this.prospectId = prospectId;
}
public String getProspectName() {
	return prospectName;
}
public void setProspectName(String prospectName) {
	this.prospectName = prospectName;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getCreatedOn() {
	return createdOn;
}
public void setCreatedOn(String createdOn) {
	this.createdOn = createdOn;
}

public int getProspectStatus() {
	return prospectStatus;
}
public void setProspectStatus(int prospectStatus) {
	this.prospectStatus = prospectStatus;
}

public int getFkCompanyId() {
	return fkCompanyId;
}
public void setFkCompanyId(int fkCompanyId) {
	this.fkCompanyId = fkCompanyId;
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
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getAlternateNumber() {
	return alternateNumber;
}
public void setAlternateNumber(String alternateNumber) {
	this.alternateNumber = alternateNumber;
}
public String getAddress1() {
	return address1;
}
public void setAddress1(String address1) {
	this.address1 = address1;
}
public String getAddress2() {
	return address2;
}
public void setAddress2(String address2) {
	this.address2 = address2;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public String getAdditionalInfo() {
	return additionalInfo;
}
public void setAdditionalInfo(String additionalInfo) {
	this.additionalInfo = additionalInfo;
}
public String getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("ProspectListResposeDTO [prospectId=");
	builder.append(prospectId);
	builder.append(", prospectName=");
	builder.append(prospectName);
	builder.append(", companyName=");
	builder.append(companyName);
	builder.append(", email=");
	builder.append(email);
	builder.append(", createdOn=");
	builder.append(createdOn);
	builder.append(", prospectStatus=");
	builder.append(prospectStatus);
	builder.append(", fkCompanyId=");
	builder.append(fkCompanyId);
	builder.append(", firstName=");
	builder.append(firstName);
	builder.append(", lastName=");
	builder.append(lastName);
	builder.append(", phoneNumber=");
	builder.append(phoneNumber);
	builder.append(", alternateNumber=");
	builder.append(alternateNumber);
	builder.append(", address1=");
	builder.append(address1);
	builder.append(", address2=");
	builder.append(address2);
	builder.append(", designation=");
	builder.append(designation);
	builder.append(", additionalInfo=");
	builder.append(additionalInfo);
	builder.append(", createdBy=");
	builder.append(createdBy);
	builder.append("]");
	return builder.toString();
}
}
