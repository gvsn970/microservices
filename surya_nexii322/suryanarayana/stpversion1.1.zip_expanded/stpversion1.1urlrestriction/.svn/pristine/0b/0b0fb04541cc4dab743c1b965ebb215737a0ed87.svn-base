package com.nexiilabs.stp.authentication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stp_mail_config")
public class FlasMailConfig {
	
	@Id
	@Column(name = "config_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int configId;
	@Column(name = "from_username")
	private String fromUserName;
	@Column(name = "from_mail_id")
	private String fromMailId;
	@Column(name = "from_password")
	private String fromPassword;
	@Column(name = "mail_host")
	private String mailHost;
	@Column(name = "mail_port")
	private String mailPort;
	public int getConfigId() {
		return configId;
	}
	public void setConfigId(int configId) {
		this.configId = configId;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getFromMailId() {
		return fromMailId;
	}
	public void setFromMailId(String fromMailId) {
		this.fromMailId = fromMailId;
	}
	public String getFromPassword() {
		return fromPassword;
	}
	public void setFromPassword(String fromPassword) {
		this.fromPassword = fromPassword;
	}
	public String getMailHost() {
		return mailHost;
	}
	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}
	public String getMailPort() {
		return mailPort;
	}
	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlasMailConfig [configId=");
		builder.append(configId);
		builder.append(", fromUserName=");
		builder.append(fromUserName);
		builder.append(", fromMailId=");
		builder.append(fromMailId);
		builder.append(", fromPassword=");
		builder.append(fromPassword);
		builder.append(", mailHost=");
		builder.append(mailHost);
		builder.append(", mailPort=");
		builder.append(mailPort);
		builder.append("]");
		return builder.toString();
	}
	
}
