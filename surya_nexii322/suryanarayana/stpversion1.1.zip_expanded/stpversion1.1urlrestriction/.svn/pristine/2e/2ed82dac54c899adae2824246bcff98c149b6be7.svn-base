package com.nexiilabs.stp.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="stp_customer_upload")
public class CustomerFileUpload {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_upload_id")
	private int customerUploadId;
	@Column(name = "file_name")
	private String fileName;
	@Column(name = "upload_path")
	private String uploadPath;
	@Column(name = "fk_customer_id")
	private int customerId;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "deleted_by")
	private int deletedBy;
	public int getCustomerUploadId() {
		return customerUploadId;
	}
	public void setCustomerUploadId(int customerUploadId) {
		this.customerUploadId = customerUploadId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(int deletedBy) {
		this.deletedBy = deletedBy;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerFileUpload [customerUploadId=");
		builder.append(customerUploadId);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", uploadPath=");
		builder.append(uploadPath);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", deletedBy=");
		builder.append(deletedBy);
		builder.append("]");
		return builder.toString();
	}
	
	
	 
}
