package com.nexiilabs.stp.resource;

public class FilesListResponseDTO {
	private int poFileId;
	private int sowFileId;
	private String poFileName;
	private String sowFileName;
	public int getPoFileId() {
		return poFileId;
	}
	public void setPoFileId(int poFileId) {
		this.poFileId = poFileId;
	}
	public int getSowFileId() {
		return sowFileId;
	}
	public void setSowFileId(int sowFileId) {
		this.sowFileId = sowFileId;
	}
	public String getPoFileName() {
		return poFileName;
	}
	public void setPoFileName(String poFileName) {
		this.poFileName = poFileName;
	}
	public String getSowFileName() {
		return sowFileName;
	}
	public void setSowFileName(String sowFileName) {
		this.sowFileName = sowFileName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FilesListResponseDTO [poFileId=");
		builder.append(poFileId);
		builder.append(", sowFileId=");
		builder.append(sowFileId);
		builder.append(", poFileName=");
		builder.append(poFileName);
		builder.append(", sowFileName=");
		builder.append(sowFileName);
		builder.append(", getPoFileId()=");
		builder.append(getPoFileId());
		builder.append(", getSowFileId()=");
		builder.append(getSowFileId());
		builder.append(", getPoFileName()=");
		builder.append(getPoFileName());
		builder.append(", getSowFileName()=");
		builder.append(getSowFileName());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	

}
