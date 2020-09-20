package com.snkit.springcache;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CacheExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MultiValueMap<String,String>> genericError(Exception e) {
		MultiValueMap<String,String> map = new LinkedMultiValueMap<String,String>();
		
		map.add("Code", "IN001");
		map.add("Message", "Please retry after sometime");

		return new ResponseEntity(map,HttpStatus.OK);

	}

	@ExceptionHandler(CacheUserNotFoundException.class)
	public ResponseEntity<RootCacheResponse> cacheUserNofFoundExcepiton(CacheUserNotFoundException e) {

		RootCacheResponse error = new RootCacheResponse();
		
		error.setCode(e.getCode());
		error.setMessage(e.getMessage());

		return new ResponseEntity<RootCacheResponse>(error,HttpStatus.OK);

	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<MultiValueMap<String,String>> userNotFound(UserNotFoundException e) {
		MultiValueMap<String,String> map = new LinkedMultiValueMap<String,String>();
		
		map.add("Code", e.getCode());
		map.add("Message", e.getMessage());

		return new ResponseEntity(map,HttpStatus.OK);

	}

}
