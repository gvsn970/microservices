package com.snkit.springbootdatajpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="poc_user")
public class UserEntity implements Serializable {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4334646919946092016L;

	public UserEntity() {
		
	}

	
	public UserEntity(String name, String desg, String compName) {
		super();
		this.name = name;
		this.desg = desg;
		this.compName = compName;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO )
	@Column(name = "USER_ID")
	private Long user_id;
	
	private String name;
	
	private String desg;
	
	private String compName;
	
	@OneToMany(mappedBy = "userEntity", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AddressEntity> addressEntity = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesg() {
		return desg;
	}

	public void setDesg(String desg) {
		this.desg = desg;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}


	

	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	
	public Set<AddressEntity> getAddressEntity() {
		return addressEntity;
	}


	public void setAddressEntity(Set<AddressEntity> addressEntity) {
		this.addressEntity = addressEntity;
	}
	
	
	
}
