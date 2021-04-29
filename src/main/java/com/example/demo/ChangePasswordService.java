package com.example.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {
	
	Scanner sc = new Scanner(System.in);
	private String email;
	private String pw;
	private String newpw;
	private MemberDao memberDao;
	private Utils utils = Utils.getInstance();
	
	
	@Autowired
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
public void changePw() {
		
		System.out.println();
		System.out.println("비밀번호 수정을 진행합니다.");
		System.out.println();
		
		System.out.print("이메일 > ");
		email = sc.nextLine();
		
		System.out.print("비밀번호 > ");
		pw = sc.nextLine();
		

		// 관리자 id 를 입력하지 않았을 경우
		if (!email.equals(utils.getAdmin())) {
			try {
				// 사용자 정보가 있는지 비교 후, 정의된 Exception 처리 
				this.isWrongUser(email, pw);
				// 사용자 정보가 없지 않다면(!null)
				if (this.memberDao.getCurrentUser(email, pw) != null) {
					System.out.println();
					System.out.print("새로 설정할 비밀번호 > ");
					newpw = sc.nextLine();
					
					//MemberRegisterService 와 달리 이미 memberDao.selectByEmail(email); 가 제대로 존재한다는 것을 증명
					//null 값 조건일 경우에 대한 예외처리를 할 필요없다.
					Member member = memberDao.selectByEmail(email);
					member.changePassword(pw, newpw);
					
					this.memberDao.update(member);

				}	
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println();
			System.out.println("관리자 계정은 수정이 불가합니다.");
		}
//
		// 로그인 멤버 저장
//		loginRequest = new LoginRequest(member);
//			
//		return loginRequest.getName();
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
	
}
