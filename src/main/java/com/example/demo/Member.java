package com.example.demo;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Member implements Serializable{
	
	
	//https://woowabros.github.io/experience/2017/10/17/java-serialize.html 참고
	private static final long serialVersionUID = 1L;
	
	//회원 id 번호다. 닉네임 id 가 아니다.
	private Long id;
	
	private String email;
	private String password;
	private String name;
	private String phone;
	private LocalDate registerDate;
	
	public Member(String email, String password, String name,String phone, LocalDate registerDate) {
		this.email = email; 
		this.password = password;
		this.name = name; 
		this.phone = phone;
		this.registerDate = registerDate;
	}
	
	// Source Getter Setter 기능 이용
	public void setId(Long id) {
		this.id = id;
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
	
	public void changePassword(String oldPassword, String newPassword) {
		if (!password.equals(oldPassword))
			throw new IdPasswordNotMatchingException();
		this.password = newPassword;
	}


}