package com.nexii.fileupload.model;


import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="emptab")
public class EmpModel implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int empid;
	private String username;
	private String email;
	private Blob photo;
	private String password;
	private String dob;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@Override
	public String toString() {
		return "EmpModel [empid=" + empid + ", username=" + username + ", email=" + email + ", photo=" + photo
				+ ", password=" + password + ", dob=" + dob + ", dateCreated=" + dateCreated + "]";
	}

	public EmpModel(String username, String email, Blob photo, String password, String dob, Date dateCreated) {
		super();
		this.username = username;
		this.email = email;
		this.photo = photo;
		this.password = password;
		this.dob = dob;
		this.dateCreated = dateCreated;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	
	
	public EmpModel() {}
	
	
}
