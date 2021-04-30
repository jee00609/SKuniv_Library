package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class InfoPrinter {
	@Autowired
	private MemberDao memDao;
	private MemberPrinter memberPrinter;
	
	//@Autowired 빼먹지 말자
	//에러 생김
	//Exception in thread "main" java.lang.NullPointerException: Cannot invoke "com.example.demo.BookDao.selectByBookISBN(String)" because "this.bookDao" is null
	@Autowired
	private BookDao bookDao;
	private BookPrinter bookPrinter;
	
	@Autowired
	private RentalDao rentalDao;
	private RentalPrinter rentalPrinter;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao; 
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao=bookDao;
	}
	
	@Autowired
	public void setMemberPrinter(MemberPrinter printer) {
		System.out.println("setMemberPrinter: " + printer);
		this.memberPrinter = printer; 
	}
	
	@Autowired
	public void setBookPrinter(BookPrinter printer) {
		System.out.println("setBookPrinter: " + printer);
		this.bookPrinter = printer; 
	}
	
	@Autowired
	public void setRentalPrinter(RentalPrinter printer) {
		System.out.println("setBookPrinter: " + printer);
		this.rentalPrinter = printer; 
	}
	
	// 회원 검색
	// 이메일로 검색
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if (member == null) {
			System.out.println("회원 데이터 없음\n");
			return;
		}
		System.out.println(
				"=======================================================================================");
		memberPrinter.print(member);
		System.out.println(
				"=======================================================================================");
		System.out.println();
	}
	
	//회원 이름으로 검색
	public void printMemberInfoByName(String name) {
		List<Member> list = new ArrayList<>();
		list = memDao.selectByName(name);
		if (list == null) {
			System.out.println("회원 데이터 없음\n");
			return;
		}
		else {
			System.out.println(
					"=======================================================================================");
			//https://bvc12.tistory.com/165 참고
			for(Member member : list) {
				memberPrinter.print(member);
				System.out.println();
			}
			System.out.println(
					"=======================================================================================");
		}
	}
	
	// 도서 검색
	// ISBN로 검색
	public void printBookInfoByISBN(String bookISBN) {
		Book book = bookDao.selectByBookISBN(bookISBN);
		if (book == null) {
			System.out.println("책 데이터 없음\n");
			return;
		}
		System.out.println(
				"=======================================================================================");
		bookPrinter.print(book);
		System.out.println(
				"=======================================================================================");
		System.out.println();
	}
	

	//도서 제목으로 검색
	public void printBookInfoByTitle(String bookTitle) {
		List<Book> list = new ArrayList<>();
		list = bookDao.selectByBookTitle(bookTitle);
		if (list == null) {
			System.out.println("책 데이터 없음\n");
			return;
		}
		else {
			System.out.println(
					"=======================================================================================");
			//https://bvc12.tistory.com/165 참고
			for(Book book : list) {
				bookPrinter.print(book);
				System.out.println();
			}
			System.out.println(
					"=======================================================================================");
		}
	}
	
	// 대여 검색
	// ISBN 으로
	public void printRentalByISBN(String bookISBN) {
		Rental rental = rentalDao.selectByBookISBN(bookISBN);
		if (rental == null) {
			System.out.println("대여 데이터 없음\n");
			return;
		}
		System.out.println(
				"=======================================================================================");
		rentalPrinter.print(rental);
		System.out.println(
				"\n=======================================================================================");
		System.out.println();
	}
	
	//대여 검색
	//특정 사용자가 빌렸었던 책들만
	//히스토리 기능
	public void printRentalInfoByemail(String email) {
		List<Rental> list = new ArrayList<>();
		list = rentalDao.selectByEmail(email);

		if (list == null) {
			System.out.println("히스토리 데이터 없음\n");
			return;
		}
		else {
			System.out.println(
					"=======================================================================================");
			System.out.println(
					"\t\t\t현재까지의 기록\n");
			//https://bvc12.tistory.com/165 참고
			for(Rental rental : list) {
				rentalPrinter.print(rental);
				System.out.println();
			}
			System.out.println(
					"=======================================================================================");
		}
	}
	

	
	public void showAdminOverdue(String bookISBN){
		System.out.println(
				"=======================================================================================");
		rentalDao.showOverdue(bookISBN);
		System.out.println(
				"=======================================================================================");
	}
	
	
	public void showBook() {
		System.out.println(
				"=======================================================================================");
		bookDao.showAllBooks();
		System.out.println(
				"=======================================================================================");
	}
	
	public void showMember() {
		System.out.println(
				"=======================================================================================");
		memDao.showAllMembers();
		System.out.println(
				"=======================================================================================");
	}
}
