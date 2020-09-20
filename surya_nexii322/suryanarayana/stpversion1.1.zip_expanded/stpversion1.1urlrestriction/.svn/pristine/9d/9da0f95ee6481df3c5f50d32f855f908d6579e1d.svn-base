package com.nexiilabs.stp.invoice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stp_invoice")
public class InvoiceModel {

	@Id
	@Column(name="invoice_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invoiceId;
	
	@Column(name="invoice_number")
	private String invoiceNumber;
	
	@Column(name="invoice_date")
	private String invoiceDate;
	
	@Column(name="po_number")
	private String poNumber;
	
	@Column(name="po_date")
	private String poDate;
	
	@Column(name="supplier_ref_number")
	private String supplierRefNumber;
	
	/*@Column(name="billing_address")
	private String billingAddress;
	
	@Column(name="shipping_address")
	private String shippingAddress;
	
	@Column(name="vendor_address")
	private String vendorAddress;*/
	
	@Column(name="fk_requirement_id")
	private int fkRequirementId;
	
	@Column(name="period")
	private String period;
	
	@Column(name="fk_employee_id")
	private int fkEmployeeId;
	
	@Column(name="hsn_sac")
	private String hsnSac;

	@Column(name="quantity")
	private int quantity;
	
	@Column(name="billing_days")
	private int billingDays;
	
	@Column(name="total_work_days")
	private int totalWorkDays;
	
	@Column(name="rate")
	private double rate;
	
	@Column(name="taxable_amount")
	private double taxableAmount;
	
	@Column(name="cgst_rate")
	private double cgst_rate;
	
	@Column(name="cgst_amount")
	private double cgstAmount;
	
	@Column(name="sgst_rate")
	private double sgstRate;
	
	@Column(name="sgst_amount")
	private double sgstAmount;
	
	@Column(name="igst_rate")
	private double igstRate;
	
	@Column(name="igst_amount")
	private double igstAmount;

	@Column(name="igst_applicable")
	private int igstApplicable;
	
	@Column(name="total_amount")
	private double totalAmount;

	@Column(name="account_holder_name")
	private String accountHolderName;
	
	@Column(name="bank_name")
	private String bankName;

	@Column(name="account_number")
	private String accountNumber;
	
	@Column(name="ifsc_code")
	private String ifscCode;

	@Column(name="fk_invoice_request_id")
	private int fkInvoiceRequestId;
	
	@Column(name="customer_pan")
	private String customerPan;
	
	@Column(name="customer_gstin")
	private String customerGstin;

	@Column(name="customer_state")
	private String customerState;
	
	@Column(name="customer_state_code")
	private String customerStateCode;

	@Column(name="our_pan")
	private String our_pan;
	
	@Column(name="our_gstin")
	private String ourGstin;
	
	@Column(name="our_state")
	private String ourState;

	@Column(name="our_state_code")
	private String ourStateCode;
	
	@Column(name="invoice_sentstatus")
	private int invoiceSentStatus;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="sent_on")
	private String sentOn;

	@Column(name="ship_add_lane1")
	private String shipAddLane1;
	
	@Column(name="ship_add_lane2")
	private String shipAddLane2;
	
	@Column(name="ship_add_state")
	private String shipAddState;

	@Column(name="ship_add_pincode")
	private String shipAddPincode;
	
	@Column(name="billing_add_lane1")
	private String billingAddLane1;
	
	@Column(name="billing_add_lane2")
	private String billingAddLane2;
	
	@Column(name="billing_add_state")
	private String billingAddState;

	@Column(name="billing_add_pincode")
	private String billingAddPincode;
	
	@Column(name="vendor_add_lane1")
	private String vendorAddLane1;
	
	@Column(name="vendor_add_lane2")
	private String vendorAddLane2;
	
	@Column(name="vendor_add_state")
	private String vendorAddState;

	@Column(name="vendor_add_pincode")
	private String vendorAddPincode;
	
	@Column(name="invoice_pdf_date")
	private String invoicePdfDate;
	
	@Column(name="shipping_pan")
	private String shippingPan;
	
	@Column(name="shipping_gstin")
	private String shippingGstin;
	
	@Column(name="shipping_state")
	private String shippingState;

	@Column(name="shipping_state_code")
	private String shippingStateCode;
	
	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public String getSupplierRefNumber() {
		return supplierRefNumber;
	}

	public void setSupplierRefNumber(String supplierRefNumber) {
		this.supplierRefNumber = supplierRefNumber;
	}

	public int getFkRequirementId() {
		return fkRequirementId;
	}

