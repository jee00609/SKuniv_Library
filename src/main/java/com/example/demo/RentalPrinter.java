package com.example.demo;

// 대여 했던 책들을 알려준다.
// 히스토리 기능에서 사용
public class RentalPrinter {

	public void print(Rental rental) {

		System.out.printf("대여 정보 : ISBN=%S, E-mail=%s, 대여 날짜=%tF",rental.getBookISBN(),rental.getUserEmail(),rental.getRentalDate());

	}
	
	public void print(Rental rental,String overdue) {

		System.out.printf("대여 정보 : ISBN=%S, E-mail=%s, 대여 날짜=%tF 반납 유무=%s",rental.getBookISBN(),rental.getUserEmail(),rental.getRentalDate(),overdue);

	}



}
