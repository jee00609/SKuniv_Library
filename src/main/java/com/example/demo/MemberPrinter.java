package com.example.demo;

public class MemberPrinter {
	public void print(Member member) {
		System.out.printf("회원 정보: 이메일=%s, 비밀번호=%s, 이름=%s, 등록일=%tF\n",member.getEmail(),member.getPassword(),member.getName(), member.getRegisterDate());
	}
}
