package com.nexiilabs.stp.bankaccount;

public class BankAccountListResponseDTO {
	private int accountId;
	private String accountNumber;
	private String accountIFSC;
	private String accountBranchName;
	private String accountHolderName;
	private String bankName;
	private int isActive;
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankAccountListResponseDTO [accountId=");
		builder.append(accountId);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", accountIFSC=");
		builder.append(accountIFSC);
		builder.append(", accountBranchName=");
		builder.append(accountBranchName);
		builder.append(", accountHolderName=");
		builder.append(accountHolderName);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append("]");
		return builder.toString();
	}

}
