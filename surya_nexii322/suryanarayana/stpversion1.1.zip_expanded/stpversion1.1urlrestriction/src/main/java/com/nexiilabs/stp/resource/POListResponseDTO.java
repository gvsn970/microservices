package com.nexiilabs.stp.resource;

public class POListResponseDTO {
private int poId;
private String poNumber;
private String description;
private String supplierRefNum;
private String raisedBy;
private String raisedOn;
private String currency;
public int getPoId() {
	return poId;
}
public void setPoId(int poId) {
	this.poId = poId;
}
public String getPoNumber() {
	return poNumber;
}
public void setPoNumber(String poNumber) {
	this.poNumber = poNumber;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getSupplierRefNum() {
	return supplierRefNum;
}
public void setSupplierRefNum(String supplierRefNum) {
	this.supplierRefNum = supplierRefNum;
}
public String getRaisedBy() {
	return raisedBy;
}
public void setRaisedBy(String raisedBy) {
	this.raisedBy = raisedBy;
}
public String getRaisedOn() {
	return raisedOn;
}
public void setRaisedOn(String raisedOn) {
	this.raisedOn = raisedOn;
}
public String getCurrency() {
	return currency;
}
public void setCurrency(String currency) {
	this.currency = currency;
}
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("POListModel [poId=");
	builder.append(poId);
	builder.append(", poNumber=");
	builder.append(poNumber);
	builder.append(", description=");
	builder.append(description);
	builder.append(", supplierRefNum=");
	builder.append(supplierRefNum);
	builder.append(", raisedBy=");
	builder.append(raisedBy);
	builder.append(", raisedOn=");
	builder.append(raisedOn);
	builder.append(", currency=");
	builder.append(currency);
	builder.append("]");
	return builder.toString();
}

}
