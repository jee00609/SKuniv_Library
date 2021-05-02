package com.example.demo;

import java.time.LocalDate;

public class NoticeRegisterRequest {

	private String noticeNum; 
	private String noticeTitle;
	private String  notice;
	
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

	
}
