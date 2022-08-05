package com.deutsche.nace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ NaceException.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request){
		NaceException naceException = new NaceException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "Internal error occurred");
		return new ResponseEntity<Object>(naceException, naceException.getStatus());
	}
	
}
