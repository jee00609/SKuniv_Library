package com.example.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class AdminSubBookMenuService {

	Scanner sc = new Scanner(System.in);
	BookDao bookDao;
	
	@Autowired
	public AdminSubBookMenuService(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	public int amdinSBMenu() {
		
		int input = 0;
		
		while (true) {
			try {
				System.out.println();
				System.out.println(
						"=======================================================================================");
				System.out.println("1.전체 도서   2.도서 등록   3.도서 삭제   4.도서 검색   0.관리자 로그아웃");
				System.out.println(
						"=======================================================================================");
				System.out.print("선택 > ");
				input = sc.nextInt();
				sc.nextLine();
				
				// 관리자 로그아웃
				if ((input != 0)&&(input != 1)&&(input != 2)&&(input != 3)&&(input != 4)) {
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
	
	public int amdinSBMenu2() {
		
		int input = 0;
		
		while (true) {
			try {
				System.out.println();
				System.out.println(
						"=======================================================================================");
				System.out.println("1.전체 회원   2.회원 검색   0.관리자 로그아웃");
				System.out.println(
						"=======================================================================================");
				System.out.print("선택 > ");
				input = sc.nextInt();
				sc.nextLine();
				
				// 관리자 로그아웃
				if ((input != 0)&&(input != 1)&&(input != 2)&&(input != 3)&&(input != 4)) {
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
	
	public int amdinSBMenu_Search() {
		
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
	
	public int amdinSBMenu2_Search() {
		
		int input = 0;
		
		while (true) {
			try {
				System.out.println();
				System.out.println(
						"=======================================================================================");
				System.out.println("1.E-mail 검색   2.이름 검색   0.종료");
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
