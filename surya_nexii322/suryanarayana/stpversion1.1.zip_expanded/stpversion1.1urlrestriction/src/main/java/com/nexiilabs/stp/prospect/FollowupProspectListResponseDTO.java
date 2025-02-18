package com.nexiilabs.stp.prospect;

public class FollowupProspectListResponseDTO {
	private int followupId;
	private int fkProspectId;
	private int fkCompanyId;
	private String prospectName;
	private String companyName;
	private String comments;
	private String Stage;
	private String meetingType;
	private String contactedOn;
	private int fkmeetingId;
	private int fkStageId;
	private String nextFollowup;
	private String email;
	private String createdOn;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String alternateNumber;
	private String address1;
	private String address2;
	private String designation;
	private String additionalInfo;
	private String createdBy;
	public int getFollowupId() {
		return followupId;
	}
	public void setFollowupId(int followupId) {
		this.followupId = followupId;
	}
	public int getFkProspectId() {
		return fkProspectId;
	}
	public void setFkProspectId(int fkProspectId) {
		this.fkProspectId = fkProspectId;
	}
	public String getProspectName() {
		return prospectName;
	}
	public void setProspectName(String prospectName) {
		this.prospectName = prospectName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStage() {
		return Stage;
	}
	public void setStage(String stage) {
		Stage = stage;
	}
	public String getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
	public String getContactedOn() {
		return contactedOn;
	}
	public void setContactedOn(String contactedOn) {
		this.contactedOn = contactedOn;
	}
	public int getFkmeetingId() {
		return fkmeetingId;
	}
	public void setFkmeetingId(int fkmeetingId) {
		this.fkmeetingId = fkmeetingId;
	}
	public int getFkStageId() {
		return fkStageId;
	}
	public void setFkStageId(int fkStageId) {
		this.fkStageId = fkStageId;
	}
	public String getNextFollowup() {
		return nextFollowup;
	}
	public void setNextFollowup(String nextFollowup) {
		this.nextFollowup = nextFollowup;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
	public int getFkCompanyId() {
		return fkCompanyId;
	}
	public void setFkCompanyId(int fkCompanyId) {
		this.fkCompanyId = fkCompanyId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAlternateNumber() {
		return alternateNumber;
	}
	public void setAlternateNumber(String alternateNumber) {
		this.alternateNumber = alternateNumber;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FollowupProspectListResponseDTO [followupId=");
		builder.append(followupId);
		builder.append(", fkProspectId=");
		builder.append(fkProspectId);
		builder.append(", fkCompanyId=");
		builder.append(fkCompanyId);
		builder.append(", prospectName=");
		builder.append(prospectName);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", Stage=");
		builder.append(Stage);
		builder.append(", meetingType=");
		builder.append(meetingType);
		builder.append(", contactedOn=");
		builder.append(contactedOn);
		builder.append(", fkmeetingId=");
		builder.append(fkmeetingId);
		builder.append(", fkStageId=");
		builder.append(fkStageId);
		builder.append(", nextFollowup=");
		builder.append(nextFollowup);
		builder.append(", email=");
		builder.append(email);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", alternateNumber=");
		builder.append(alternateNumber);
		builder.append(", address1=");
		builder.append(address1);
		builder.append(", address2=");
		builder.append(address2);
		builder.append(", designation=");
		builder.append(designation);
		builder.append(", additionalInfo=");
		builder.append(additionalInfo);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append("]");
		return builder.toString();
	}
	
}
