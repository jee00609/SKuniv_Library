package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

public class LogoutService {

	private MemberDao memberDao;
	private BookDao bookDao;
	private RentalDao rentalDao;
	private NoticeDao noticeDao;
	
	
	@Autowired
	public LogoutService(MemberDao memberDao,BookDao bookDao,RentalDao rentalDao,NoticeDao noticeDao) {
		this.memberDao = memberDao;
		this.bookDao = bookDao;
		this.rentalDao = rentalDao;
		this.noticeDao = noticeDao;
	}
	
	//현재 프로그램을 돌리면서 있었던 파일 쓰기들을 .dat 형식으로 저장한다
	public void fileSave() {
		System.out.println();
		System.out.println("서경대학교 도서관 프로그램 종료");
		
		//회원
		this.memberDao.logout();
		//책
		this.bookDao.logout();
		//대여
		this.rentalDao.logout();
		//공지
		this.noticeDao.logout();
	}
}
