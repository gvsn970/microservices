package com.snkit.springcache;

public class CacheUserNotFoundException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8640889656224040840L;

	private String code;
	
	private String message;

	public CacheUserNotFoundException(String code, String message) {
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
