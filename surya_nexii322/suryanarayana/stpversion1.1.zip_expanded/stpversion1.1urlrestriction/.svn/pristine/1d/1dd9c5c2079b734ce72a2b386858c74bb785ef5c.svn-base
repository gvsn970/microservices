package com.nexiilabs.stp.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "stp_role")
public class RoleCRUD {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id", unique = true, nullable = false)
	private int role_id;
	
	@Column(name = "role_name", length = 50)
	private String role_name;
	
	@Column(name = "fk_permission_id")
	private String fk_permission_id;
	
	@Column(name = "delete_status")
	private int delete_status ;
	
	@Column(name = "updated_on")
	private String updated_on ;
	
	@Transient
	//@Column(name = "created_on")
	private String created_on;
	
	@Column(name = "deleted_on")
	private String deleted_on;
	
	public RoleCRUD() {
		super();
	}

	public RoleCRUD(String role_name, String fk_permission_id, int delete_status, String updated_on, String created_on,
			String deleted_on) {
		super();
		this.role_name = role_name;
		this.fk_permission_id = fk_permission_id;
		this.delete_status = delete_status;
		this.updated_on = updated_on;
		this.created_on = created_on;
		this.deleted_on = deleted_on;
	}

	public RoleCRUD(int role_id, String role_name, String fk_permission_id, int delete_status, String updated_on,
			String created_on, String deleted_on) {
		super();
		this.role_id = role_id;
		this.role_name = role_name;
		this.fk_permission_id = fk_permission_id;
		this.delete_status = delete_status;
		this.updated_on = updated_on;
		this.created_on = created_on;
		this.deleted_on = deleted_on;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getFk_permission_id() {
		return fk_permission_id;
	}

	public void setFk_permission_id(String fk_permission_id) {
		this.fk_permission_id = fk_permission_id;
	}

	public int getDelete_status() {
		return delete_status;
	}

	public void setDelete_status(int delete_status) {
		this.delete_status = delete_status;
	}

	public String getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(String updated_on) {
		this.updated_on = updated_on;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(String deleted_on) {
		this.deleted_on = deleted_on;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleCRUD [role_id=");
		builder.append(role_id);
		builder.append(", role_name=");
		builder.append(role_name);
		builder.append(", fk_permission_id=");
		builder.append(fk_permission_id);
		builder.append(", delete_status=");
		builder.append(delete_status);
		builder.append(", updated_on=");
		builder.append(updated_on);
		builder.append(", created_on=");
		builder.append(created_on);
		builder.append(", deleted_on=");
		builder.append(deleted_on);
		builder.append("]");
		return builder.toString();
	}

}
