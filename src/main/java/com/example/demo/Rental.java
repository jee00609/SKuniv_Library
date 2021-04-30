package com.example.demo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Rental implements Serializable{
	
	private long rentalNo;

	//책 ISBN
	private String bookISBN;
	//사용자
	private String userEmail;
	//언제 책 빌렸는지
	private LocalDate rentalDate;
	//반납 예정 만기일(7일로 잡을까)
	private LocalDate dueDate;
	//연체 기간 알아보기
	private int overDue;
	
	

	//Source 에서 Constructor 만들 수 있단게 신의 한수다
	//못해먹겠어으ㅏ아앙가	
	public Rental(String bookISBN, String userEmail, LocalDate rentalDate, LocalDate dueDate) {
		this.bookISBN = bookISBN;
		this.userEmail = userEmail;
		this.rentalDate = rentalDate;
		this.dueDate = dueDate;
		//처음 대여할 당시엔 연체 0일
		setOverDue(0);
	}
	

	public long getRentalNo() {
		return rentalNo;
	}

	public void setRentalNo(long rentalNo) {
		this.rentalNo = rentalNo;
	}






	public String getBookISBN() {
		return bookISBN;
	}

	public String getUserEmail() {
		return userEmail;
	}

	
	/////
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



	public int getOverDue() {
		return overDue;
	}



	public void setOverDue(int overDue) {
		this.overDue = overDue;
	}
	
	
}
