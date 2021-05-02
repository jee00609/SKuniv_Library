package com.example.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

public class LibraryApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath*:applicationContext.xml");
		// 회원등록
		MemberRegisterService regSvc = (MemberRegisterService) ctx.getBean("memberRegSvc");
		// 출력 기능들 대부분
		InfoPrinter infoPrinter = (InfoPrinter) ctx.getBean("infoPrinter");
		
		// 로그인 기능
		LoginRequestService loginReqSvc = (LoginRequestService) ctx.getBean("loginReqSvc");
		// 종료 기능 -> 파일 저장
		LogoutService logoutSvc = (LogoutService) ctx.getBean("logoutSvc");

		// 비밀 번호 수정
		ChangePasswordService changePwSvc = (ChangePasswordService) ctx.getBean("changePwSvc");
		
		// 관리자 메인 메뉴 (도서 관리 / 회원 관리)
		AdminMenuService adminMnSvc = (AdminMenuService) ctx.getBean("adminMnSvc");
		// 관리자 서브 메뉴
		AdminSubBookMenuService adminSBMnSvc = (AdminSubBookMenuService) ctx.getBean("adminSBMnSvc");
		
		// 회원 메뉴
		UserMenuService userMnSvc = (UserMenuService) ctx.getBean("userMnSvc");
		
		// 책 등록
		BookRegisterService bookRegSvc = (BookRegisterService) ctx.getBean("bookRegSvc");
		// 책 삭제
		BookDeleteService bookDelSvc = (BookDeleteService) ctx.getBean("bookDelSvc");
		
		// 책 대여
		RentalRequestService rentalReqSvc = (RentalRequestService) ctx.getBean("rentalReqSvc");
		// 책 반납
		RentalReturnService rentalRetSvc = (RentalReturnService) ctx.getBean("rentalRetSvc");
		
		//공지 등록
		NoticeService noticeSvc = (NoticeService) ctx.getBean("noticeSvc");
		
		int input;
		String loginUser;
		
		try {
			while (true) {
				System.out.println();
				System.out.println(
						"=======================================================================================");
				System.out.println("\t\t\t\t서경대학교 도서관");
				System.out.println(
						"=======================================================================================");
				System.out.println();
				System.out.println("\t\t로그인을 하시면 사용가능합니다.(처음 이용하실 경우 회원가입을 진행해주세요.)");
				System.out.println();

				System.out.println(
						"=======================================================================================");
				System.out.println("\t\t1.회원가입   2.로그인   3.정보 수정   4.공지 목록   0.종료");
				System.out.println(
						"=======================================================================================");

				System.out.print("선택 > ");
				
				while(true) {
					try {
						input = sc.nextInt();
						break;
					}catch(InputMismatchException e) {
						System.out.println("정수만 입력해주세요.");
						System.out.println("자동으로 종료합니다.");
						input = 0;
						break;
					}
				}

				// 종료
				if (input == 0) {
					ctx.close();
					break;
				}
				switch (input) {
				// 회원가입
				case 1:
					regSvc.regist();
					break;
				// 로그인
				case 2:
					//로그인 할 때 admin 이면 관리자 / 아닐시 회원인지 확인하고 로그인
					loginUser = loginReqSvc.login();
					if (loginUser == null) {
						break;
					}
					//관리자 메뉴
					else if (loginUser.equals("admin")) {
						while(true) {
//							System.out.println("관리자 로그인 리턴");
							// 도서관리 / 회원 관리 선택
							int adminMenuStart = adminMnSvc.amdinMenu();
							// 종료
							if (adminMenuStart == 0) break;
							//도서 관리
							else if (adminMenuStart==1) {
//								System.out.println("관리자 도서관리 리턴");
								int adminBook = adminSBMnSvc.amdinSBMenu();
								if (adminBook==0)break;
								//전체 도서 목록 출력
								else if (adminBook==1) {
									infoPrinter.showBook();
								}
								//도서 등록
								else if (adminBook==2) {
									bookRegSvc.regist();
								}
								//도서 삭제
								else if (adminBook==3) {
									bookDelSvc.delete();
								}
								//도서 검색
								else if (adminBook==4) {
									int adminSearchBook = adminSBMnSvc.amdinSBMenu_Search();
									if (adminSearchBook==0)break;
									else if (adminSearchBook==1) {
										Scanner sc2 = new Scanner(System.in);
										String adminSearchBookISBN;
										System.out.println();

										System.out.print("도서 ISBN 검색 > ");
										adminSearchBookISBN = sc2.nextLine();
//										System.out.println(adminSearchBookISBN);
										
										infoPrinter.printBookInfoByISBN(adminSearchBookISBN);
//										
									}
									else if (adminSearchBook==2) {
										Scanner sc2 = new Scanner(System.in);
										String adminSearchBookTitle;
										System.out.println();

										System.out.print("도서 제목 검색 > ");
										adminSearchBookTitle = sc2.nextLine();
//										System.out.println(adminSearchBookTitle);
										infoPrinter.printBookInfoByTitle(adminSearchBookTitle);
										
									}
								}
								//연체 검색
								else if (adminBook==5) {
									System.out.println();
									System.out.println(
											"=======================================================================================");
									Scanner sc2 = new Scanner(System.in);
									System.out.print("도서 ISBN 검색 > ");
									String adminSearchOverdueBookISBN = sc2.nextLine();
									infoPrinter.printIsOverdue(adminSearchOverdueBookISBN);
									
								}
							}
							// 회원 관리
							else if (adminMenuStart==2) {
//								System.out.println("관리자 회원관리 리턴");
								int adminBook = adminSBMnSvc.amdinSBMenu2();
								if (adminBook==0)break;
								//전체 회원 검색
								else if (adminBook==1) {
									infoPrinter.showMember();
								}
								//회원 검색
								else if (adminBook==2) {
									int adminSearchMember = adminSBMnSvc.amdinSBMenu2_Search();
									if (adminSearchMember==0)break;
									else if (adminSearchMember==1) {
										Scanner sc2 = new Scanner(System.in);
										System.out.println();
										System.out.println(
												"=======================================================================================");
										System.out.print("회원 E-mail 검색 > ");
										String adminSearchEmail = sc2.nextLine();
										infoPrinter.printMemberInfo(adminSearchEmail);
										
									}
									else if (adminSearchMember==2) {
										Scanner sc2 = new Scanner(System.in);
										System.out.println();
										System.out.println(
												"=======================================================================================");
										System.out.print("회원 이름 검색 > ");
										String adminSearchName = sc2.nextLine();
//										System.out.println(adminSearchName);
										infoPrinter.printMemberInfoByName(adminSearchName);
										
									}
								}
							}
							//공지 등록
							else if (adminMenuStart==3) {
								noticeSvc.regist();
							}
						}
					}
					//사용자 메뉴
					else {
						while(true) {
//							System.out.println(loginUser+" 리턴 ");
//							System.out.println("제발 제발 이메일"+loginReqSvc.loginEmail());
							//현재 로그인 되어있는 이메일 반환
							String currentEmail = loginReqSvc.loginEmail();
							
							int userMenuStart = userMnSvc.userMenu();
							
							//사용자 로그아웃
							if (userMenuStart == 0) break;
							//전체 도서 출력
							else if (userMenuStart == 1) {
								infoPrinter.showBook();
							}
							// 도서 검색
							else if (userMenuStart == 2) {
								int userSearchBook = userMnSvc.userMenu_Search();
								// 도서 검색 종료
								if (userSearchBook==0)break;
								// 도서 검색 -> ISBN 오로지 하나 출력
								else if (userSearchBook==1) {
									Scanner sc2 = new Scanner(System.in);
									System.out.println();
									System.out.println(
											"=======================================================================================");
									System.out.print("도서 ISBN 검색 > ");
									String adminSearchBookISBN = sc2.nextLine();
									infoPrinter.printBookInfoByISBN(adminSearchBookISBN);
									
								}
								// 도서 검색 -> 제목 (중복된 제목을 가지면 모두 출력)
								else if (userSearchBook==2) {
									Scanner sc2 = new Scanner(System.in);
									System.out.println();
									System.out.println(
											"=======================================================================================");
									System.out.print("도서 제목 검색 > ");
									String adminSearchBookTitle = sc2.nextLine();
//									System.out.println(adminSearchBookTitle);
									infoPrinter.printBookInfoByTitle(adminSearchBookTitle);
									
								}
							}
							//도서 대여
							else if (userMenuStart==3) {
								rentalReqSvc.regist(currentEmail);
							}
							//도서반납
							else if (userMenuStart==4) {
								rentalRetSvc.returnB(currentEmail);
							}
							//히스토리 기능 (현재까지 빌렸었던 책들을 출력)
							else if (userMenuStart==5) {
								infoPrinter.printRentalInfoByemail(currentEmail);
							}
							
						}
					}
					break;
				// 비밀 번호 수정
				case 3:
					changePwSvc.changePw();
					break;
				case 4:
					int detailNotice = infoPrinter.showNotice();
					if (detailNotice==0)break;
					else {
						Scanner sc2 = new Scanner(System.in);
						System.out.println();
						System.out.println(
								"=======================================================================================");
						System.out.print("공지 번호 검색 > ");
						String SearchNum = sc2.nextLine();
						infoPrinter.printNoticeByNoticeNum(SearchNum);
					}
					break;
				// 이상한 숫자 적으면 출력
				default:
					System.out.println("0 1 2 3번 중 하나를 입력해주세요. ");
					break;
				}
			}
			// 스캐너 종료
			sc.close();
			// 프로그램 돌리면서 했던 member book rental 저장
			logoutSvc.fileSave();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
//		테스트
//		infoPrinter.printMemberInfo("jee00609@naver.com");
//		infoPrinter.printBookInfoByISBN("9788964212660");
//		infoPrinter.printRentalByISBN("9788964212660");
//		infoPrinter.printIsOverdue("9788964212660");
//		infoPrinter.showNotice();
		ctx.close();
	}
	
	
}
