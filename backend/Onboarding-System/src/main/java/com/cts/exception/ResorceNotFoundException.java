package com.cts.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResorceNotFoundException extends RuntimeException {
	static final long serialVersionUID = 1L;

	public ResorceNotFoundException(String message) {
		super(message);
	}

}
