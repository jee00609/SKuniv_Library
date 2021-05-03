package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RentalDao {
	
	
	private static long rentalNo;
	
	
	private Map<String, Rental> map = new HashMap<String, Rental>();
	private static final String RENTAL_FILE = "rentals.dat"; // 대여 정보를 저장할 파일 경로 및 파일 이름
	
	// 파일 불러오기를 위한 메소드 선언
	// obj 캐스팅 컴파일 경고를 사용하지 않도록 설정
	// https://blog.naver.com/funcyboy/80090152278 참고
	@SuppressWarnings("unchecked")
	public RentalDao() {
		File file = new File(RENTAL_FILE);
		Object obj = null;
		// 책 정보 파일이 존재 할 경우라면
		if (file.exists()) {
			// 역직렬화 해서 파일 가져오기
			obj = deSerialization(RENTAL_FILE);
			// 역직렬화한 파일을 사용자 정보를 저장한 저장소에 할당
			this.map = (Map<String, Rental>) obj;
			System.out.println("Rental Rental "+map);
		}
	}
	
	//도서 ISBN 으로 검색
	public Rental selectByBookISBN(String bookISBN) {
		return map.get(bookISBN);
	}
	
	//도서 이름으로 검색
	//https://balhae79.tistory.com/315 참고
	//도서 제목은 중복될 수 있으니 리스트를 통해 가져온다.
	//https://bvc12.tistory.com/165 참고
	public List<Rental> selectByEmail(String myemail) {
		List<Rental> list = new ArrayList<>();
		Rental rental = null;
		Set<String> key = this.map.keySet();
		List<String> temp = new ArrayList<String>(key);
		
		// Iterator로 반복문 돌리기
		Iterator<String> it = temp.iterator();
		
		while (it.hasNext()) {
			String keyTemp = (String) it.next();
			// Rental 타입의 임시 변수 선언 후 map 맵 객체의 값 할당
			Rental r = this.map.get(keyTemp);
			// 매개변수로 입력받은 도서 제목과 일치하는 값이 있다면
			if (r.getUserEmail().equals(myemail)) {
				// 결과 값을 리턴할 유저 변수에 할당
				rental = r;
				list.add(map.get(rental.getBookISBN()));
			}
		}
				
		return list;
	}
	
	// 연체 인지 아닌지 확인하는 함수
	public void showOverdue(String bookISBN) {
		Rental rental = map.get(bookISBN);
		
		LocalDate today = LocalDate.now();
		LocalDate duedate = map.get(rental.getBookISBN()).getDueDate();
		String email = map.get(bookISBN).getUserEmail();
		
		// 현재날짜가 반납 예정 만기일 보다 지났으면
		if (today.isAfter(duedate)) {
			long period = ChronoUnit.DAYS.between(today,duedate);
			period = period*(-1);
			System.out.println(email+" 님이 "+period+"일 만큼 연체중 입니다.");
		}
		// 아닐 경우
		else {
			long period = ChronoUnit.DAYS.between(today,duedate);
			System.out.println("현재 이책은 "+period+"일 만큼 남았습니다.");
		}

	}
	
	//infoPrinter 에서 사용할 친구
	public int UserOverdue(String bookISBN) {
		Rental rental = map.get(bookISBN);
		
		LocalDate today = LocalDate.now();
		LocalDate duedate = map.get(rental.getBookISBN()).getDueDate();
		String email = map.get(bookISBN).getUserEmail();
		
		// 현재날짜가 반납 예정 만기일 보다 하루 이상 지났으면
		if (today.isAfter(duedate)) {
			long period = ChronoUnit.DAYS.between(today,duedate);
			period = period*(-1);
			return 1;
		}
		// 아닐 경우
		else {
			long period = ChronoUnit.DAYS.between(today,duedate);
			return 0;
		}

	}
	
	//대여
	public void insert(Rental rental) {
		rental.setRentalNo(++rentalNo);
		map.put(rental.getBookISBN(),rental);
	}
	
	//반납
	//이거 제대로 안되요...
	// Book 모델 고쳐서 반납 기능 다시 구현했다
	// 아니 다시되네 혹시 모르니 남겨두자
	public void remove(String bookISBN) {
		map.remove(bookISBN);
	}
	
	// BookDao MemberDao 에 있는 logout과 똑같다.
	public void logout() {
		
		try {
			// 사용자 객체가 저장된 컬렉션의 크기가 0보다 크다면
			if (this.map.size() > 0) {	
			
				// 직렬화를 위한 스트림 생성
				FileOutputStream fs = null;
				ObjectOutputStream os = null;
				
				// users.data 파일의 스트림 객체 생성
				fs = new FileOutputStream(RENTAL_FILE);
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
//			else System.out.println("대여한 책이 하나도 존재하지 않습니다.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 파일 저장용 메소드 선언
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
