package com.deutsche.nace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ NaceException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleAll(NaceException naceException){
		return naceException.getMessage();
	}
	
	@ExceptionHandler({ NaceDetailsNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNaceDetailsNotFound(NaceDetailsNotFoundException naceDetailsNotFoundException){
		return naceDetailsNotFoundException.getMessage();
	}
	
	//NaceBadRequestException
	@ExceptionHandler({ NaceBadRequestException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleNaceBadRequest(NaceBadRequestException naceBadRequestException){
		return naceBadRequestException.getMessage();
	}
	
}
