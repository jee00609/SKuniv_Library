package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

public class LogoutService {

	private MemberDao memberDao;
	private BookDao bookDao;
	private RentalDao rentalDao;
	
	
	@Autowired
	public LogoutService(MemberDao memberDao,BookDao bookDao,RentalDao rentalDao) {
		this.memberDao = memberDao;
		this.bookDao = bookDao;
		this.rentalDao = rentalDao;
	}
	
	public void fileSave() {
		System.out.println();
		System.out.println("프로그램 종료");
		
		this.memberDao.logout();
		this.bookDao.logout();
		this.rentalDao.logout();
	}
}
