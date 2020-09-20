package com.nexiilabs.stp.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "stp_customer")
public class CreateCustomerModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private int customerId;
	@Column(name = "customer_name")
	private String customerName;
	@Column(name = "customer_location")
	private String customerLocation;
	@Column(name = "contact_number")
	private String contactNumber;
	@Column(name = "contact_person_name")
	private String contactPerson;
	@Transient
	private int delete_status;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "updated_by")
	private int updatedBy;
	@Column(name = "deleted_by")
	private int deletedBy;
	@Column(name = "state_name")
	private String state;
	@Column(name = "state_code")
	private String stateCode;
	@Column(name = "pan_number")
	private String panNumber;
	@Column(name = "gst_in")
	private String gstIn;
	@Column(name = "billing_add_lane1")
	private String billingAddressLane1;
	@Column(name = "billing_add_lane2")
	private String billingAddressLane2;
	@Column(name = "billing_add_pincode")
	private String billingAddressPinCode;
	@Column(name = "billing_add_state")
	private String billingAddressState;
	@Column(name = "ship_add_lane1")
	private String shippingAddressLane1;
	@Column(name = "ship_add_lane2")
	private String shippingAddressLane2;
	@Column(name = "ship_add_state")
	private String shippingAddressState;
	@Column(name = "ship_add_pincode")
	private String shippingAddressPinCode;
	@Column(name = "vendor_add_lane1")
	private String vendorAddressLane1;
	@Column(name = "vendor_add_lane2")
	private String vendorAddressLane2;
	@Column(name = "vendor_add_state")
	private String vendorAddressState;
	@Column(name = "vendor_add_pincode")
	private String vendorAddressPinCode;
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
	public String getCustomerLocation() {
		return customerLocation;
	}
	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
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
	public int getDelete_status() {
		return delete_status;
	}
	public void setDelete_status(int delete_status) {
		this.delete_status = delete_status;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateCustomerModel [customerId=");
		builder.append(customerId);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", customerLocation=");
		builder.append(customerLocation);
		builder.append(", contactNumber=");
		builder.append(contactNumber);
		builder.append(", contactPerson=");
		builder.append(contactPerson);
		builder.append(", delete_status=");
		builder.append(delete_status);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append(", deletedBy=");
		builder.append(deletedBy);
		builder.append(", state=");
		builder.append(state);
		builder.append(", stateCode=");
		builder.append(stateCode);
		builder.append(", panNumber=");
		builder.append(panNumber);
		builder.append(", gstIn=");
		builder.append(gstIn);
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
		builder.append("]");
		return builder.toString();
	}

}
