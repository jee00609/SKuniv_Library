package com.example.demo;

import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

//추가 기능 - 도서 등록 기능
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

		//도서 ISBN 이 이미 존재하는지 검색한다.
		Book book = bookDao.selectByBookISBN(req.getBookISBN());
		//해당 ISBN 으로 책이 존재할 경우
		if (book != null) {
			throw new AlreadyExistingException("중복 ISBN " + req.getBookISBN());
		}
		//없을 경우
		else {
			Book newBook = new Book(req.getBookISBN(),req.getBookTitle(),req.getAuthor(),req.getPublisher());
			bookDao.insert(newBook);
		}
		
	}
}
