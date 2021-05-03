package com.example.demo;

import java.time.LocalDate;

import java.util.Date;
//회원 등록시 사용자가 입력한 값을 받고 그것을 다시 가져올 수 있도록 하는 기능 제공
//스프링 5 입문 63페이지 참고
public class LoginRequest {
	
	//책 참고해서 사용한 ID
	private Long id;
	// e-mail 인데 이 친구가 프라이머리키다.
	private String email;
	// 비밀번호 -> 현재는 이것만 수정 가능
	private String password;
	// 이름
	private String name;
	// 전화번호
	private String phone;
	// 가입 날짜 -> 기말 때 이걸로 한번 기능 추가해보려고 미리 넣어둠
	private LocalDate registerDate;
	
	
	public LoginRequest(Member member) {
		this.id = member.getId();
		this.email = member.getEmail();
		this.password = member.getPassword();
		this.name = member.getName();
		this.phone = member.getPhone();
		this.registerDate = member.getRegisterDate();
	}



	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}


	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}


	public String getName() {
		return name;
	}


	public String getPhone() {
		return phone;
	}


	public LocalDate getRegisterDate() {
		return registerDate;
	}
	
	
	

}
