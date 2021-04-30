package com.example.demo;

import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

public class LibraryApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath*:applicationContext.xml");
		MemberRegisterService regSvc = (MemberRegisterService) ctx.getBean("memberRegSvc");
		InfoPrinter infoPrinter = (InfoPrinter) ctx.getBean("infoPrinter");
		
		LogoutService logoutSvc = (LogoutService) ctx.getBean("logoutSvc");
		LoginRequestService loginReqSvc = (LoginRequestService) ctx.getBean("loginReqSvc");
		
		ChangePasswordService changePwSvc = (ChangePasswordService) ctx.getBean("changePwSvc");
		
		AdminMenuService adminMnSvc = (AdminMenuService) ctx.getBean("adminMnSvc");
		AdminSubBookMenuService adminSBMnSvc = (AdminSubBookMenuService) ctx.getBean("adminSBMnSvc");
		
		UserMenuService userMnSvc = (UserMenuService) ctx.getBean("userMnSvc");
		
		BookRegisterService bookRegSvc = (BookRegisterService) ctx.getBean("bookRegSvc");
		BookDeleteService bookDelSvc = (BookDeleteService) ctx.getBean("bookDelSvc");
		
		RentalRequestService rentalReqSvc = (RentalRequestService) ctx.getBean("rentalReqSvc");
		RentalReturnService rentalRetSvc = (RentalReturnService) ctx.getBean("rentalRetSvc");
		
		String loginUser;
		
		try {
			while (true) {
				System.out.println();
				System.out.println(
						"=======================================================================================");
				System.out.println("\t\t\t\t서경대학교 도서관");
				System.out.println(
						"=======================================================================================");
				System.out.println("\t\t\t\t회원가입 혹은 로그인");
				System.out.println();

				System.out.println(
						"=======================================================================================");
				System.out.println("\t\t\t1.회원가입   2.로그인   3.정보 수정   0.종료");
				System.out.println(
						"=======================================================================================");
				System.out.print("선택 > ");
				int input = sc.nextInt();
				sc.nextLine();
				if (input == 0) {
					ctx.close();
					break;
				}
				switch (input) {
				case 1:
					regSvc.regist();
					break;
				case 2:
//					service.login(sc);
					loginUser = loginReqSvc.login();
					if (loginUser == null) {
						break;
					}
					//관리자 메뉴
					else if (loginUser.equals("admin")) {
						while(true) {
							System.out.println("관리자 로그인 리턴");
							int adminMenuStart = adminMnSvc.amdinMenu();
							if (adminMenuStart == 0) break;
							else if (adminMenuStart==1) {
								System.out.println("관리자 도서관리 리턴");
								int adminBook = adminSBMnSvc.amdinSBMenu();
								if (adminBook==0)break;
								//전체 도서 목록 출력
								else if (adminBook==1) {
//									bookRegSvc.regist();
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
										System.out.println();
										System.out.println(
												"=======================================================================================");
										System.out.print("도서 ISBN 검색 > ");
										String adminSearchBookISBN = sc.nextLine();
										infoPrinter.printBookInfoByISBN(adminSearchBookISBN);
									}
									else if (adminSearchBook==2) {
										System.out.println();
										System.out.println(
												"=======================================================================================");
										System.out.print("도서 제목 검색 > ");
										String adminSearchBookTitle = sc.nextLine();
										System.out.println(adminSearchBookTitle);
										infoPrinter.printBookInfoByTitle(adminSearchBookTitle);
									}
								}
								//도서 삭제
								else if (adminBook==5) {
									System.out.println();
									System.out.println(
											"=======================================================================================");
									System.out.print("도서 ISBN 검색 > ");
									String adminSearchOverdueBookISBN = sc.nextLine();
									infoPrinter.showAdminOverdue(adminSearchOverdueBookISBN);
								}
							}
							else if (adminMenuStart==2) {
								System.out.println("관리자 회원관리 리턴");
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
										System.out.println();
										System.out.println(
												"=======================================================================================");
										System.out.print("회원 E-mail 검색 > ");
										String adminSearchEmail = sc.nextLine();
										infoPrinter.printMemberInfo(adminSearchEmail);
									}
									else if (adminSearchMember==2) {
										System.out.println();
										System.out.println(
												"=======================================================================================");
										System.out.print("회원 이름 검색 > ");
										String adminSearchName = sc.nextLine();
//										System.out.println(adminSearchName);
										infoPrinter.printMemberInfoByName(adminSearchName);
									}
								}
							}
						}
					}
					//사용자 메뉴
					else {
						while(true) {
							System.out.println(loginUser+" 리턴 ");
							System.out.println("제발 제발 이메일"+loginReqSvc.loginEmail());
							String currentEmail = loginReqSvc.loginEmail();
							int userMenuStart = userMnSvc.userMenu();
							if (userMenuStart == 0) break;
							//전체 도서 출력
							else if (userMenuStart == 1) {
								infoPrinter.showBook();
							}
							else if (userMenuStart == 2) {
								int userSearchBook = userMnSvc.userMenu_Search();
								if (userSearchBook==0)break;
								else if (userSearchBook==1) {
									System.out.println();
									System.out.println(
											"=======================================================================================");
									System.out.print("도서 ISBN 검색 > ");
									String adminSearchBookISBN = sc.nextLine();
									infoPrinter.printBookInfoByISBN(adminSearchBookISBN);
								}
								else if (userSearchBook==2) {
									System.out.println();
									System.out.println(
											"=======================================================================================");
									System.out.print("도서 제목 검색 > ");
									String adminSearchBookTitle = sc.nextLine();
									System.out.println(adminSearchBookTitle);
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
							//사용자가 빌린 책들만
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
				default:
					System.out.println("0 1 2 3번 중 하나를 입력해주세요. ");
					break;
				}
			}
			sc.close();
			logoutSvc.fileSave();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		

		infoPrinter.printMemberInfo("jee00609@naver.com");
		infoPrinter.printBookInfoByISBN("9788964212660");
		infoPrinter.printRentalByISBN("9788964212660");
		ctx.close();
	}
	
	
}
