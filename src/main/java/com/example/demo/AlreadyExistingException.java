package com.example.demo;

//이미 이메일이 존재하는데 중복되게 회원가입 하려는 경우 예외처리를 출력해주기 위해 구현
//BookRegisterService, MemberRegisterService,MoticeService 에서 사용
public class AlreadyExistingException extends RuntimeException {

	public AlreadyExistingException(String message) {
		super(message);
	}

}