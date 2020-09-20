package com.nexii.DemoCacheData.bean;

public class Person {
	
	
	private String name;
	private String data;
	public Person(String name, String data) {
		
		this.name = name;
		this.data = data;
	}
	public Person() {}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