	public void setFkRequirementId(int fkRequirementId) {
		this.fkRequirementId = fkRequirementId;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getFkEmployeeId() {
		return fkEmployeeId;
	}

	public void setFkEmployeeId(int fkEmployeeId) {
		this.fkEmployeeId = fkEmployeeId;
	}

	public String getHsnSac() {
		return hsnSac;
	}

	public void setHsnSac(String hsnSac) {
		this.hsnSac = hsnSac;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBillingDays() {
		return billingDays;
	}

	public void setBillingDays(int billingDays) {
		this.billingDays = billingDays;
	}

	public int getTotalWorkDays() {
		return totalWorkDays;
	}

	public void setTotalWorkDays(int totalWorkDays) {
		this.totalWorkDays = totalWorkDays;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public double getCgst_rate() {
		return cgst_rate;
	}

	public void setCgst_rate(double cgst_rate) {
		this.cgst_rate = cgst_rate;
	}

	public double getCgstAmount() {
		return cgstAmount;
	}

	public void setCgstAmount(double cgstAmount) {
		this.cgstAmount = cgstAmount;
	}

	public double getSgstRate() {
		return sgstRate;
	}

	public void setSgstRate(double sgstRate) {
		this.sgstRate = sgstRate;
	}

	public double getSgstAmount() {
		return sgstAmount;
	}

	public void setSgstAmount(double sgstAmount) {
		this.sgstAmount = sgstAmount;
	}

	public double getIgstRate() {
		return igstRate;
	}

	public void setIgstRate(double igstRate) {
		this.igstRate = igstRate;
	}

	public double getIgstAmount() {
		return igstAmount;
	}

	public void setIgstAmount(double igstAmount) {
		this.igstAmount = igstAmount;
	}

	public int getIgstApplicable() {
		return igstApplicable;
	}

	public void setIgstApplicable(int igstApplicable) {
		this.igstApplicable = igstApplicable;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public int getFkInvoiceRequestId() {
		return fkInvoiceRequestId;
	}

	public void setFkInvoiceRequestId(int fkInvoiceRequestId) {
		this.fkInvoiceRequestId = fkInvoiceRequestId;
	}

	public String getCustomerPan() {
		return customerPan;
	}

	public void setCustomerPan(String customerPan) {
		this.customerPan = customerPan;
	}

	public String getCustomerGstin() {
		return customerGstin;
	}

	public void setCustomerGstin(String customerGstin) {
		this.customerGstin = customerGstin;
	}

	public String getCustomerState() {
		return customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	public String getCustomerStateCode() {
		return customerStateCode;
	}

	public void setCustomerStateCode(String customerStateCode) {
		this.customerStateCode = customerStateCode;
	}

	public String getOur_pan() {
		return our_pan;
	}

	public void setOur_pan(String our_pan) {
		this.our_pan = our_pan;
	}

	public String getOurGstin() {
		return ourGstin;
	}

	public void setOurGstin(String ourGstin) {
		this.ourGstin = ourGstin;
	}

	public String getOurState() {
		return ourState;
	}

	public void setOurState(String ourState) {
		this.ourState = ourState;
	}

	public String getOurStateCode() {
		return ourStateCode;
	}

	public void setOurStateCode(String ourStateCode) {
		this.ourStateCode = ourStateCode;
	}

	public int getInvoiceSentStatus() {
		return invoiceSentStatus;
	}

	public void setInvoiceSentStatus(int invoiceSentStatus) {
		this.invoiceSentStatus = invoiceSentStatus;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getSentOn() {
		return sentOn;
	}

	public void setSentOn(String sentOn) {
		this.sentOn = sentOn;
	}

	public String getShipAddLane1() {
		return shipAddLane1;
	}

	public void setShipAddLane1(String shipAddLane1) {
		this.shipAddLane1 = shipAddLane1;
	}

	public String getShipAddLane2() {
		return shipAddLane2;
	}

	public void setShipAddLane2(String shipAddLane2) {
		this.shipAddLane2 = shipAddLane2;
	}

	public String getShipAddState() {
		return shipAddState;
	}

	public void setShipAddState(String shipAddState) {
		this.shipAddState = shipAddState;
	}

	public String getShipAddPincode() {
		return shipAddPincode;
	}

	public void setShipAddPincode(String shipAddPincode) {
		this.shipAddPincode = shipAddPincode;
	}

	public String getBillingAddLane1() {
		return billingAddLane1;
	}

	public void setBillingAddLane1(String billingAddLane1) {
		this.billingAddLane1 = billingAddLane1;
	}

	public String getBillingAddLane2() {
		return billingAddLane2;
	}

	public void setBillingAddLane2(String billingAddLane2) {
		this.billingAddLane2 = billingAddLane2;
	}

	public String getBillingAddState() {
		return billingAddState;
	}

	public void setBillingAddState(String billingAddState) {
		this.billingAddState = billingAddState;
	}

	public String getBillingAddPincode() {
		return billingAddPincode;
	}

	public void setBillingAddPincode(String billingAddPincode) {
		this.billingAddPincode = billingAddPincode;
	}

	public String getVendorAddLane1() {
		return vendorAddLane1;
	}

	public void setVendorAddLane1(String vendorAddLane1) {
		this.vendorAddLane1 = vendorAddLane1;
	}

	public String getVendorAddLane2() {
		return vendorAddLane2;
	}

	public void setVendorAddLane2(String vendorAddLane2) {
		this.vendorAddLane2 = vendorAddLane2;
	}

	public String getVendorAddState() {
		return vendorAddState;
	}

	public void setVendorAddState(String vendorAddState) {
		this.vendorAddState = vendorAddState;
	}

	public String getVendorAddPincode() {
		return vendorAddPincode;
	}

	public void setVendorAddPincode(String vendorAddPincode) {
		this.vendorAddPincode = vendorAddPincode;
	}
	
	public String getInvoicePdfDate() {
		return invoicePdfDate;
	}

	public void setInvoicePdfDate(String invoicePdfDate) {
		this.invoicePdfDate = invoicePdfDate;
	}

	public String getShippingPan() {
		return shippingPan;
	}

	public void setShippingPan(String shippingPan) {
		this.shippingPan = shippingPan;
	}

	public String getShippingGstin() {
		return shippingGstin;
	}

	public void setShippingGstin(String shippingGstin) {
		this.shippingGstin = shippingGstin;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InvoiceModel [invoiceId=");
		builder.append(invoiceId);
		builder.append(", invoiceNumber=");
		builder.append(invoiceNumber);
		builder.append(", invoiceDate=");
		builder.append(invoiceDate);
		builder.append(", poNumber=");
		builder.append(poNumber);
		builder.append(", poDate=");
		builder.append(poDate);
		builder.append(", supplierRefNumber=");
		builder.append(supplierRefNumber);
		builder.append(", fkRequirementId=");
		builder.append(fkRequirementId);
		builder.append(", period=");
		builder.append(period);
		builder.append(", fkEmployeeId=");
		builder.append(fkEmployeeId);
		builder.append(", hsnSac=");
		builder.append(hsnSac);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", billingDays=");
		builder.append(billingDays);
		builder.append(", totalWorkDays=");
		builder.append(totalWorkDays);
		builder.append(", rate=");
		builder.append(rate);
		builder.append(", taxableAmount=");
		builder.append(taxableAmount);
		builder.append(", cgst_rate=");
		builder.append(cgst_rate);
		builder.append(", cgstAmount=");
		builder.append(cgstAmount);
		builder.append(", sgstRate=");
		builder.append(sgstRate);
		builder.append(", sgstAmount=");
		builder.append(sgstAmount);
		builder.append(", igstRate=");
		builder.append(igstRate);
		builder.append(", igstAmount=");
		builder.append(igstAmount);
		builder.append(", igstApplicable=");
		builder.append(igstApplicable);
		builder.append(", totalAmount=");
		builder.append(totalAmount);
		builder.append(", accountHolderName=");
		builder.append(accountHolderName);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", ifscCode=");
		builder.append(ifscCode);
		builder.append(", fkInvoiceRequestId=");
		builder.append(fkInvoiceRequestId);
		builder.append(", customerPan=");
		builder.append(customerPan);
		builder.append(", customerGstin=");
		builder.append(customerGstin);
		builder.append(", customerState=");
		builder.append(customerState);
		builder.append(", customerStateCode=");
		builder.append(customerStateCode);
		builder.append(", our_pan=");
		builder.append(our_pan);
		builder.append(", ourGstin=");
		builder.append(ourGstin);
		builder.append(", ourState=");
		builder.append(ourState);
		builder.append(", ourStateCode=");
		builder.append(ourStateCode);
		builder.append(", invoiceSentStatus=");
		builder.append(invoiceSentStatus);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", sentOn=");
		builder.append(sentOn);
		builder.append(", shipAddLane1=");
		builder.append(shipAddLane1);
		builder.append(", shipAddLane2=");
		builder.append(shipAddLane2);
		builder.append(", shipAddState=");
		builder.append(shipAddState);
		builder.append(", shipAddPincode=");
		builder.append(shipAddPincode);
		builder.append(", billingAddLane1=");
		builder.append(billingAddLane1);
		builder.append(", billingAddLane2=");
		builder.append(billingAddLane2);
		builder.append(", billingAddState=");
		builder.append(billingAddState);
		builder.append(", billingAddPincode=");
		builder.append(billingAddPincode);
		builder.append(", vendorAddLane1=");
		builder.append(vendorAddLane1);
		builder.append(", vendorAddLane2=");
		builder.append(vendorAddLane2);
		builder.append(", vendorAddState=");
		builder.append(vendorAddState);
		builder.append(", vendorAddPincode=");
		builder.append(vendorAddPincode);
		builder.append(", invoicePdfDate=");
		builder.append(invoicePdfDate);
		builder.append(", shippingPan=");
		builder.append(shippingPan);
		builder.append(", shippingGstin=");
		builder.append(shippingGstin);
		builder.append(", shippingState=");
		builder.append(shippingState);
		builder.append(", shippingStateCode=");
		builder.append(shippingStateCode);
		builder.append("]");
		return builder.toString();
	}

}