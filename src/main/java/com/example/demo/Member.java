package com.example.demo;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

// 회원 모델 구성
// .dat 파일을 쓰고 닫기 위한 직렬화 역직열화를 위해 Serializable 추가
// 안하면 에러생김
public class Member implements Serializable{
	
	// Book 클래스를 분명히 양산해서 사용한건데 이런 에러가 났다.
	//https://woowabros.github.io/experience/2017/10/17/java-serialize.html 참고해서 해결!
	//자바 직렬화 대상 객체는 동일한 serialVersionUID 를 가지고 있어야 합니다.
	// id 를 long 으로 변환했었는데 그 때문에 에러 발생한듯
	//java.io.InvalidClassException
	private static final long serialVersionUID = 1L;
	
	//회원 id 번호다. 닉네임 id 가 아니다.
	//스프링 입문 5 참고
	private Long id;
	// e-mail 프라이머리 키
	private String email;
	// 비밀번호 -> 기본 기능으로 수정을 지원
	private String password;
	// 기말고사 땐 이것들 까지 바꿀 수 있도록 해봐야지
	// 회원 이름
	private String name;
	// 회원 전화번호
	private String phone;
	// 가입 날짜
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
			throw new PasswordException("ChangePassword 실패");
		this.password = newPassword;
	}
	
	// 새로운 기능 추가
	public void changeName(String oldName, String newName) {
		if (!name.equals(oldName))
			throw new PasswordException("ChangeName 실패");
		this.name = newName;
	}


}