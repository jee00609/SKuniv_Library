package com.example.demo;

import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class BookRegisterService {
	
	private BookDao bookDao;
	BookRegisterRequest req  = new BookRegisterRequest();
	
	Scanner sc = new Scanner(System.in);

	private String bookISBN;
	private String bookTitle;
	private String author;
	private String publisher;
	
	@Autowired
	public BookRegisterService(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	public void regist() {
		System.out.println();
		System.out.println("도서 등록을 진행합니다.");
		System.out.println();
		
		System.out.print("도서 ISBN > ");
		bookISBN = sc.nextLine();
		req.setBookISBN(bookISBN);
		
		
		System.out.print("도서 제목 > ");
		bookTitle = sc.nextLine();
		req.setBookTitle(bookTitle);
		
		System.out.print("도서 작가 > ");
		author = sc.nextLine();
		req.setAuthor(author);
		
		System.out.print("도서 출판사 > ");
		publisher = sc.nextLine();
		req.setPublisher(publisher);

		
		Book book = bookDao.selectByBookISBN(req.getBookISBN());
		if (book != null) {
			throw new AlreadyExistingException("중복 ISBN " + req.getBookISBN());
		}
		
		Book newBook = new Book(req.getBookISBN(),req.getBookTitle(),req.getAuthor(),req.getPublisher());
		bookDao.insert(newBook);
		
	}
}
