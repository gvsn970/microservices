package com.ness.springbootcrud.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emp_resume_uploads")
public class ResumeUploadModel  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_resume_uploads_id")
	private int attachmentId;
	@Column(name = "resume_extension")
	private String fileType;
	@Column(name = "resume_name")
	private String fileName;
	@Column(name = "resume_location")
	private String fileLocation;
	@Column(name = "resume_size")
	private String fileSize;
	@Column(name="fk_emp_id")
	private int empId;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "updated_by")
	private int updatedBy;
	@Column(name = "deleted_by")
	private int deletedBy;
	@Column(name = "delete_status")
	private int deleteStatus;
	public int getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
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
	@Override
	public String toString() {
		return "ResumeUploadModel [attachmentId=" + attachmentId + ", fileType=" + fileType + ", fileName=" + fileName
				+ ", fileLocation=" + fileLocation + ", fileSize=" + fileSize + ", empId=" + empId + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + ", deletedBy=" + deletedBy + ", deleteStatus=" + deleteStatus
				+ "]";
	}
	

}
