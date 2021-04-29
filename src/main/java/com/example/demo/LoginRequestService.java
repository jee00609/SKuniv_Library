package com.example.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class LoginRequestService {
	
	Scanner sc = new Scanner(System.in);
	private String email;
	private String pw;
	
	private MemberDao memberDao;
	private LoginRequest loginRequest;
	private Utils utils = Utils.getInstance();
	
	@Autowired
	public LoginRequestService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	// 로그인 함수
	public String login() {
		
		System.out.println();
		System.out.println("로그인을 진행합니다.");
		System.out.println();
		
		System.out.print("이메일 > ");
		email = sc.nextLine();
		
		System.out.print("비밀번호 > ");
		pw = sc.nextLine();
		
		
		
		// 관리자 id 및 비밀번호를 입력 했을 경우라면
		if (email.equals(utils.getAdmin()) && pw.equals(utils.getAdmin())) {
			System.out.println();
			System.out.println("[관리자]로 로그인 했습니다.");
			// 관리자 메뉴 보여주기
//			this.adminMenu(sc);
			System.out.println("관리자 메뉴");
			String returnUser = "admin";
			return returnUser;
			
		} else {
			try {
				// 사용자 정보가 있는지 비교 후, 정의된 Exception 처리 
				this.isWrongUser(email, pw);
				// 사용자 정보가 없지 않다면(!null)
				if (this.memberDao.getCurrentUser(email, pw) != null) {
					// 현재 사용자 객체 정보 저장
					utils.setCurrentMember(this.memberDao.getCurrentUser(email, pw));
					// 사용자 메뉴 보여주기
//					this.userMenu(sc);
					System.out.println("로그인 성공");
//					//이메일과 비밂번호를 먼저 받아야한다.
//					//논리적 순서를 기억하자.
					Member member = memberDao.selectByEmail(email);
					loginRequest = new LoginRequest(member);
					System.out.println("안녕하세요 "+loginRequest.getName()+" 님");
					return loginRequest.getName();
					
				}	
				} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	private void isWrongUser(String email, String password) throws ExistUserException {
		// 사용자 입력 정보가 없거나 관리자 정보가 아닐 경우라면
		if (this.memberDao.getCurrentUser(email, password) == null && !email.equals(utils.getAdmin())) {
			throw new ExistUserException("등록되지 않은 사용자 입니다. 다시 입력해주세요.");
		}
		if (this.memberDao.isWrongPassword(email, password)) {
			throw new ExistUserException("잘못된 비밀번호 입니다. 다시 입력해주세요.");
		}
	}
	
	public String loginEmail() {
		return loginRequest.getEmail();
	}
	
//	public LoginRequest getLoginRequest() {
//		return loginRequest;
//	}
	
//	// 현재 로그인 멤버 반환
//	public Long getId() {
//		return loginRequest.getId();
//	}
	
	
}
