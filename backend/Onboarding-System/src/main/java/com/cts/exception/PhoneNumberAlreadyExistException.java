package com.cts.exception;

public class PhoneNumberAlreadyExistException extends RuntimeException {

	//private static String msg;
	private String message;

	public PhoneNumberAlreadyExistException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
		this.message = msg;
	}

}
