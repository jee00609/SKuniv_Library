package com.example.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

//기본 기능 - 수정 - 비밀번호 수정 제공
public class ChangePasswordService {
	
	Scanner sc = new Scanner(System.in);
	private String email;
	private String pw;
	private String newpw;
	private String newname;
	
	private MemberDao memberDao;
	// utils 만 getInstance 를 구현하여 만든 이유는 관리자는 Utils 클래스를 제외하곤 이메일을 바꿀 수 없게 하기 위해 final 을 붙였기 때문이다.
	// final 을 사용하면서 그냥 사용하려고 하면 java.lang.NullPointerException 에러 발생
	private Utils utils = Utils.getInstance();
	
	
	@Autowired
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	//비밀번호 수정
	public void changePw() {
		
		System.out.println();
		System.out.println("회원 정보 수정을 진행합니다.");
		System.out.println();
		
		System.out.print("이메일 > ");
		email = sc.nextLine();
		
		System.out.print("비밀번호 > ");
		pw = sc.nextLine();
		

		// 이메일에 관리자 id 를 입력하지 않았을 경우
		if (!email.equals(utils.getAdmin())) {
			try {
				// 사용자 정보가 있는지 비교 후, 정의된 Exception 처리 
				this.isWrongUser(email, pw);
				// 사용자 정보가 존재할 시
				if (this.memberDao.getCurrentUser(email, pw) != null) {
					System.out.println();
					System.out.print("새로 설정할 비밀번호 > ");
					newpw = sc.nextLine();
					
					System.out.print("새로 설정할 이름 > ");
					newname = sc.nextLine();
					
					//MemberRegisterService 와 달리 이미 memberDao.selectByEmail(email); 가 제대로 존재한다는 것을 증명
					//null 값 조건일 경우에 대한 예외처리를 할 필요없다.
					Member member = memberDao.selectByEmail(email);
					String oldname = member.getName();
					
					member.changePassword(pw, newpw);
					member.changeName(oldname, newname);
					
					//비밀번호를 수정한다!
					this.memberDao.update(member);

				}	
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		// 이메일에 관리자 id 를 입력했을 경우
		else {
			System.out.println();
			System.out.println("관리자 계정은 수정이 불가합니다.");
		}

	}
	
	//사용자 존재 유무 체크
	private void isWrongUser(String email, String password) throws ExistUserException {
		// 사용자 입력 정보가 없거나 관리자 정보가 아닐 경우라면
		if (this.memberDao.getCurrentUser(email, password) == null && !email.equals(utils.getAdmin())) {
			throw new ExistUserException("정보 수정 불가. 다시 입력해주세요.");
		}
		if (this.memberDao.isWrongPassword(email, password)) {
			throw new ExistUserException("잘못된 비밀번호 입니다. 다시 입력해주세요.");
		}
	}
	
}
