package com.example.demo;

// 비밀번호 변경 싫패
// Member 클래스에서 사용
public class PasswordException extends RuntimeException {
	public PasswordException(String message) {
		super(message);
	}
}