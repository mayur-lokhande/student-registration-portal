package com.cts.exception;


public class StudentAlreadyExistException extends RuntimeException {
	private String message;
	private static final long serialVersionUID = 1L;

	public StudentAlreadyExistException(String msg) {
		super(msg);
		this.message = msg;
	}

}
