package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MemberDao {
	private static long nextId = 0;
	
	private Map<String, Member> map = new HashMap<String, Member>();
	private static final String USER_FILE = "users.data"; // 사용자 정보를 저장할 파일 경로 및 파일 이름
	
	public Member selectByEmail(String email) { return map.get(email); }
	
	//회원 이름으로 검색
	//https://balhae79.tistory.com/315 참고
	//회원 이름은 중복될 수 있으니 리스트를 통해 가져온다.
	//https://bvc12.tistory.com/165 참고
	public List<Member> selectByName(String name) {
		List<Member> list = new ArrayList<>();
		Member member = null;
		Set<String> key = this.map.keySet();
		List<String> temp = new ArrayList<String>(key);
		
		// Iterator로 반복문 돌리기
		Iterator<String> it = temp.iterator();
		
		while (it.hasNext()) {
			String keyTemp = (String) it.next();
			// Book 타입의 임시 변수 선언 후 map 맵 객체의 값 할당
			Member m = this.map.get(keyTemp);
			// 매개변수로 입력받은 도서 제목과 일치하는 값이 있다면
			if (m.getName().equals(name)) {
				// 결과 값을 리턴할 유저 변수에 할당
				member = m;
				list.add(map.get(member.getEmail()));
			}
		}
				
		return list;
	}
	
	// 파일 불러오기를 위한 메소드 선언
	@SuppressWarnings("unchecked") // obj 캐스팅 컴파일 경고를 사용하지 않도록 설정
	public MemberDao() {
		File file = new File(USER_FILE);
		Object obj = null;
		// 사용자 정보 파일이 존재 할 경우라면
		if (file.exists()) {
			// 역직렬화 해서 파일 가져오기
			obj = deSerialization(USER_FILE);
			// 역직렬화한 파일을 사용자 정보를 저장한 저장소에 할당
			this.map = (Map<String, Member>) obj;
		}
	}
	
	// 회원 추가
	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
	}
	
	// 회원 수정
	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
	
	
	public Collection<Member> selectAll() { return map.values(); }
	
	// 사용자 객체 저장소에 존재 여부 확인용 메소드 선언
	// @Param 사용자 이메일, 사용자 비밀번호 범용적으로 사용 가능한 메소드. (현재 사용자 설정용, 사용자 존재 여부)
	public Member getCurrentUser(String email, String password) {
		// 결과 값으로 넘길 User 타입 변수 생성
		Member user = null;
		// 전체 users의 반복문을 돌리며, 탐색하기 위해 set, list 생성
		Set<String> key = this.map.keySet();
		List<String> temp = new ArrayList<String>(key);
		
		
		// Iterator로 반복문 돌리기
		Iterator<String> it = temp.iterator();
		
		
		while (it.hasNext()) {
			String keyTemp = (String) it.next();
			// User 타입의 임시 변수 선언 후 users 맵 객체의 값 할당
			Member m = this.map.get(keyTemp);
//			System.out.println("email is "+email);
//			System.out.println("m is "+m.getEmail());
			// 매개변수로 입력받은 사용자 이메일 및 비밀번호가 일치하는 값이 있다면
			if (m.getEmail().equals(email) && m.getPassword().equals(password)) {
				// 결과 값을 리턴할 유저 변수에 할당
				user = m;
				// 결과 값을 찾았으므로, 더 이상 반복문을 돌릴 필요가 없다. break;
				break;
			}
		}
		// 결과 값 리턴 -> 결과 값이 null이라면, 사용자가 없다는 뜻이다. 가져다 쓸 때 널 체크 필수!
		return user;
	}
	
	public boolean isWrongPassword(String email, String password) {
		boolean result = false;
		Set<String> key = this.map.keySet();
		List<String> temp = new ArrayList<String>(key);
		Iterator<String> it = temp.iterator();
		while (it.hasNext()) {
			String keyTemp = (String) it.next();
			Member u = this.map.get(keyTemp);
			if (u.getEmail().equals(email) && !u.getPassword().equals(password)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	//전체 회원 목록 출력
	public void showAllMembers() {
		System.out.println();
		System.out.println("전체 회원 목록 입니다.");
		StringBuilder sb = new StringBuilder();
		sb.append("---------------------------------------------------------------------------------------\n");
		sb.append(String.format("이메일/비번/이름/전화번호/회원가입 날짜\n"));
		sb.append("---------------------------------------------------------------------------------------\n");
		
		Set<String> key = this.map.keySet();
		
		List<String> list = new ArrayList<String>(key);
		
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String b1, String b2) {
				return b1.compareTo(b2);
			}
		});
		
		for (String memberlist : list) {
			sb.append(String.format("%s/%s/%s/%s/%s\n",
					this.map.get(memberlist).getEmail(),this.map.get(memberlist).getPassword(),this.map.get(memberlist).getName(),
					this.map.get(memberlist).getPhone(),this.map.get(memberlist).getRegisterDate()));
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
		FileOutputStream fs = null;
		ObjectOutputStream os = null;
		try {
			// 사용자 객체가 저장된 컬렉션의 크기가 0보다 크다면
			if (this.map.size() > 0) {				
				// D:\\users.data 파일의 스트림 객체 생성
				fs = new FileOutputStream(USER_FILE);
				os = new ObjectOutputStream(fs);
				// 컬렉션 저장소에 저장된 모든 정보를 직렬화 시도
				os.writeObject(this.map);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 스트림 닫아주기
				os.close();
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 파일 저장용 메소드 선언
	// @Param 파일 이름이 포함된 경로(USER_FILE, BOOK_FILE, CHECKOUT_FILE)
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
