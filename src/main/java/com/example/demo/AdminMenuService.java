package com.example.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

// 관리자 로그인 메인 화면 구성 출력
// 여기서 도서관리 기능 / 회원 관리 기능 / 종료 를 선택하여 더 자세한 기능으로 들어갈 수 있게한다.
public class AdminMenuService {
	Scanner sc = new Scanner(System.in);

	public int amdinMenu() {
	
		int input = 0;
		
		while (true) {
			try {
				System.out.println();
				System.out.println(
						"=======================================================================================");
				System.out.println("1.도서 관리   2.회원 관리   3.공지 등록   0.관리자 로그아웃");
				System.out.println(
						"=======================================================================================");
				System.out.print("선택 > ");
				input = sc.nextInt();
				sc.nextLine();
				
				// 관리자 로그아웃
				if ((input != 0)&&(input != 1)&&(input != 2)&&(input != 3)) {
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
