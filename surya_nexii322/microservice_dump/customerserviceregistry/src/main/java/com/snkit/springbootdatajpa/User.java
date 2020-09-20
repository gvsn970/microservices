package com.snkit.springbootdatajpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4559235601858974096L;
	
	

	private String name;
	
	private String desg;
	
	private String compName;
	
	private List<Address>  addList = new ArrayList<Address>(); 
	
	private Integer id;
	
	
	public User() {
		
	}

	public User(String name, String desg) {
		super();
		this.name = name;
		this.desg = desg;
	}

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

	public List<Address> getAddList() {
		return addList;
	}

	public void setAddList(List<Address> addList) {
		this.addList = addList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
}
