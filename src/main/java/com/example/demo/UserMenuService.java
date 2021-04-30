package com.example.demo;

import java.util.Scanner;

public class UserMenuService {

	Scanner sc = new Scanner(System.in);
	
	public int userMenu() {
		
		int input = 0;
		
		while (true) {
			try {
				System.out.println();
				System.out.println(
						"=======================================================================================");
				System.out.println("1.전체 도서   2.도서 검색   3.도서 대여   4.도서 반납   5.히스토리   0.사용자 로그아웃");
				System.out.println(
						"=======================================================================================");
				System.out.print("선택 > ");
				input = sc.nextInt();
				sc.nextLine();
				
				// 관리자 로그아웃
				if ((input != 0)&&(input != 1)&&(input != 2)&&(input != 3)&&(input != 4)&&(input != 5)) {
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
	
	//도서 검색 메뉴바
	public int userMenu_Search() {
		
		int input = 0;
		
		while (true) {
			try {
				System.out.println();
				System.out.println(
						"=======================================================================================");
				System.out.println("1.ISBN 검색   2.제목 검색   0.종료");
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
