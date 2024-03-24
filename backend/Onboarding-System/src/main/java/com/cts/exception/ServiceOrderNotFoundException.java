package com.cts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServiceOrderNotFoundException extends RuntimeException{

	public ServiceOrderNotFoundException(String message) {
		super(message);
	}
}
