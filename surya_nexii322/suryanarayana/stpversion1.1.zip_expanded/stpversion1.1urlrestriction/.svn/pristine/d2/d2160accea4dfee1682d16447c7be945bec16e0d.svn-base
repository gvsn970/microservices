package com.nexiilabs.stp.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="stp_po_upload")
public class UploadPOModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "po_upload_id")
	private int poUploadId;
	@Column(name = "file_name")
	private String fileName;
	@Column(name = "upload_path")
	private String uploadPath;
	@Column(name = "fk_po_id")
	private int poId;
	@Column(name = "fk_employee_id")
	private int employeeId;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "deleted_by")
	private int deletedBy;
	public int getPoUploadId() {
		return poUploadId;
	}
	public void setPoUploadId(int poUploadId) {
		this.poUploadId = poUploadId;
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
	public int getPoId() {
		return poId;
	}
	public void setPoId(int poId) {
		this.poId = poId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
		builder.append("UploadPOModel [poUploadId=");
		builder.append(poUploadId);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", uploadPath=");
		builder.append(uploadPath);
		builder.append(", poId=");
		builder.append(poId);
		builder.append(", employeeId=");
		builder.append(employeeId);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", deletedBy=");
		builder.append(deletedBy);
		builder.append("]");
		return builder.toString();
	}

	
}
