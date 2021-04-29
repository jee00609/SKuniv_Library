package com.example.demo;

import java.time.LocalDate;
import java.util.Date;

public class LoginRequest {
	
	private Long id;
	private String email;
	private String password;
	private String name;
	private String phone;
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
