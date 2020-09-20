package com.nexiilabs.stp.invoice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stp_invoice_request")
public class InvoiceRequestModel {

	@Id
	@Column(name="invoice_request_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invoiceRequestId;
	
	@Column(name="invoice_name")
	private String invoiceName;
	
	@Column(name="fk_invoice_id")
	private int fkInvoiceId;
	
	@Column(name="invoice_status")
	private int invoiceStatus;
	
	@Column(name="fk_po_id")
	private int fkPoId;

	@Column(name="invoice_month")
	private int invoiceMonth;
	
	@Column(name="invoice_year")
	private int invoiceYear;
	
	@Column(name="invoice_file_name")
	private String invoiceFileName;
	
	@Column(name="invoice_file_path")
	private String invoiceFilePath;
	
	@Column(name="invoice_sent_status")
	private int invoiceSentStatus;
	
	@Column(name="requested_on")
	private String requestedOn;
	
	@Column(name="requested_by")
	private int requestedBy;
	
	@Column(name="generated_by")
	private int generatedBy;
	
	@Column(name="generated_on")
	private String generatedOn;
	
	@Column(name="sent_by")
	private int sentBy;
	
	@Column(name="sent_on")
	private String sentOn;
	
	@Column(name="invoice_count")
	private int invoiceCount;
	

	public int getInvoiceRequestId() {
		return invoiceRequestId;
	}

	public void setInvoiceRequestId(int invoiceRequestId) {
		this.invoiceRequestId = invoiceRequestId;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public int getFkInvoiceId() {
		return fkInvoiceId;
	}

	public void setFkInvoiceId(int fkInvoiceId) {
		this.fkInvoiceId = fkInvoiceId;
	}

	public int getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(int invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public int getFkPoId() {
		return fkPoId;
	}

	public void setFkPoId(int fkPoId) {
		this.fkPoId = fkPoId;
	}

	public int getInvoiceMonth() {
		return invoiceMonth;
	}

	public void setInvoiceMonth(int invoiceMonth) {
		this.invoiceMonth = invoiceMonth;
	}

	public int getInvoiceYear() {
		return invoiceYear;
	}

	public void setInvoiceYear(int invoiceYear) {
		this.invoiceYear = invoiceYear;
	}

	public String getInvoiceFileName() {
		return invoiceFileName;
	}

	public void setInvoiceFileName(String invoiceFileName) {
		this.invoiceFileName = invoiceFileName;
	}

	public String getInvoiceFilePath() {
		return invoiceFilePath;
	}

	public void setInvoiceFilePath(String invoiceFilePath) {
		this.invoiceFilePath = invoiceFilePath;
	}

	public int getInvoiceSentStatus() {
		return invoiceSentStatus;
	}

	public void setInvoiceSentStatus(int invoiceSentStatus) {
		this.invoiceSentStatus = invoiceSentStatus;
	}
	
	public String getRequestedOn() {
		return requestedOn;
	}

	public void setRequestedOn(String requestedOn) {
		this.requestedOn = requestedOn;
	}

	public int getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(int requestedBy) {
		this.requestedBy = requestedBy;
	}

	public int getGeneratedBy() {
		return generatedBy;
	}

	public void setGeneratedBy(int generatedBy) {
		this.generatedBy = generatedBy;
	}

	public String getGeneratedOn() {
		return generatedOn;
	}

	public void setGeneratedOn(String generatedOn) {
		this.generatedOn = generatedOn;
	}

	public int getSentBy() {
		return sentBy;
	}

	public void setSentBy(int sentBy) {
		this.sentBy = sentBy;
	}

	public String getSentOn() {
		return sentOn;
	}

	public void setSentOn(String sentOn) {
		this.sentOn = sentOn;
	}

	public int getInvoiceCount() {
		return invoiceCount;
	}

	public void setInvoiceCount(int invoiceCount) {
		this.invoiceCount = invoiceCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InvoiceRequestModel [invoiceRequestId=");
		builder.append(invoiceRequestId);
		builder.append(", invoiceName=");
		builder.append(invoiceName);
		builder.append(", fkInvoiceId=");
		builder.append(fkInvoiceId);
		builder.append(", invoiceStatus=");
		builder.append(invoiceStatus);
		builder.append(", fkPoId=");
		builder.append(fkPoId);
		builder.append(", invoiceMonth=");
		builder.append(invoiceMonth);
		builder.append(", invoiceYear=");
		builder.append(invoiceYear);
		builder.append(", invoiceFileName=");
		builder.append(invoiceFileName);
		builder.append(", invoiceFilePath=");
		builder.append(invoiceFilePath);
		builder.append(", invoiceSentStatus=");
		builder.append(invoiceSentStatus);
		builder.append(", requestedOn=");
		builder.append(requestedOn);
		builder.append(", requestedBy=");
		builder.append(requestedBy);
		builder.append(", generatedBy=");
		builder.append(generatedBy);
		builder.append(", generatedOn=");
		builder.append(generatedOn);
		builder.append(", sentBy=");
		builder.append(sentBy);
		builder.append(", sentOn=");
		builder.append(sentOn);
		builder.append(", invoiceCount=");
		builder.append(invoiceCount);
		builder.append("]");
		return builder.toString();
	}

}