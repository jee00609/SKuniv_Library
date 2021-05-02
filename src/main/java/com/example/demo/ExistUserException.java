package com.example.demo;

//LoginRequestService 및 ChangePasswordService 에서 사용하는 exception 클래스
public class ExistUserException extends Exception {

	public ExistUserException(String message) {
		super(message);
	}


}
