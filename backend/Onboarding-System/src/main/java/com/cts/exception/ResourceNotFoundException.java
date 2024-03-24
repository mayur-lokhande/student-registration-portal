package com.cts.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	
	private Long fieldValue;

	public ResourceNotFoundException( Long fieldValue) {
		super(String.format("ID not found with '%s'", fieldValue));
		this.fieldValue = fieldValue;
	}
}
