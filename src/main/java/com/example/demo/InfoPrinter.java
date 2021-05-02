package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class InfoPrinter {
	
	//@Autowired 빼먹지 말자
	//에러 생김
	//Exception in thread "main" java.lang.NullPointerException: Cannot invoke "com.example.demo.BookDao.selectByBookISBN(String)" because "this.bookDao" is null
	@Autowired
	private MemberDao memDao;
	private MemberPrinter memberPrinter;
	
	@Autowired
	private BookDao bookDao;
	private BookPrinter bookPrinter;
	
	@Autowired
	private RentalDao rentalDao;
	private RentalPrinter rentalPrinter;
	
	@Autowired
	private NoticeDao noticeDao;
	private NoticePrinter noticePrinter;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao; 
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao=bookDao;
	}
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	
	//2020-04-30 생각을 해봤는데 이거 그냥 한꺼번에 인자 받아서 하는게 더 편하지 않나?
	//혹시 몰라 에러날 수 있으니 딱히 고치진 않는다. 다음번에 시도해보자!
	@Autowired
	public void setMemberPrinter(MemberPrinter printer) {
//		System.out.println("setMemberPrinter: " + printer);
		this.memberPrinter = printer; 
	}
	
	@Autowired
	public void setBookPrinter(BookPrinter printer) {
//		System.out.println("setBookPrinter: " + printer);
		this.bookPrinter = printer; 
	}
	
	@Autowired
	public void setRentalPrinter(RentalPrinter printer) {
//		System.out.println("setBookPrinter: " + printer);
		this.rentalPrinter = printer; 
	}
	
	@Autowired
	public void setNoticePrinter(NoticePrinter printer) {
//		System.out.println("setBookPrinter: " + printer);
		this.noticePrinter = printer; 
	}
	
	// 회원 검색
	// 이메일로 검색 (단 하나만 검색)
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if (member == null) {
			System.out.println("회원 데이터 없음\n");
			return;
		}
		else {
			System.out.println(
					"=======================================================================================");
			memberPrinter.print(member);
			System.out.println(
					"=======================================================================================");
			System.out.println();
		}
	}
	
	//회원 검색
	//회원 이름으로 검색 (중복된 이름을 가져도 모두 검색 가능!)
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
	// ISBN로 검색 (단 하나만 검색)
	public void printBookInfoByISBN(String bookISBN) {
		Book book = bookDao.selectByBookISBN(bookISBN);
		if (book == null) {
			System.out.println("책 데이터 없음\n");
			return;
		}
		else{
			System.out.println(
					"=======================================================================================");
			bookPrinter.print(book);
			System.out.println(
					"=======================================================================================");
			System.out.println();
		}
	}
	
	//도서 검색
	//도서 제목으로 검색 (중복된 제목이라도 모두 출력 가능!)
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
	// ISBN 으로 (단 하나만 검색)
	public void printRentalByISBN(String bookISBN) {
		Rental rental = rentalDao.selectByBookISBN(bookISBN);
		if (rental == null) {
			System.out.println("대여 데이터 없음\n");
			return;
		}
		else {
			System.out.println(
					"=======================================================================================");
			rentalPrinter.print(rental);
			System.out.println(
					"\n=======================================================================================");
			System.out.println();
		}
	}
	
	//히스토리 기능
	//특정 사용자가 빌렸었던 책들만 출력
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
				String bookISBN = rental.getBookISBN();
				Book book = bookDao.selectByBookISBN(bookISBN);
				String isBookOk = book.getBookStatus();
				int isOverdue = rentalDao.UserOverdue(bookISBN);
				
				// 정상 반납
				if (isBookOk.equals("비치중")) {
					String yes = "정상 반납";
					rentalPrinter.print(rental,yes);
					System.out.println();
				}
				else if (isOverdue == 0) {
					String yes = "대여중";
					rentalPrinter.print(rental,yes);
					System.out.println();
				}
				// 연체 중
				else {
					String no = "연체중";
					rentalPrinter.print(rental,no);
					System.out.println();
				}
			}
			System.out.println(
					"=======================================================================================");
		}
	}
	
	//연체중인지 검색
	//연체일 경우 몇일 연체인지 알 수 있다.
	//연체가 아닐경우 몇일 남았는지 알 수 있다.
	public void printIsOverdue(String searchISBN) {
		Book book = bookDao.selectByBookISBN(searchISBN);
		if(book!=null) {
			if(book.getBookStatus().equals("대여중")) {
				String ISBN = book.getBookISBN();
				System.out.println(
						"=======================================================================================");
				rentalDao.showOverdue(ISBN);
				System.out.println(
						"=======================================================================================");
			}
			else {
				System.out.println("책이 이미 비치중입니다.");
			}
		}
		else {
			System.out.println("책이 존재하지 않습니다.");
		}
		
	}
	
	//공지사항 보기
	public void printNoticeByNoticeNum(String noticeNum) {
		Notice notice = noticeDao.selectByNoticeNum(noticeNum);

		if (notice == null) {
			System.out.println("공지 내용 없음\n");
			return;
		}
		else {
			System.out.println(
					"=======================================================================================");
			noticePrinter.print(notice);
			System.out.println(
					"\n=======================================================================================");
			System.out.println();
		}
		
	}
	
	//등록된 모든 책을 보여준다.
	public void showBook() {
		System.out.println(
				"=======================================================================================");
		bookDao.showAllBooks();
		System.out.println(
				"=======================================================================================");
	}
	
	// 등록된 모든 회원을 보여준다.
	// 오로지 관리자만!
	public void showMember() {
		System.out.println(
				"=======================================================================================");
		memDao.showAllMembers();
		System.out.println(
				"=======================================================================================");
	}
	
	//등록된 모든 책을 보여준다.
	public int showNotice() {
		Scanner sc = new Scanner(System.in);
		int input;
		System.out.println(
				"=======================================================================================");
		noticeDao.showAllNotices();
		System.out.println(
				"=======================================================================================");
		System.out.println("\n1.공지 검색  0.종료\n");
		System.out.print("선택 > ");
		input = sc.nextInt();
		while(true) {
			if ((input != 0)&&(input != 1)) {
				System.out.println("알 수 없는 입력입니다. 다시 입력해주세요.");
			}
			else {
				return input;
			}
		}
		
	}
	
	
}
