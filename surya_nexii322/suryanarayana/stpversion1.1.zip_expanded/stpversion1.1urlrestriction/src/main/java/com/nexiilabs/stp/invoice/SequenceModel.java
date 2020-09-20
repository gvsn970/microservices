package com.nexiilabs.stp.invoice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stp_invoice_sequence")
public class SequenceModel {
	@Id
	@Column(name="sequence_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sequenceId;
	@Column(name="sequence_number")
	private int sequenceNumber;
	@Column(name="sequence_code")
	private String sequenceCode;
	public int getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getSequenceCode() {
		return sequenceCode;
	}
	public void setSequenceCode(String sequenceCode) {
		this.sequenceCode = sequenceCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SequenceModel [sequenceId=");
		builder.append(sequenceId);
		builder.append(", sequenceNumber=");
		builder.append(sequenceNumber);
		builder.append(", sequenceCode=");
		builder.append(sequenceCode);
		builder.append("]");
		return builder.toString();
	}
	
}
