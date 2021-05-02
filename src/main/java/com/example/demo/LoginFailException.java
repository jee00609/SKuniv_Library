package com.example.demo;

//LoginRequestService 에서 사용
public class LoginFailException extends RuntimeException {
	
	public LoginFailException(String message) {
		super(message);
	}
}