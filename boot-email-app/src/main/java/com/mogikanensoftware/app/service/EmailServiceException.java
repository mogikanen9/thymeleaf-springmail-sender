package com.mogikanensoftware.app.service;

public class EmailServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailServiceException(String message) {
		super(message);
	}

}
