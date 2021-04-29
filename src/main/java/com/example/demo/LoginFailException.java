package com.example.demo;

public class LoginFailException extends RuntimeException {
	
	public LoginFailException(String message) {
		super(message);
	}
}