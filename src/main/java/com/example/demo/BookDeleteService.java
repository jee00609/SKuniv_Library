package com.example.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class BookDeleteService {

	private BookDao bookDao;
//	BookRegisterRequest req  = new BookRegisterRequest();
	Scanner sc = new Scanner(System.in);

	private String bookISBN;
	private String confirm;
	
	@Autowired
	public BookDeleteService(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	public void delete() {
		System.out.println();
		System.out.println("도서 삭제를 진행합니다.");
		System.out.println();
		
		System.out.print("도서 ISBN > ");
		bookISBN = sc.nextLine();
//		req.setBookISBN(bookISBN);
		
		System.out.print("정말로 삭제하시겠습니까? (Y 입력시 삭제) > ");
		confirm = sc.nextLine();
		if (confirm.equals("Y")) {
			bookDao.remove(bookISBN);
			System.out.print("도서를 삭제하였습니다.");
		}
		else {
			System.out.print("도서 삭제를 취소하셨습니다.");
		}
	}
	
	
}
