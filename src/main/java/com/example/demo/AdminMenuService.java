package com.example.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class AdminMenuService {
	Scanner sc = new Scanner(System.in);
	
//	MemberDao memberDao;
//	BookDao bookDao;
//	
//	@Autowired
//	public AdminMenuService(MemberDao memberDao,BookDao bookDao) {
//		this.memberDao = memberDao;
//		this.bookDao = bookDao;
//	}

	public int amdinMenu() {
	
		int input = 0;
		
		while (true) {
			try {
				System.out.println();
				System.out.println(
						"=======================================================================================");
				System.out.println("1.도서 관리   2.회원 관리   0.관리자 로그아웃");
				System.out.println(
						"=======================================================================================");
				System.out.print("선택 > ");
				input = sc.nextInt();
				sc.nextLine();
				
				// 관리자 로그아웃
				if ((input != 0)&&(input != 1)&&(input != 2)) {
					System.out.println("알 수 없는 입력입니다. 다시 입력해주세요.");
				}
				else {
					break;
				}
				
			}catch (Exception e) {
					
			}
		}
		return input;
	}
	
}
