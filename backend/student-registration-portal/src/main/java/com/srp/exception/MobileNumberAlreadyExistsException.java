package com.srp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class MobileNumberAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long fieldName;

	public MobileNumberAlreadyExistsException(Long fieldName) {
		super(String.format("%s: This Mobile Number Already Exists!",fieldName));
		this.fieldName = fieldName;
	}
}