package com.nexiilabs.stp.bankaccount;

public class TaxDetailsListResponseDTO {
	private int taxId;
	private String state;
	private String stateCode;
	private String panNumber;
	private String gstIn;
    private int isActive;
	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
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
   
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TaxDetailsListResponseDTO [taxId=");
		builder.append(taxId);
		builder.append(", state=");
		builder.append(state);
		builder.append(", stateCode=");
		builder.append(stateCode);
		builder.append(", panNumber=");
		builder.append(panNumber);
		builder.append(", gstIn=");
		builder.append(gstIn);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append("]");
		return builder.toString();
	}

}
