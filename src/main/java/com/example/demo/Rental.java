package com.example.demo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

//대여 모델
public class Rental implements Serializable{
	
	// 스프링 5 입문 참고해서 구현
	// 솔직히 꼭 필요한 변수라곤 볼 수 없는 듯
	private long rentalNo;

	//책 ISBN
	private String bookISBN;
	//사용자
	private String userEmail;
	//언제 책 빌렸는지 (연체)
	private LocalDate rentalDate;
	//반납 예정 만기일(1일로 잡는다-테스팅) - 연체 인지 아닌지 알 수 있도록!
	private LocalDate dueDate;
	//연체 기간 알아보기
	//함수를 만들었는데 이 변수를 사용안했다.
	//만일을 위해 남겨둬야징
	private int overDue;
	
	

	//Source 에서 Constructor 만들 수 있단게 신의 한수다
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





	//Book 모델의 ISBN 코드를 사용할 변수
	public String getBookISBN() {
		return bookISBN;
	}

	//Member 모델의 e-mail 을 사용할 변수
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
