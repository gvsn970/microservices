package com.snkit.springcache;

public class UserNotFoundException extends RuntimeException {
	private String code;
	
	private String message;

	public UserNotFoundException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	

	public String getMessage() {
		return message;
	}
}
