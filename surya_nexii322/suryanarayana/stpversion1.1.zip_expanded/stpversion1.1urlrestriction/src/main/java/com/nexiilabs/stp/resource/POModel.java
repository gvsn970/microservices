package com.nexiilabs.stp.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stp_po")
public class POModel {

	@Id
	@Column(name = "po_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int poId;
	@Column(name = "description")
	private String description;
	@Column(name = "po_number")
	private String poNumber;
	@Column(name = "supplier_ref_number")
	private String supplierRefNumber;
	@Column(name = "raised_on")
	private String raisedOn;
	@Column(name = "raised_by")
	private String raisedBy;
	@Column(name = "currency")
	private String currency;
	@Column(name = "unit_price")
	private double unitPrice;
	@Column(name = "duration_months")
	private int durationMonths;
	@Column(name = "start_date")
	private String startDate;
	@Column(name = "end_date")
	private String endDate;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "updated_by")
	private int updatedBy;
	@Column(name = "deleted_by")
	private int deletedBy;
	@Column(name = "delete_status")
	private int deleteStatus;
	@Column(name = "expiry_status")
	private int expiryStatus;
	@Column(name = "fk_employee_id")
	private int employeeId;
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
	@Column(name = "shipping_state_name")
	private String shippingState;
	@Column(name = "shipping_state_code")
	private String shippingStateCode;
	@Column(name = "shipping_pan_number")
	private String shippingPanNumber;
	@Column(name = "shipping_gst_in")
	private String shippingGstIn;
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

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getSupplierRefNumber() {
		return supplierRefNumber;
	}

	public void setSupplierRefNumber(String supplierRefNumber) {
		this.supplierRefNumber = supplierRefNumber;
	}

	public String getRaisedOn() {
		return raisedOn;
	}

	public void setRaisedOn(String raisedOn) {
		this.raisedOn = raisedOn;
	}

	public String getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getDurationMonths() {
		return durationMonths;
	}

	public void setDurationMonths(int durationMonths) {
		this.durationMonths = durationMonths;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public int getExpiryStatus() {
		return expiryStatus;
	}

	public void setExpiryStatus(int expiryStatus) {
		this.expiryStatus = expiryStatus;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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

	public String getShippingState() {
		return shippingState;
	}

	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	public String getShippingStateCode() {
		return shippingStateCode;
	}

	public void setShippingStateCode(String shippingStateCode) {
		this.shippingStateCode = shippingStateCode;
	}

	public String getShippingPanNumber() {
		return shippingPanNumber;
	}

	public void setShippingPanNumber(String shippingPanNumber) {
		this.shippingPanNumber = shippingPanNumber;
	}

	public String getShippingGstIn() {
		return shippingGstIn;
	}

	public void setShippingGstIn(String shippingGstIn) {
		this.shippingGstIn = shippingGstIn;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("POModel [poId=");
		builder.append(poId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", poNumber=");
		builder.append(poNumber);
		builder.append(", supplierRefNumber=");
		builder.append(supplierRefNumber);
		builder.append(", raisedOn=");
		builder.append(raisedOn);
		builder.append(", raisedBy=");
		builder.append(raisedBy);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", unitPrice=");
		builder.append(unitPrice);
		builder.append(", durationMonths=");
		builder.append(durationMonths);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append(", deletedBy=");
		builder.append(deletedBy);
		builder.append(", deleteStatus=");
		builder.append(deleteStatus);
		builder.append(", expiryStatus=");
		builder.append(expiryStatus);
		builder.append(", employeeId=");
		builder.append(employeeId);
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
		builder.append(", shippingState=");
		builder.append(shippingState);
		builder.append(", shippingStateCode=");
		builder.append(shippingStateCode);
		builder.append(", shippingPanNumber=");
		builder.append(shippingPanNumber);
		builder.append(", shippingGstIn=");
		builder.append(shippingGstIn);
		builder.append("]");
		return builder.toString();
	}

	
}
