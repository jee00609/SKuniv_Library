package com.example.demo;

public class NoticePrinter {

	
	public void print(Notice notice) {
		System.out.printf("[공지 번호] : "+notice.getNoticeNum()+" [공지 제목] : "+notice.getNoticeTitle()+" [공지 날짜] : "+notice.getNoticeDate()+"\n[공지 내용] : "+notice.getNotice());

	}
}
