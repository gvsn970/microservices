package com.nexiilabs.stp.prospect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "stp_meeting_type")
public class MeetingType {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "meeting_type_id", unique = true, nullable = false)
		private int meetingTypeId;
		@Column(name = "meeting_type")
		private String meetingType;
		public int getMeetingTypeId() {
			return meetingTypeId;
		}
		public void setMeetingTypeId(int meetingTypeId) {
			this.meetingTypeId = meetingTypeId;
		}
		public String getMeetingType() {
			return meetingType;
		}
		public void setMeetingType(String meetingType) {
			this.meetingType = meetingType;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("MeetingType [meetingTypeId=");
			builder.append(meetingTypeId);
			builder.append(", meetingType=");
			builder.append(meetingType);
			builder.append("]");
			return builder.toString();
		}
		
}
