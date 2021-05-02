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

//회원 클래스에 대한 기능들 제공
//각종 서비스 클래스에서 요놈들을 가져간다. WA!
public class MemberDao {
	//책참고(스프링 5 입문) 2020-05-02 ppt 에서 했다고 기억했는데 아니더라
	private static long nextId = 0;
	private Map<String, Member> map = new HashMap<String, Member>();
	// .dat 파일로 저장한다. 직렬화 역직렬화 사용
	// txt 파일보다 더 쉽게 긁어올 수 있다는 장점이 있다.
	// 사용자 정보를 저장할 파일 경로 및 파일 이름
	private static final String MEMBER_FILE = "members.dat";
	
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
			// Member 타입의 임시 변수 선언 후 map 맵 객체의 값 할당
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
	// obj 캐스팅 컴파일 경고를 사용하지 않도록 설정
	@SuppressWarnings("unchecked")
	public MemberDao() {
		File file = new File(MEMBER_FILE);
		Object obj = null;
		// 사용자 정보 파일이 존재 할 경우라면
		if (file.exists()) {
			// 역직렬화 해서 파일 가져오기
			obj = deSerialization(MEMBER_FILE);
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
	// 이미 KEY 값이 존재할 때 똑같은 KEY 값으로 VALUE 입력시 덮어써버리는 것을 이용해서 간단히 만듬
	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
	
	// 사용자 존재 여부 확인용
	// 프라이머리 키인 e-mail 을 기준으로 하기에 단 하나의 e-mail 만 찾아도 break 하게 작성
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
			// e-mail 및 비밀번호가 일치하는 값이 있다면
			if (m.getEmail().equals(email) && m.getPassword().equals(password)) {
				// 결과 값을 리턴할 유저 변수에 할당
				user = m;
				// 결과 값을 찾았으므로, 더 이상 반복문을 돌릴 필요가 없다. break;
				break;
			}
		}
		// 결과 값 리턴해주는데 null 값이면 사용자가 없다는 것!
		return user;
	}
	
	// LoginRequest 서비스 및 ChangePasswordService 에서 사용
	// member.dat 에서 꺼내온 값들에 대해서 map 에 저장해뒀는데
	// 이걸 현재 사용자가 입력한 값과 일치하는지 확인해주는 함수
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
	//BookDao 랑 사용하는 해시맵만 다르지 알고리즘은 완벽히 똑같다. 양산형이라고 볼 수 있다.
	// 솔직히 리팩토링 해보고 싶긴한데 이건 나중에 기말고사 때 시도해보기로 하자
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
	
	// members.dat 파일 생성
	// 자바 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 바이트(byte) 형태로 데이터 변환하는 기술과 바이트로 변환된 데이터를 다시 객체로 변환하는 기술
	// java.io.NotSerializableException 에러 발생!!!!!!!!! 이거 어케 고쳐....
	// https://woowabros.github.io/experience/2017/10/17/java-serialize.html 참고
	// Member 에 implements Serializable 해준다.
	public void logout() {
		
		try {
			// 직렬화를 위한 스트림 생성
			FileOutputStream fs = null;
			ObjectOutputStream os = null;
			
			// 사용자 객체가 저장된 컬렉션의 크기가 0보다 크다면
			if (this.map.size() > 0) {				
				// D:\\users.data 파일의 스트림 객체 생성
				fs = new FileOutputStream(MEMBER_FILE);
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
