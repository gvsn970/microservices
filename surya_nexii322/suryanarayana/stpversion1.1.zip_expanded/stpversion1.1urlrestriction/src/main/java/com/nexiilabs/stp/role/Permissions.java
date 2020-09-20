package com.nexiilabs.stp.role;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stp_permissions")
public class Permissions implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "permission_id", unique = true, nullable = false)
	private int permission_id;
	
	@Column(name = "permission_name", length = 50)
	private String permission_name;
	
	public Permissions() {
		super();
	}

	public Permissions(int permission_id, String permission_name) {
		super();
		this.permission_id = permission_id;
		this.permission_name = permission_name;
	}

	public int getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(int permission_id) {
		this.permission_id = permission_id;
	}

	public String getPermission_name() {
		return permission_name;
	}

	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Permissions [permission_id=");
		builder.append(permission_id);
		builder.append(", permission_name=");
		builder.append(permission_name);
		builder.append("]");
		return builder.toString();
	}

}
