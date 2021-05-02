package com.example.demo;

//도서 등록시 사용자가 입력한 값을 받고 그것을 다시 가져올 수 있도록 하는 기능 제공
//스프링 5 입문 63페이지 참고
public class BookRegisterRequest {

	private String bookISBN;
	private String bookTitle;
	private String author;
	private String publisher;
	
	
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	
}
