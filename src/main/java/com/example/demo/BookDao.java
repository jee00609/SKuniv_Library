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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookDao {
	

	private static long bookNo;

	private Map<String, Book> map = new HashMap<String, Book>();
	private static final String BOOK_FILE = "books.data"; // 도서 정보를 저장할 파일 경로 및 파일 이름
	
	// 파일 불러오기를 위한 메소드 선언
	// obj 캐스팅 컴파일 경고를 사용하지 않도록 설정
	@SuppressWarnings("unchecked")
	public BookDao() {
		File file = new File(BOOK_FILE);
		Object obj = null;
		// 책 정보 파일이 존재 할 경우라면
		if (file.exists()) {
			// 역직렬화 해서 파일 가져오기
			obj = deSerialization(BOOK_FILE);
			// 역직렬화한 파일을 사용자 정보를 저장한 저장소에 할당
			this.map = (Map<String, Book>) obj;
		}
	}
	
	//도서 ISBN 으로 검색
	public Book selectByBookISBN(String bookISBN) {return map.get(bookISBN);}
	
	//도서 이름으로 검색
	//https://balhae79.tistory.com/315 참고
	//도서 제목은 중복될 수 있으니 리스트를 통해 가져온다.
	//https://bvc12.tistory.com/165 참고
	public List<Book> selectByBookTitle(String bookTitle) {
		List<Book> list = new ArrayList<>();
		Book book = null;
		Set<String> key = this.map.keySet();
		List<String> temp = new ArrayList<String>(key);
		
		// Iterator로 반복문 돌리기
		Iterator<String> it = temp.iterator();
		
		while (it.hasNext()) {
			String keyTemp = (String) it.next();
			// Book 타입의 임시 변수 선언 후 map 맵 객체의 값 할당
			Book b = this.map.get(keyTemp);
			// 매개변수로 입력받은 도서 제목과 일치하는 값이 있다면
			if (b.getBookTitle().equals(bookTitle)) {
				// 결과 값을 리턴할 유저 변수에 할당
				book = b;
				list.add(map.get(book.getBookISBN()));
			}
		}
				
		return list;
	}
	
	//도서 추가
	public void insert(Book book) {
		book.setBookNo(++bookNo);
		map.put(book.getBookISBN(), book);
	}
	
	//도서 이름 변경
	public void update(Book book) {
		map.put(book.getBookISBN(), book);
	}
	
	//도서 삭제
	public void remove(String bookISBN) {
		map.remove(bookISBN);
	}
	
	public void showAllBooks() {
		System.out.println();
		System.out.println("전체 도서 목록 입니다.");
		StringBuilder sb = new StringBuilder();
		sb.append("---------------------------------------------------------------------------------------\n");
		sb.append(String.format("ISBN/제목/저자/출판사/대출유무\n"));
		sb.append("---------------------------------------------------------------------------------------\n");
		
		Set<String> key = this.map.keySet();
		
		List<String> list = new ArrayList<String>(key);
		
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String b1, String b2) {
				return b1.compareTo(b2);
			}
		});
		
		for (String booklist : list) {
			sb.append(String.format("%s/%s/%s/%s/%s\n", 
					this.map.get(booklist).getBookISBN(),this.map.get(booklist).getBookTitle(),
					this.map.get(booklist).getAuthor(),this.map.get(booklist).getPublisher(),this.map.get(booklist).getBookStatus()));
		}
		sb.append("---------------------------------------------------------------------------------------");
		System.out.println(sb.toString());
	}
	
	public void logout() {
		// 자바 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 바이트(byte) 형태로 데이터 변환하는 기술과 바이트로 변환된 데이터를 다시 객체로 변환하는 기술
		// java.io.NotSerializableException 에러 발생!!!!!!!!! 이거 어케 고쳐....
		// https://woowabros.github.io/experience/2017/10/17/java-serialize.html 참고
		// Member 에 implements Serializable 해준다.
		// 직렬화를 위한 스트림 생성
		try {
			// 사용자 객체가 저장된 컬렉션의 크기가 0보다 크다면
			if (this.map.size() > 0) {	
				
				// 자바 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 바이트(byte) 형태로 데이터 변환하는 기술과 바이트로 변환된 데이터를 다시 객체로 변환하는 기술
				// java.io.NotSerializableException 에러 발생!!!!!!!!! 이거 어케 고쳐....
				// https://woowabros.github.io/experience/2017/10/17/java-serialize.html 참고
				// Member 에 implements Serializable 해준다.
				// 직렬화를 위한 스트림 생성
				FileOutputStream fs = null;
				ObjectOutputStream os = null;
				
				// users.data 파일의 스트림 객체 생성
				fs = new FileOutputStream(BOOK_FILE);
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
//			도서가 존재하지 않을시
//			else System.out.println("bookmap size <=0");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 파일 저장용 메소드 선언
	// @Param 파일 이름이 포함된 경로(BOOK_FILE)
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
