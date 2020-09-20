package com.nexiilabs.stp.user;

public class UserResponseDTO {
private String message;
private int statusCode;
private int employeeId;
private int customerId;
private int poId;
private int filesUploadedforCustomer;
private int noOfSowfiles;
private int noOfPOfiles;
private int bankAccountActive;
private int taxDetailsActive;
private String uploadPath;
private int expireIn;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public int getStatusCode() {
	return statusCode;
}
public void setStatusCode(int statusCode) {
	this.statusCode = statusCode;
}

public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}

public int getFilesUploadedforCustomer() {
	return filesUploadedforCustomer;
}
public void setFilesUploadedforCustomer(int filesUploadedforCustomer) {
	this.filesUploadedforCustomer = filesUploadedforCustomer;
}

public int getPoId() {
	return poId;
}
public void setPoId(int poId) {
	this.poId = poId;
}

public int getNoOfSowfiles() {
	return noOfSowfiles;
}
public void setNoOfSowfiles(int noOfSowfiles) {
	this.noOfSowfiles = noOfSowfiles;
}

public int getNoOfPOfiles() {
	return noOfPOfiles;
}
public void setNoOfPOfiles(int noOfPOfiles) {
	this.noOfPOfiles = noOfPOfiles;
}

public int getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(int employeeId) {
	this.employeeId = employeeId;
}

public int getBankAccountActive() {
	return bankAccountActive;
}
public void setBankAccountActive(int bankAccountActive) {
	this.bankAccountActive = bankAccountActive;
}
public int getTaxDetailsActive() {
	return taxDetailsActive;
}
public void setTaxDetailsActive(int taxDetailsActive) {
	this.taxDetailsActive = taxDetailsActive;
}

public String getUploadPath() {
	return uploadPath;
}
public void setUploadPath(String uploadPath) {
	this.uploadPath = uploadPath;
}

public int getExpireIn() {
	return expireIn;
}
public void setExpireIn(int expireIn) {
	this.expireIn = expireIn;
}
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("UserResponseDTO [message=");
	builder.append(message);
	builder.append(", statusCode=");
	builder.append(statusCode);
	builder.append(", employeeId=");
	builder.append(employeeId);
	builder.append(", customerId=");
	builder.append(customerId);
	builder.append(", poId=");
	builder.append(poId);
	builder.append(", filesUploadedforCustomer=");
	builder.append(filesUploadedforCustomer);
	builder.append(", noOfSowfiles=");
	builder.append(noOfSowfiles);
	builder.append(", noOfPOfiles=");
	builder.append(noOfPOfiles);
	builder.append(", bankAccountActive=");
	builder.append(bankAccountActive);
	builder.append(", taxDetailsActive=");
	builder.append(taxDetailsActive);
	builder.append(", uploadPath=");
	builder.append(uploadPath);
	builder.append(", expireIn=");
	builder.append(expireIn);
	builder.append("]");
	return builder.toString();
}

}
