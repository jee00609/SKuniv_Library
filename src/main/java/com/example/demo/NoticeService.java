package com.example.demo;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class NoticeService {
	
	
	Scanner sc = new Scanner(System.in);
	private NoticeDao noticeDao;
	NoticeRegisterRequest req = new NoticeRegisterRequest();
	
	private String noticeNum;
	private String noticeTitle;
	private String noticeContent; 
	
	@Autowired
	public NoticeService(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	public void regist() {
		System.out.println();
		System.out.println("공지사항 등록을 진행합니다.");
		System.out.println();
	
		
		System.out.print("공지사항 번호 > ");
		noticeNum = sc.nextLine();
		req.setNoticeNum(noticeNum);
		
		System.out.print("공지사항 제목 > ");
		noticeTitle = sc.nextLine();
		req.setNoticeTitle(noticeTitle);
		
		System.out.print("공지 내용 > ");
		noticeContent = sc.nextLine();
		req.setNotice(noticeContent);
		

		Notice notice = noticeDao.selectByNoticeNum(req.getNoticeNum());

		// 입력한 이메일로 이미 회원이 존재할 경우
		if (notice != null) {
			throw new AlreadyExistingException("이미 존재하는 공지번호 입니다.");
		}
		// 없을 경우
		else {
			Notice newNotice = new Notice(req.getNoticeNum(), req.getNoticeTitle(),req.getNotice());
			
			noticeDao.insert(newNotice);
		}
		
	}
	

}
