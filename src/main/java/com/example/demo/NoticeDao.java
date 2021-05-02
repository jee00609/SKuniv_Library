package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NoticeDao {
	private static long id=0;
	//ppt 를 참고했더니 해시맵으로 저장하더라
	private Map<String, Notice> map = new HashMap<String, Notice>();
	// 도서 정보를 저장할 파일 경로 및 파일 이름
	// 참고 -https://blog.naver.com/funcyboy/80090152278
	private static final String NOTICE_FILE = "notices.dat"; 
	
	
	public Notice selectByNoticeNum(String noticeNum) { 
//		System.out.println("넌 뭐가 문제니 대체... "+noticeNum);
		return map.get(noticeNum); 
	}
	
	// 파일 불러오기를 위한 메소드 선언
	// obj 캐스팅 컴파일 경고를 사용하지 않도록 설정
	@SuppressWarnings("unchecked")
	public NoticeDao() {
		File file = new File(NOTICE_FILE);
		Object obj = null;
		// 사용자 정보 파일이 존재 할 경우라면
		if (file.exists()) {
			// 역직렬화 해서 파일 가져오기
			obj = deSerialization(NOTICE_FILE);
			// 역직렬화한 파일을 사용자 정보를 저장한 저장소에 할당
			this.map = (Map<String, Notice>) obj;
		}
	}
	
	// 회원 추가
	public void insert(Notice notice) {
		notice.setId(++id);
		map.put(notice.getNoticeNum(), notice);
	}
	
	//전체 책을 보여준다.
	public void showAllNotices() {
		System.out.println();
		System.out.println("전체 공지사항 목록 입니다.");
		StringBuilder sb = new StringBuilder();
		sb.append("---------------------------------------------------------------------------------------\n");
		sb.append(String.format("공지번호/제목\n"));
		sb.append("---------------------------------------------------------------------------------------\n");
		
		Set<String> key = this.map.keySet();
		
		List<String> list = new ArrayList<String>(key);
		
		// sort() = 오름차순 와 reverse() = 내림차순
		// 참고 - https://devlog-wjdrbs96.tistory.com/68
		// 참고 - https://wjheo.tistory.com/entry/Java-%EC%A0%95%EB%A0%AC%EB%B0%A9%EB%B2%95-Collectionssort
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String b1, String b2) {
				return b1.compareTo(b2);
			}
		});
		
		for (String noticelist : list) {
			sb.append(String.format("%s/%s\n", 
					this.map.get(noticelist).getNoticeNum(),this.map.get(noticelist).getNoticeTitle()));
		}
		sb.append("---------------------------------------------------------------------------------------");
		System.out.println(sb.toString());
	}
		
	public void logout() {
		try {
			// 직렬화를 위한 스트림 생성
			FileOutputStream fs = null;
			ObjectOutputStream os = null;
			
			// 사용자 객체가 저장된 컬렉션의 크기가 0보다 크다면
			if (this.map.size() > 0) {				
				// D:\\users.data 파일의 스트림 객체 생성
				fs = new FileOutputStream(NOTICE_FILE);
				os = new ObjectOutputStream(fs);
				// 컬렉션 저장소에 저장된 모든 정보를 직렬화 시도
				os.writeObject(this.map);
				
				try {
					// 스트림 닫아주기
					os.close();
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	// 파일 저장용 메소드 선언
	// MEMBER_FILE 경로에 저장
	public Object deSerialization(String fileName) {
		// 역직렬화한 객체를 반환하기 위한 Object 변수 선언
		Object result = null;
		// 파일을 스트림으로 읽어오기 위한 변수 선언
		FileInputStream fs = null;
		// 파일로부터 읽어온 스트림을 원래 필드 형으로 가져오기 위한 변수 선언
		ObjectInputStream os = null;
		try {
			// 파일 읽어오기
			fs = new FileInputStream(fileName);
			// 읽어온 파일로부터 객체 데이터를 읽어오기
			os = new ObjectInputStream(fs);
			// 객체 읽기
			result = os.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// 스트림을 닫아주기
				os.close();
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 읽어온 객체 리턴
		return result;
	}
}
