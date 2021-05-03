package com.example.demo;

import java.io.Serializable;

//책 모델 구성
public class Book implements Serializable{
	
	//책의 넘버
	//스프링 5 입문 60페이지 참고
	private long bookNo;
	//책의 ISBN 코드 -> 이건 개별 책마다 갖는 고유한 번호로 책의 이름이 동일하다 하더라도 절대 동일한 ISBN 코드를 가질 수 없다. 프라이머리키로 매우 제격!
	private String bookISBN;
	//책의 제목
	private String bookTitle;
	//책의 저자
	private String author;
	//책의 출판사
	private String publisher;
	//책의 비치 유무 -> 책을 누군가 빌리면 다른 사람이 빌릴 수 없도록 함
	private String bookStatus;
	//현재 책을 누가 빌렸는가 -> 2021-04-30 새로 추가 , 대여 기능을 만드는데 책을 현재 누가 빌려갔는가를 저장한다. 기존에 Rental 클래스가 저장하던 email 에 대해 대신 저장해준다.
	private String rentalUsers;
	
	public Book(String bookISBN,String bookTitle, String author, String publisher) {
		this.bookISBN = bookISBN;
		this.bookTitle = bookTitle;
		this.author = author;
		this.publisher = publisher;
		// 책의 비치유무와 누가 빌려갔는가는 책을 처음 등록할 떈 무조건 비치중,null 로 초기화해준다.
		setBookStatus("비치중");
		setRentalUsers(null);
	}


	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}
	
	public String getBookISBN() {
		return bookISBN;
	}

	public String getPublisher() {
		return publisher;
	}
	
	public long getBookNo() {
		return bookNo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	//도서를 처음 등록 시엔 무조건 비치중 이어야 한다.
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	//도서를 처음 등록 시엔 아무도 빌리지 않았어야 한다.
	public String getRentalUsers() {
		return rentalUsers;
	}


	public void setRentalUsers(String rentalUsers) {
		this.rentalUsers = rentalUsers;
	}
	
	
	
}
