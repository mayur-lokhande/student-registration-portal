package com.srp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fieldName;

	public EmailAlreadyExistsException(String fieldName) {
		super(String.format("%s: This Email Already Exists...!",fieldName));
		this.fieldName = fieldName;
	}
}