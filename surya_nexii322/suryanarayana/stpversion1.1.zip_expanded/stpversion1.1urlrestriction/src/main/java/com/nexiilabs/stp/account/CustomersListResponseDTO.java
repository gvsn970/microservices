package com.nexiilabs.stp.account;

public class CustomersListResponseDTO {
	private int customerId;
	private String customerName;
	private String contactNumber;
	private String contactPerson;
	private String location;
	private String fileIds;
	private String fileNames;
	private String billingAddressLane1;
	private String billingAddressLane2;
	private String billingAddressPinCode;
	private String billingAddressState;
	private String shippingAddressLane1;
	private String shippingAddressLane2;
	private String shippingAddressState;
	private String shippingAddressPinCode;
	private String vendorAddressLane1;
	private String vendorAddressLane2;
	private String vendorAddressState;
	private String vendorAddressPinCode;
	private String panNumber;
	private String gstIn;
	private String state;
	private String stateCode;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getFileIds() {
		return fileIds;
	}
	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}
	public String getFileNames() {
		return fileNames;
	}
	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}
	public String getBillingAddressLane1() {
		return billingAddressLane1;
	}
	public void setBillingAddressLane1(String billingAddressLane1) {
		this.billingAddressLane1 = billingAddressLane1;
	}
	public String getBillingAddressLane2() {
		return billingAddressLane2;
	}
	public void setBillingAddressLane2(String billingAddressLane2) {
		this.billingAddressLane2 = billingAddressLane2;
	}
	public String getBillingAddressPinCode() {
		return billingAddressPinCode;
	}
	public void setBillingAddressPinCode(String billingAddressPinCode) {
		this.billingAddressPinCode = billingAddressPinCode;
	}
	public String getBillingAddressState() {
		return billingAddressState;
	}
	public void setBillingAddressState(String billingAddressState) {
		this.billingAddressState = billingAddressState;
	}
	public String getShippingAddressLane1() {
		return shippingAddressLane1;
	}
	public void setShippingAddressLane1(String shippingAddressLane1) {
		this.shippingAddressLane1 = shippingAddressLane1;
	}
	public String getShippingAddressLane2() {
		return shippingAddressLane2;
	}
	public void setShippingAddressLane2(String shippingAddressLane2) {
		this.shippingAddressLane2 = shippingAddressLane2;
	}
	public String getShippingAddressState() {
		return shippingAddressState;
	}
	public void setShippingAddressState(String shippingAddressState) {
		this.shippingAddressState = shippingAddressState;
	}
	public String getShippingAddressPinCode() {
		return shippingAddressPinCode;
	}
	public void setShippingAddressPinCode(String shippingAddressPinCode) {
		this.shippingAddressPinCode = shippingAddressPinCode;
	}
	public String getVendorAddressLane1() {
		return vendorAddressLane1;
	}
	public void setVendorAddressLane1(String vendorAddressLane1) {
		this.vendorAddressLane1 = vendorAddressLane1;
	}
	public String getVendorAddressLane2() {
		return vendorAddressLane2;
	}
	public void setVendorAddressLane2(String vendorAddressLane2) {
		this.vendorAddressLane2 = vendorAddressLane2;
	}
	public String getVendorAddressState() {
		return vendorAddressState;
	}
	public void setVendorAddressState(String vendorAddressState) {
		this.vendorAddressState = vendorAddressState;
	}
	public String getVendorAddressPinCode() {
		return vendorAddressPinCode;
	}
	public void setVendorAddressPinCode(String vendorAddressPinCode) {
		this.vendorAddressPinCode = vendorAddressPinCode;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getGstIn() {
		return gstIn;
	}
	public void setGstIn(String gstIn) {
		this.gstIn = gstIn;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomersListResponseDTO [customerId=");
		builder.append(customerId);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", contactNumber=");
		builder.append(contactNumber);
		builder.append(", contactPerson=");
		builder.append(contactPerson);
		builder.append(", location=");
		builder.append(location);
		builder.append(", fileIds=");
		builder.append(fileIds);
		builder.append(", fileNames=");
		builder.append(fileNames);
		builder.append(", billingAddressLane1=");
		builder.append(billingAddressLane1);
		builder.append(", billingAddressLane2=");
		builder.append(billingAddressLane2);
		builder.append(", billingAddressPinCode=");
		builder.append(billingAddressPinCode);
		builder.append(", billingAddressState=");
		builder.append(billingAddressState);
		builder.append(", shippingAddressLane1=");
		builder.append(shippingAddressLane1);
		builder.append(", shippingAddressLane2=");
		builder.append(shippingAddressLane2);
		builder.append(", shippingAddressState=");
		builder.append(shippingAddressState);
		builder.append(", shippingAddressPinCode=");
		builder.append(shippingAddressPinCode);
		builder.append(", vendorAddressLane1=");
		builder.append(vendorAddressLane1);
		builder.append(", vendorAddressLane2=");
		builder.append(vendorAddressLane2);
		builder.append(", vendorAddressState=");
		builder.append(vendorAddressState);
		builder.append(", vendorAddressPinCode=");
		builder.append(vendorAddressPinCode);
		builder.append(", panNumber=");
		builder.append(panNumber);
		builder.append(", gstIn=");
		builder.append(gstIn);
		builder.append(", state=");
		builder.append(state);
		builder.append(", stateCode=");
		builder.append(stateCode);
		builder.append("]");
		return builder.toString();
	}
	
}