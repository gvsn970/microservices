package com.nexiilabs.stp.prospect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stp_stage")
public class StageModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stage_id", unique = true, nullable = false)
	private int stageId;
	@Column(name = "stage_name")
	private String stageName;
	public int getStageId() {
		return stageId;
	}
	public void setStageId(int stageId) {
		this.stageId = stageId;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StageModel [stageId=");
		builder.append(stageId);
		builder.append(", stageName=");
		builder.append(stageName);
		builder.append("]");
		return builder.toString();
	}
	
}
