package com.example.demo;

// 회원에 대한 정보를 출력해주는 기능

// 비밀번호 까지 출력하기 때문에 이 기능은 오로지 관리자만 사용가능하다. 
public class MemberPrinter {
	public void print(Member member) {
		System.out.printf("회원 정보: 이메일=%s, 비밀번호=%s, 이름=%s, 등록일=%tF\n",member.getEmail(),member.getPassword(),member.getName(), member.getRegisterDate());
	}
}
