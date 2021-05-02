package com.example.demo;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

// 반납 기능
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
	
	
	public void returnB(String user) {
		System.out.println();
		System.out.println("도서 반납을 진행합니다.");
		System.out.println();
		
		System.out.print("도서 ISBN > ");
		bookISBN = sc.nextLine();
		
		Rental rental = rentalDao.selectByBookISBN(bookISBN);
//		System.out.println("왜 비어있니 rental 아아악 "+rental);
		//책이 존재할 경우
		if (rental!=null) {
			if(rental.getBookISBN().equals(bookISBN)&&rental.getUserEmail().equals(user)) {
				Book book = bookDao.selectByBookISBN(bookISBN);
				//이걸 지우면 히스토리 기능이 된다.
//				rentalDao.remove(bookISBN);
				// Book 의 상태를 대여 가능하도록 다시 조정
				book.setBookStatus("비치중");
				// Book 을 빌린 사람이 없도록 조정
				book.setRentalUsers(null);
			}
			else {
				System.out.println("대여한 책이 아닙니다.");
			}
		}
		else {
			System.out.println("해당 ISBN 의 책은 존재하지 않습니다.");
		}
		
	}
	
}
