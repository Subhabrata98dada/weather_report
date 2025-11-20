package com.qsp.exceptionhandling;

public class AddClientException extends RuntimeException{
	public AddClientException(String message) {
		super(message);
	}
}
