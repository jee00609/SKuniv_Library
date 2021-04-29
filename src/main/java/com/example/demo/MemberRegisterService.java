package com.example.demo;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
public class MemberRegisterService {
	
	private MemberDao memberDao;
	RegisterRequest req  = new RegisterRequest();
	
	Scanner sc = new Scanner(System.in);
	
	private String email;
	private String password;
	private String name;
	private String phone;
	
	@Autowired
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void regist() {
		System.out.println();
		System.out.println("회원가입을 진행합니다.");
		System.out.println();
		
		
		System.out.print("이메일 > ");
		email = sc.nextLine();
		req.setEmail(email);
		
		System.out.print("비밀번호 > ");
		password = sc.nextLine();
		req.setPassword(password);
		
		System.out.print("이름 > ");
		name = sc.nextLine();
		req.setName(name);
		
		System.out.print("전화번호(010-1234-5678) > ");
		phone = sc.nextLine();
		req.setPhone(phone);
		
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new AlreadyExistingException("중복 email " + req.getEmail());
		}
		
		Member newMember = new Member(req.getEmail(), req.getPassword(),req.getName(),req.getPhone(), LocalDate.now());
		
		memberDao.insert(newMember);
	}
}