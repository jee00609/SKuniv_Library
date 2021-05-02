package com.example.demo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class Notice implements Serializable{

	//스프링 5 입문 60페이지 참고
	private Long id;
	private String noticeNum; 
	private String noticeTitle;
	private String  notice;
	private LocalDate noticeDate;

	
	public Notice(String noticeNum,String noticeTitle, String notice) {
		this.noticeNum = noticeNum;
		this.noticeTitle = noticeTitle;
		this.notice = notice;
		setNoticeDate(LocalDate.now());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNoticeNum() {
		return noticeNum;
	}
	public void setNoticeNum(String noticeNum) {
		this.noticeNum = noticeNum;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public LocalDate getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(LocalDate noticeDate) {
		this.noticeDate = noticeDate;
	}

	
	
}
