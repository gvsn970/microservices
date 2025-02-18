package com.nexiilabs.stp.prospect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stp_prospect_followup")
public class ProspectUpdateModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "followup_id")
	private int followUpId;
	@Column(name = "fk_prospect_id")
	private int fkProspectId;
	@Column (name="fk_meeting_id")
	private int meetingTypeId;
	@Column (name="fk_stage_id")
	private int stageId;
	@Column (name="next_followup")
	private String nextFollowup;
	@Column (name="comments")
	private String comments;
	@Column (name="contacted_on")
	private String contactedOn;
	@Column (name="updated_by")
	private int updatedBy;
	@Column (name="created_by")
	private int createdBy;
	@Column (name="updated_on")
	private String updatedOn;
	@Column (name="delete_status")
	private int deleteStatus;
	@Column (name="followup_status")
	private int followupStatus;
	@Column (name="initial_status")
	private int initialStatus;
	public int getFollowUpId() {
		return followUpId;
	}
	public void setFollowUpId(int followUpId) {
		this.followUpId = followUpId;
	}
	public int getFkProspectId() {
		return fkProspectId;
	}
	public void setFkProspectId(int fkProspectId) {
		this.fkProspectId = fkProspectId;
	}
	public int getMeetingTypeId() {
		return meetingTypeId;
	}
	public void setMeetingTypeId(int meetingTypeId) {
		this.meetingTypeId = meetingTypeId;
	}
	public int getStageId() {
		return stageId;
	}
	public void setStageId(int stageId) {
		this.stageId = stageId;
	}
	public String getNextFollowup() {
		return nextFollowup;
	}
	public void setNextFollowup(String nextFollowup) {
		this.nextFollowup = nextFollowup;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getContactedOn() {
		return contactedOn;
	}
	public void setContactedOn(String contactedOn) {
		this.contactedOn = contactedOn;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	public int getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getFollowupStatus() {
		return followupStatus;
	}
	public void setFollowupStatus(int followupStatus) {
		this.followupStatus = followupStatus;
	}
	
	public int getInitialStatus() {
		return initialStatus;
	}
	public void setInitialStatus(int initialStatus) {
		this.initialStatus = initialStatus;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProspectUpdateModel [followUpId=");
		builder.append(followUpId);
		builder.append(", fkProspectId=");
		builder.append(fkProspectId);
		builder.append(", meetingTypeId=");
		builder.append(meetingTypeId);
		builder.append(", stageId=");
		builder.append(stageId);
		builder.append(", nextFollowup=");
		builder.append(nextFollowup);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", contactedOn=");
		builder.append(contactedOn);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", updatedOn=");
		builder.append(updatedOn);
		builder.append(", deleteStatus=");
		builder.append(deleteStatus);
		builder.append(", followupStatus=");
		builder.append(followupStatus);
		builder.append(", initialStatus=");
		builder.append(initialStatus);
		builder.append("]");
		return builder.toString();
	}
	
	
}
