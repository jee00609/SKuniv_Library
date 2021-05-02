package com.example.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

//등록되 있는 책을 삭제하는 기능
public class BookDeleteService {

	private BookDao bookDao;
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
	
		Book book = null;
		book = bookDao.selectByBookISBN(bookISBN);
		
		System.out.print("정말로 삭제하시겠습니까? (Y 입력시 삭제) > ");
		confirm = sc.nextLine();
		
		//해당 ISBN 을 가진 도서가 존재하는 경우
		if (book!=null){
			// 정말로 삭제하겠다고 한 경우
			if (confirm.equals("Y")) {
				bookDao.remove(bookISBN);
				System.out.print("도서를 삭제하였습니다.");
			}
			//취소했을 경우
			else if(!confirm.equals("Y")) {
				System.out.print("도서 삭제를 취소하셨습니다.");
			}
		}
		//해당 ISBN 을 가진 도서가 존재하지 않는 경우
		else {
			System.out.println("해당 ISBN 을 가진 도서는 존재하지 않습니다.");
		}
	}
	
	
}
