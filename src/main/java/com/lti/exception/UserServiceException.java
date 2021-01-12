package com.lti.exception;

public class UserServiceException extends RuntimeException {

	public UserServiceException() {
		
	}
	
	public UserServiceException(String message) {
		super(message);
	}
}
