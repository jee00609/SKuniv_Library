package com.example.demo;

import java.io.Serializable;

public class Book implements Serializable{

	private long bookNo;
	private String bookISBN;
	private String bookTitle;
	private String author;
	private String publisher;
	private String bookStatus;
	
	public Book(String bookISBN,String bookTitle, String author, String publisher) {
		this.bookISBN = bookISBN;
		this.bookTitle = bookTitle;
		this.author = author;
		this.publisher = publisher;
		setBookStatus("비치중");
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
	
}
