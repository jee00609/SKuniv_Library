package com.example.demo;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class RentalReturnService {

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
	public RentalReturnService(BookDao bookDao,RentalDao rentalDao) {
		this.bookDao = bookDao;
		this.rentalDao = rentalDao;
	}
	
	//return 으로 이름짓고 싶은데 안되네
	public void returnB(String user) {
		System.out.println();
		System.out.println("도서 반납을 진행합니다.");
		System.out.println();
		
		System.out.print("도서 ISBN > ");
		bookISBN = sc.nextLine();
		
		Rental rental = rentalDao.selectByBookISBN(bookISBN);
//		System.out.println("왜 비어있니 rental 아아악 "+rental);
		if (rental !=null) {
			String rentalUser = rental.getUserEmail();
//			System.out.println("왜 비어있니 rentalUser 아아악 "+rentalUser);
			
			if(user.equals(rentalUser)) {
				Book book = bookDao.selectByBookISBN(bookISBN);
				
				rentalDao.remove(bookISBN);
				book.setBookStatus("비치중");
				
				System.out.println("반납하였습니다.");
			}
			else{
				System.out.println("대여를 한 사용자가 아닙니다.");
			}
		}
		else {
			System.out.println("대여한 책이 아닙니다.");
		}
		
		
	}
	
}