package com.cts.exception;

public class EmailIdAlreadyExistException extends RuntimeException {

	private String message;

	public EmailIdAlreadyExistException(String msg) {
		super(msg);
		this.message = msg;
	
	}

}
