package com.nexiilabs.stp.bankaccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stp_bank_account")
public class BankAccountModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bank_account_id")
	private int accountId;
	@Column(name = "account_name")
	private String accountName;
	@Column(name = "account_holder_name")
	private String accountHolderName;
	@Column(name = "account_number")
	private String accountNumber;
	@Column(name = "account_ifsc")
	private String accountIFSC;
	@Column(name = "account_branch_name")
	private String accountBranchName;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "updated_by")
	private int updatedBy;
	@Column(name = "deleted_by")
	private int deletedBy;
	@Column(name = "delete_status")
	private int deleteStatus;
	@Column(name = "is_Active")
	private int isActive;
	@Column(name = "bank_name")
	private String bankName;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountIFSC() {
		return accountIFSC;
	}

	public void setAccountIFSC(String accountIFSC) {
		this.accountIFSC = accountIFSC;
	}

	public String getAccountBranchName() {
		return accountBranchName;
	}

	public void setAccountBranchName(String accountBranchName) {
		this.accountBranchName = accountBranchName;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankAccountModel [accountId=");
		builder.append(accountId);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", accountHolderName=");
		builder.append(accountHolderName);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", accountIFSC=");
		builder.append(accountIFSC);
		builder.append(", accountBranchName=");
		builder.append(accountBranchName);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append(", deletedBy=");
		builder.append(deletedBy);
		builder.append(", deleteStatus=");
		builder.append(deleteStatus);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append("]");
		return builder.toString();
	}

}
