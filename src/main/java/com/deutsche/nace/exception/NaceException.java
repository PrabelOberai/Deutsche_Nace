package com.deutsche.nace.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

public class NaceException extends Exception {

	private HttpStatus status;
	private String message;
	private List<String> errors;
	
	public NaceException() {
		super();
	}
	
	public NaceException(HttpStatus status, String message, List<String> errors) {
		super();
        this.status = status;
        this.message = message;
        this.errors = errors;
	}
	
	public NaceException(HttpStatus status, String message, String error) {
		super();
        this.status = status;
        this.message = message;
        this.errors = Arrays.asList(error);
	}
	
	public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setError(String error) {
        errors = Arrays.asList(error);
    }

	
}
