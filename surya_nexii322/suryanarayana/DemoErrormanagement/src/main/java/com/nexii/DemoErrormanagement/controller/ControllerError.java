package com.nexii.DemoErrormanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerError {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleError(Exception e){
		
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
