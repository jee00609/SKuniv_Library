package com.example.demo;


//관리자 저장
public class Utils {


	//관리자 이메일이자 비번
	//여길 바꾸면 관리자 로그인 이메일이 변경된다!
	private final String ADMIN = "admin";

	//https://shxrecord.tistory.com/132
	//3.DCL(Double-Checking Locking) + volatile
	//DCL은 인스턴스를 체크하여 인스턴스가 null일 경우에만 동기화를 한다. 즉, 최초 인스턴스 생성시에만 동기화 블럭에 진입하게 되면서 이후로는 동기화되지 않는다.
	//최초 사용시점에서만 인스턴스화를 시키기 때문에, 프로그램이 메모리에 적재되는 시점에 부담이 많이 줄게된다.
	private static Utils utils = null;

	private Member member;

	private Utils() {

	}

	//https://shxrecord.tistory.com/132
	public static synchronized Utils getInstance() {
		if (utils == null) {
			utils = new Utils();
		}
		return Utils.utils;
	}

	public String getAdmin() {
		return ADMIN;
	}

	public void setCurrentMember(Member member) {
		this.member = member;
	}

	public Member getCurrentMember() {
		return member;
	}

}
