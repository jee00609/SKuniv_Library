package com.example.demo;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class RentalRequestService {
	
	Scanner sc = new Scanner(System.in);
	private BookDao bookDao;
	private RentalDao rentalDao;
	
	RentalRegistRequest rentalReq = new RentalRegistRequest();
	BookRegisterRequest bookReq  = new BookRegisterRequest();
	
	//책 ISBN
	private String bookISBN;
	//사용자
	private String userEmail;
	//언제 책 빌렸는지
	private LocalDate rentalDate;
	//형식 고침
	//https://enai.tistory.com/39
	//LocalDate 가 더 편할듯
	//반납 예정 만기일
	private LocalDate dueDate;
//	//연체 기간 알아보기
//	private int overDue;
	
	private String canRental = null;
	
	@Autowired
	public RentalRequestService(BookDao bookDao,RentalDao rentalDao) {
		this.bookDao = bookDao;
		this.rentalDao = rentalDao;
	}
	
	public void regist(String user) {
		System.out.println();
		System.out.println("도서 대여를 진행합니다.");
		System.out.println();
		
		System.out.print("도서 ISBN > ");
		bookISBN = sc.nextLine();
		rentalReq.setBookISBN(bookISBN);
		
		userEmail = user;
		rentalReq.setUserEmail(userEmail);
		
		rentalDate = LocalDate.now();
		rentalReq.setRentalDate(rentalDate);
		//7일로 하고 싶다만 test 용으로 1일로 지정
		//현재 날짜에서 1일 더한다
		//반납 예정 만기일
		dueDate = LocalDate.now().plusDays(1);
		rentalReq.setDueDate(dueDate);
		
		Book book = bookDao.selectByBookISBN(rentalReq.getBookISBN());
		if (book != null) {
			canRental = book.getBookStatus();
			if (canRental.equals("비치중")) {
				Rental rental = new Rental(rentalReq.getBookISBN(),rentalReq.getUserEmail(),rentalReq.getRentalDate(),rentalReq.getDueDate());
				rentalDao.insert(rental);
				//대여 로 변환
				book.setBookStatus("대여중");
				//누가 빌렸는가
				book.setRentalUsers(userEmail);
				System.out.println(user+" 님이"+book.getBookTitle()+" 을 대여하였습니다.");
			}
			else{
				System.out.println("이미 대여중인 책입니다.");
			}
		}
		else {
			System.out.println("해당 ISBN 의 책이 존재하지 않습니다.");
		}
	}

}
