package com.cts.exception;

public class UserNameAlreadyExistException extends RuntimeException {

	private String message;

	public UserNameAlreadyExistException(String msg) {
		super(msg);
		this.message = msg;
		// TODO Auto-generated constructor stub
	}

}
