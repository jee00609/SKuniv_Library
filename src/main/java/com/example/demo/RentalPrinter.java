package com.example.demo;

public class RentalPrinter {

	public void print(Rental rental) {
		System.out.printf("대여 정보 : ISBN=%S, E-mail=%s, 대여 날짜=%tF, 반납 만료일=%tF",rental.getBookISBN(),rental.getUserEmail(),rental.getRentalDate(),rental.getDueDate());
	}



}