package com.masters.accounting.finance.onpassive.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "AllMasters")
public class AllMasters {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "code",  nullable=false)
	private String code;
	@Column(name = "name",  nullable=false)
	private String name;
	

	public AllMasters() {
		
	}
	
	public AllMasters(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AllMasters [id=" + id + ", code=" + code + ", name=" + name + "]";
	}


}
