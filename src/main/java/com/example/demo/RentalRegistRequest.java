package com.example.demo;

import java.time.LocalDate;
import java.util.Date;
//대여 등록시 사용자가 입력한 값을 받고 그것을 다시 가져올 수 있도록 하는 기능 제공
//스프링 5 입문 63페이지 참고
public class RentalRegistRequest {

	//책 ISBN
	private String bookISBN;
	//사용자
	private String userEmail;
	//언제 책 빌렸는지
	private LocalDate rentalDate;
	//반납 예정 만기일(7일로 잡을까)
	private LocalDate dueDate;
	
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public LocalDate getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	
	
	
	
	
	
}
